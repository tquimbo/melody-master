package com.melodymaster.melodymaster.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Song {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    private String artist;
    
    private String album;
    
    private Integer year;

    private String genre;

    // @ManyToOne
    // @JoinColumn(name = "user_recording_id")
    // private UserRecording userRecording;

    // @ManyToOne
    // @JoinColumn(name = "note_id")
    // private Note note;

    // @ManyToOne
    // @JoinColumn(name = "lyrics_id")
    // private Lyrics lyric;

    @OneToOne(mappedBy = "song", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AudioFile audioFile;

    @OneToOne(mappedBy = "song", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Lyrics lyrics;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Score> scores;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SongHistory> songHistories;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRecording> userRecordings;


    

    public Song() {}
    
    // public Song(String title, String artist, String album, Integer year, String genre) {
    //     this.title = title;
    //     this.artist = artist;
    //     this.album = album;
    //     this.year = year;
    //     this.genre = genre;
    // }
    
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
    
    public String getAlbum() {
        return album;
    }
    
    public void setAlbum(String album) {
        this.album = album;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }

        public AudioFile getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(AudioFile audioFile) {
        this.audioFile = audioFile;
    }

    public Lyrics getLyrics() {
        return lyrics;
    }

    public void setLyrics(Lyrics lyrics) {
        this.lyrics = lyrics;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public List<SongHistory> getSongHistories() {
        return songHistories;
    }

    public void setSongHistories(List<SongHistory> songHistories) {
        this.songHistories = songHistories;
    }

    public List<UserRecording> getUserRecordings() {
        return userRecordings;
    }

    public void setUserRecordings(List<UserRecording> userRecordings) {
        this.userRecordings = userRecordings;
    }
    

    
}


