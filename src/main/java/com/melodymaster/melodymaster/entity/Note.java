package com.melodymaster.melodymaster.entity;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "pitch")
    private Double pitch;
    
    @Column(name = "duration")
    private Integer duration;

    @Column(name = "lyrics")
    private String lyrics;
    
    // constructors, getters and setters
    public Note() {}
    
    public Note(Double frequency, Integer duration, String lyrics) {
        this.pitch = pitch;
        this.duration = duration;
        this.lyrics = lyrics;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Double getPitch() {
        return pitch;
    }
    
    public void setPitch(Double pitch) {
        this.pitch = pitch;
    }
    
    public Integer getDuration() {
        return duration;
    }
    
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getLyrics() {
        return lyrics;
    }
    
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
