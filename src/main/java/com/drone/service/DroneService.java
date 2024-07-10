package com.drone.service;


import com.drone.ResourceNotFoundException;
import com.drone.dto.DroneDto;
import com.drone.dto.MedicationDto;
import com.drone.enums.State;
import com.drone.model.Drone;
import com.drone.model.Medication;
import com.drone.repository.DroneRepository;
import com.drone.repository.MedicationRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
        return droneRepository.findById(droneId)
                .map(drone -> drone.getMedications().stream()
                        .map(medication -> new MedicationDto(
                                medication.getId(),
                                medication.getName(),
                                medication.getWeight(),
                                medication.getCode(),
                                medication.getImage(),
                                drone.getSerialNumber()))
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));
    }

    public Drone getDroneById(Long droneId) {
        return droneRepository.findById(droneId).orElse(null);
    }

    public void loadMedications(Long droneId, List<MedicationDto> medicationDtos) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));

        List<Medication> medications = medicationDtos.stream()
                .map(dto -> new Medication(dto.getName(), dto.getWeight(), dto.getCode(), dto.getImage(), drone))
                .collect(Collectors.toList());

        drone.setMedications(medications);
        droneRepository.save(drone);
    }

    public int getDroneBatteryLevel(Long droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));
        return drone.getBatteryCapacity();
    }
}
