package com.drone.controller;

import com.drone.dto.DroneDto;
import com.drone.dto.MedicationDto;
import com.drone.model.Drone;
import com.drone.service.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {
    private final DroneService droneService;



    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    public Drone registerDrone(@RequestBody Drone drone) {
        return droneService.registerDrone(drone);
    }

    @GetMapping("/available")
    public List<DroneDto> getAvailableDrones() {
        return droneService.getAvailableDrones();
    }

    @GetMapping("/{droneId}/medications")
    public List<MedicationDto> getLoadedMedications(@PathVariable Long droneId) {
        return droneService.getLoadedMedications(droneId);
    }

    @PostMapping("/{droneId}/medications")
    public ResponseEntity<Object> loadMedications(@PathVariable Long droneId, @RequestBody List<MedicationDto> medications) throws Exception {
        try {
            droneService.loadMedications(droneId, medications);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{droneId}/battery")
    public int getDroneBatteryLevel(@PathVariable Long droneId) {
        return droneService.getDroneBatteryLevel(droneId);
    }
}
