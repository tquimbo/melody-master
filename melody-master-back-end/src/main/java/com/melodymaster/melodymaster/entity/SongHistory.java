package com.melodymaster.melodymaster.entity;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

// import com.melodymaster.melodymaster.entity.ScoreEntity;

@Entity
@Table(name = "song_history")
public class SongHistory {
    
  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    // @ManyToOne
    // @JoinColumn(name = "song_id")
    // private Song song;
     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;


    @Column(name = "played_at")
    private Date playedAt;

    // @OneToOne
    // @JoinColumn(name = "score_id") // Assuming there's a separate entity for score
    // private ScoreEntity score;



    public SongHistory() {}

    // public SongHistory(Long userId, Long songId, Date playedAt, double score) {
    //     this.userId = userId;
    //     this.songId = songId;
    //     // this.playedAt = playedAt;
    //     // this.score = score;
    // }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getSongId() {
        return song.getId();
    }

    public Date getPlayedAt() {
        return playedAt;
    }

    // public Double getScore() {
    //     return score;
    // }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSongId(Long songId) {
        this.song.setId(songId);
    }

    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }

    // public void setScore(int score) {
    //     this.score = score;
    // }
}