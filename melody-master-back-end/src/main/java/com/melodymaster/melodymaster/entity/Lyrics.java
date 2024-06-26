package com.melodymaster.melodymaster.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "lyrics")
public class Lyrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "song_id")
    // private Song song;
    @OneToOne
    @JoinColumn(name = "song_id")
    private Song song;


    // @ManyToOne
    // @JoinColumn(name = "audio_file_id")
    // private AudioFile audio_file;

    @Column(name = "content")
    private String content;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
    
}