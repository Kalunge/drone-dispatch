package com.drone.controller;

import com.drone.model.Drone;
import com.drone.model.Medication;
import com.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {
    @Autowired
    private DroneService droneService;

    @PostMapping
    public Drone registerDrone(@RequestBody Drone drone) {
        return droneService.registerDrone(drone);
    }

    @GetMapping("/available")
    public List<Drone> getAvailableDrones() {
        return droneService.getAvailableDrones();
    }

    @GetMapping("/{droneId}/medications")
    public List<Medication> getLoadedMedications(@PathVariable Long droneId) {
        return droneService.getLoadedMedications(droneId);
    }

    @PostMapping("/{droneId}/medications")
    public void loadMedications(@PathVariable Long droneId, @RequestBody List<Medication> medications) throws Exception {
        droneService.loadMedications(droneId, medications);
    }

    @GetMapping("/{droneId}/battery")
    public int getDroneBatteryLevel(@PathVariable Long droneId) {
        return droneService.getDroneBatteryLevel(droneId);
    }
}
