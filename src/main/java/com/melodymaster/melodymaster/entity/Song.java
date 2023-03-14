package com.melodymaster.melodymaster.entity;

public class SongMetaDataDTO {
    private Long id;
    private String title;
    private String artist;
    private String album;
    private int year;
    
    public SongMetaDataDTO() {}
    
    public SongMetaDataDTO(String title, String artist, String album, int year) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
