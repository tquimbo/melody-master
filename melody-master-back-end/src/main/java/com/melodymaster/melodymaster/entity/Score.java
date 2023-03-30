package com.melodymaster.melodymaster.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "scores")
public class Score {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "user_recording_id")
    private int userRecordingId;
    
    @Column(name = "song_id")
    private int songId;
    
    @Column(name = "accuracy_score")
    private double accuracyScore;
    
    @Column(name = "timing_score")
    private double timingScore;
    
    @Column(name = "total_score")
    private double totalScore;
    
    public Score() {}
    
    public Score(int userRecordingId, int songId, double accuracyScore, double timingScore) {
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