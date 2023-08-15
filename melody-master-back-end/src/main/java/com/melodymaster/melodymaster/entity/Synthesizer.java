package com.melodymaster.melodymaster.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.*;

@Entity
@Table(name = "synthesizer")
public class Synthesizer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    // Default constructor
    public Synthesizer() {
        // intentionally left blank
    }

    // Parameterized constructor
    public Synthesizer(String name, String manufacturer, String model) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    // Getters and setters
    // ... [rest of the code remains unchanged]
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

// @Entity
// @Table(name = "synthesizer")
// public class Synthesizer {
    
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "name")
//     private String name;

//     @Column(name = "manufacturer")
//     private String manufacturer;

//     @Column(name = "model")
//     private String model;

//     // Constructor
//     public Synthesizer(String name, String manufacturer, String model) {
//         this.name = name;
//         this.manufacturer = manufacturer;
//         this.model = model;
//     }

//     // Getters and setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getManufacturer() {
//         return manufacturer;
//     }

//     public void setManufacturer(String manufacturer) {
//         this.manufacturer = manufacturer;
//     }

//     public String getModel() {
//         return model;
//     }

//     public void setModel(String model) {
//         this.model = model;
//     }
// }
