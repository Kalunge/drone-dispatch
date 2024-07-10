package com.drone.service;


import com.drone.ResourceNotFoundException;
import com.drone.dto.DroneDto;
import com.drone.dto.MedicationDto;
import com.drone.enums.State;
import com.drone.model.Drone;
import com.drone.model.Medication;
import com.drone.repository.DroneRepository;
import com.drone.repository.MedicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;


    public DroneService(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    public List<DroneDto> getAvailableDrones() {
        return droneRepository.findByState(State.IDLE).stream()
                .map(drone -> new DroneDto(
                        drone.getId(),
                        drone.getSerialNumber(),
                        drone.getModel().name(),
                        drone.getWeightLimit(),
                        drone.getBatteryCapacity(),
                        drone.getState().name(),
                        drone.getMedications().stream()
                                .map(medication -> new MedicationDto(
                                        medication.getId(),
                                        medication.getName(),
                                        medication.getWeight(),
                                        medication.getCode(),
                                        medication.getImage(),
                                        drone.getSerialNumber()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public List<MedicationDto> getLoadedMedications(Long droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));

        return drone.getMedications().stream()
                .map(medication -> new MedicationDto(
                        medication.getId(),
                        medication.getName(),
                        medication.getWeight(),
                        medication.getCode(),
                        medication.getImage(),
                        drone.getSerialNumber()))
                .collect(Collectors.toList());
    }

    public void loadMedications(Long droneId, List<MedicationDto> medicationDtos) throws Exception {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));

        if (drone.getBatteryCapacity() < 25) {
            throw new IllegalArgumentException("Battery level too low to load medications");
        }

        double totalWeight = medicationDtos.stream()
                .mapToDouble(MedicationDto::getWeight)
                .sum();

        if (totalWeight > drone.getWeightLimit()) {
            throw new IllegalArgumentException("Total weight exceeds drone's weight limit");
        }

        List<Medication> medications = medicationDtos.stream()
                .map(dto -> new Medication(
                        dto.getName(),
                        dto.getWeight(),
                        dto.getCode(),
                        dto.getImage(),
                        drone))
                .collect(Collectors.toList());

        drone.setState(State.LOADING);
        drone.setMedications(medications);
        droneRepository.save(drone);
//       medicationRepository.saveAll(medications);
    }

    public int getDroneBatteryLevel(Long droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));
        return drone.getBatteryCapacity();
    }

    @Scheduled(fixedRate = 60000) // every 60 seconds
    public void checkDronesBatteryLevels() {
        List<Drone> drones = droneRepository.findAll();
        for (Drone drone : drones) {
//            log.info("Drone {} battery level: {}%", drone.getSerialNumber(), drone.getBatteryCapacity());
            // Store logs in db or integrate with a monitoring service.
            System.out.println("Drone " + drone.getSerialNumber() + " battery level: " + drone.getBatteryCapacity() + "%");

        }
    }
}