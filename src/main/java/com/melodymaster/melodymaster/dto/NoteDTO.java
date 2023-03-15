package com.melodymaster.melodymaster.dto;

public class NoteDTO {
    private Long id;
    private Double pitch;
    private Integer duration;
    private String lyrics;

    
    // getters and setters
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