// package com.melodymaster.melodymaster.dto;

// public class AudioFileDTO {

//     private Long id;
//     private String title;
//     private String artist;
//     private String genre;

//     private String audioUrl;
//     private Long userRecordingId;

//     public AudioFileDTO() {
//     }

//     public AudioFileDTO(Long id, String title, String artist, String genre, String audioUrl, Long userRecordingId) {
//         this.id = id;
//         this.title = title;
//         this.artist = artist;
//         this.genre = genre;
//         this.audioUrl = audioUrl;
//         this.userRecordingId = userRecordingId;
//     }

//     // getters and setters

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
//     }

//     public String getArtist() {
//         return artist;
//     }

//     public void setArtist(String artist) {
//         this.artist = artist;
//     }

//     public String getGenre() {
//         return genre;
//     }

//     public void setGenre(String genre) {
//         this.genre = genre;
//     }

//     public String getAudioUrl() {
//         return audioUrl;
//     }

//     public void setAudioUrl(String audioUrl) {
//         this.audioUrl = audioUrl;
//     }

//     public Long getUserRecordingId() {
//         return userRecordingId;
//     }

//     public void setUserRecordingId(Long userRecordingId) {
//         this.userRecordingId = userRecordingId;
//     }
// }
package com.melodymaster.melodymaster.dto;

import java.util.List;

public class AudioFileDTO {

    private Long id;
    private String title;
    private String artist;
    private String genre;
    private String audioUrl;
    private Long userRecordingId;
    private List<NoteDTO> notes;

    public AudioFileDTO() {
    }

    public AudioFileDTO(Long id, String title, String artist, String genre, String audioUrl, Long userRecordingId, List<NoteDTO> notes) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.audioUrl = audioUrl;
        this.userRecordingId = userRecordingId;
        this.notes = notes;
    }

    // Getters and setters

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

    public Long getUserRecordingId() {
        return userRecordingId;
    }

    public void setUserRecordingId(Long userRecordingId) {
        this.userRecordingId = userRecordingId;
    }

    public List<NoteDTO> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteDTO> notes) {
        this.notes = notes;
    }
}