package com.melodymaster.melodymaster.dto;

public class ScoreDTO {
    private int id;
    private int userRecordingId;
    private int songId;
    private double accuracyScore;
    private double timingScore;
    private double totalScore;
    
    public ScoreDTO(int id, int userRecordingId, int songId, double accuracyScore, double timingScore) {
        this.id = id;
        this.userRecordingId = userRecordingId;
        this.songId = songId;
        this.accuracyScore = accuracyScore;
        this.timingScore = timingScore;
        this.totalScore = (accuracyScore + timingScore) / 2; // Calculate total score as average of accuracy and timing scores
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public int getUserRecordingId() {
        return userRecordingId;
    }
    
    public int getSongId() {
        return songId;
    }
    
    public double getAccuracyScore() {
        return accuracyScore;
    }
    
    public double getTimingScore() {
        return timingScore;
    }
    
    public double getTotalScore() {
        return totalScore;
    }
    
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    
    public void setUserRecordingId(int userRecordingId) {
        this.userRecordingId = userRecordingId;
    }
    
    public void setSongId(int songId) {
        this.songId = songId;
    }
    
    public void setAccuracyScore(double accuracyScore) {
        this.accuracyScore = accuracyScore;
        this.totalScore = (accuracyScore + timingScore) / 2; // Recalculate total score when accuracy score changes
    }
    
    public void setTimingScore(double timingScore) {
        this.timingScore = timingScore;
        this.totalScore = (accuracyScore + timingScore) / 2; // Recalculate total score when timing score changes
    }
}