package com.melodymaster.melodymaster.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "audio_recording")
public class AudioFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @Column(name = "genre")
    private String genre;

    @Column(name = "audio_url")
    private String audioUrl;

    // @ManyToOne
    // @JoinColumn(name = "user_recording_id")
    // private UserRecording userRecording;
    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;


    @ManyToOne
    @JoinColumn(name = "lyrics_id")
    private Lyrics lyric;

    public AudioFile() {
    }

    public AudioFile(String title, String artist, String genre, String audioUrl, UserRecording userRecording) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.audioUrl = audioUrl;
        this.userRecording = userRecording;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public UserRecording getUserRecording() {
        return userRecording;
    }

    public void setUserRecording(UserRecording userRecording) {
        this.userRecording = userRecording;
    }
}