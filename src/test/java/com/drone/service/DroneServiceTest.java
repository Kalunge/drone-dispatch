package com.drone.service;

import static org.junit.jupiter.api.Assertions.*;


import com.drone.model.Drone;
import com.drone.repository.DroneRepository;
import com.drone.repository.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class DroneServiceTest {
    @Mock
    private DroneRepository droneRepository;

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private DroneService droneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerDrone() {
        Drone drone = new Drone();
        drone.setSerialNumber("DRONE123");
        when(droneRepository.save(drone)).thenReturn(drone);

        Drone result = droneService.registerDrone(drone);
        assertEquals("DRONE123", result.getSerialNumber());
    }

    @Test
    void getAvailableDrones() {
        //todo Add test cases for fetching available drones
    }

    @Test
    void getLoadedMedications() {
        //todo Add test cases for fetching loaded medications
    }

    @Test
    void loadMedications() {
        //todo Add test cases for loading medications
    }

    @Test
    void getDroneBatteryLevel() {
        //todo Add test cases for fetching drone battery level
    }
}
