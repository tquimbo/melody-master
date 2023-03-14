package com.melodymaster.melodymaster.dto;

public class PitchDTO {
    private Long id;
    private Double frequency;
    private Integer duration;
    
    // getters and setters
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