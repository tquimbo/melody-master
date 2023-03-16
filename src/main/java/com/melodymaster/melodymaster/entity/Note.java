package com.melodymaster.melodymaster.entity;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "pitch")
    private float pitch;
    
    @Column(name = "start_time")
    private Double startTime;
    
    @Column(name = "end_time")
    private Double endTime;
    
    @Column(name = "duration")
    private Double duration;
    
    @Column(name = "lyrics")
    private String lyrics;
    
    // constructors, getters and setters
    public Note() {}
    
    public Note(float pitch, Double startTime, Double endTime, Double duration, String lyrics) {
        this.pitch = pitch;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.lyrics = lyrics;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public float getPitch() {
        return pitch;
    }
    
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }
    
    public Double getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }
    
    public Double getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Double endTime) {
        this.endTime = endTime;
    }
    
    public Double getDuration() {
        return duration;
    }
    
    public void setDuration(Double duration) {
        this.duration = duration;
    }
    
    public String getLyrics() {
        return lyrics;
    }
    
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}