package com.drone.repository;

import com.drone.enums.State;
import com.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByState(State state);
}
