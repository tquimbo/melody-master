package com.melodymaster.melodymaster.dto;

public class SynthesizerDTO {
    
    private Long id;
    private String name;
    private String manufacturer;
    private String model;

    // Constructor
    public SynthesizerDTO(Long id, String name, String manufacturer, String model) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}