package com.melodymaster.melodymaster.dto;

import java.util.Date;

public class SongHistoryDTO {
    private Long id;
    private Long userId;
    private Long songId;
    private Date playedAt;
    private Double score;
    
    public SongHistoryDTO(Long id, Long userId, Long songId, Date playedAt, Double score) {
        this.id = id;
        this.userId = userId;
        this.songId = songId;
        this.playedAt = playedAt;
        this.score = score;
    }
    
    // Getters
    public Long getId() {
        return id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public Long getSongId() {
        return songId;
    }
    
    public Date getPlayedAt() {
        return playedAt;
    }
    
    public Double getScore() {
        return score;
    }
    
    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public void setSongId(Long songId) {
        this.songId = songId;
    }
    
    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }
}