package com.drone.dto;

public class MedicationDto {

    private Long id;
    private String name;
    private float weight;
    private String code;
    private String image;


    public MedicationDto(Long id, String name, float weight, String code, String image) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }
}

