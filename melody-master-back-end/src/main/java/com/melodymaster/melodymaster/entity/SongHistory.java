package com.melodymaster.melodymaster.entity;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "song_history")
public class SongHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "song_id")
    private Long songId;

    @Column(name = "played_at")
    private Date playedAt;

    @OneToOne
    @JoinColumn(name = "score_id")
    private Score score;


    public SongHistory() {}

    public SongHistory(Long userId, Long songId, Date playedAt, Double score) {
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