package com.drone.service;


import com.drone.enums.State;
import com.drone.model.Drone;
import com.drone.model.Medication;
import com.drone.repository.DroneRepository;
import com.drone.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneService {
    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    public List<Drone> getAvailableDrones() {
        return droneRepository.findByState(State.IDLE);
    }

    public List<Medication> getLoadedMedications(Long droneId) {
        Optional<Drone> drone = droneRepository.findById(droneId);
        return drone.map(Drone::getMedications).orElse(null);
    }

    public Drone getDroneById(Long droneId) {
        return droneRepository.findById(droneId).orElse(null);
    }

    public void loadMedications(Long droneId, List<Medication> medications) throws Exception {
        Drone drone = getDroneById(droneId);

        if (drone == null) {
            throw new Exception("Drone not found");
        }

        if (drone.getBatteryCapacity() < 25) {
            throw new Exception("Battery level is below 25%");
        }

        int totalWeight = medications.stream().mapToInt(Medication::getWeight).sum();
        if (totalWeight > drone.getWeightLimit()) {
            throw new Exception("Total weight exceeds the drone's weight limit");
        }

        for (Medication medication : medications) {
            medication.setDrone(drone);
            medicationRepository.save(medication);
        }

        drone.setState(State.LOADED);
        droneRepository.save(drone);
    }

    public int getDroneBatteryLevel(Long droneId) {
        Drone drone = getDroneById(droneId);
        return drone != null ? drone.getBatteryCapacity() : 0;
    }
}
