package com.melodymaster.melodymaster.dto;

public class NoteDTO {
    private double pitch;
    private double startTime;
    private double endTime;
    private String lyrics;
    private double duration;

    public NoteDTO(double pitch, double startTime, double endTime, String lyrics) {
        this.pitch = pitch;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lyrics = lyrics;
        this.duration = endTime - startTime;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}