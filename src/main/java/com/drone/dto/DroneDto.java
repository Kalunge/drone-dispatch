package com.drone.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
public class DroneDto {
    private Long id;
    private String serialNumber;
    private String model;
    private float weightLimit;
    private int batteryCapacity;
    private String state;
    private List<MedicationDto> medications;


    public DroneDto(Long id, String serialNumber, String model, float weightLimit, int batteryCapacity, String state, List<MedicationDto> medications) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.medications = medications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(float weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<MedicationDto> getMedications() {
        return medications;
    }

    public void setMedications(List<MedicationDto> medications) {
        this.medications = medications;
    }
}
