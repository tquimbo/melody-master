package com.melodymaster.melodymaster.entity;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "frequency")
    private Double frequency;
    
    @Column(name = "duration")
    private Integer duration;
    
    // constructors, getters and setters
    public Notes() {}
    
    public Notes(Double frequency, Integer duration) {
        this.frequency = frequency;
        this.duration = duration;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Double getFrequency() {
        return frequency;
    }
    
    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
