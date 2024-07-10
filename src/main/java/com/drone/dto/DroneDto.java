package com.drone.dto;

public class DroneDto {

    private Long id;
    private String serialNumber;
    private String model;
    private float weightLimit;
    private int batteryCapacity;
    private String state;


    public DroneDto(Long id, String serialNumber, String model, float weightLimit, int batteryCapacity, String state) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }
}
