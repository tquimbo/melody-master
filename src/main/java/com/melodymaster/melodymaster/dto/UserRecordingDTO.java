package com.melodymaster.melodymaster.dto;

public class UserRecordingDTO {
    private Long id;
    private Long userId;
    private Long songId;
    private Date recordingDate;
    private String recordingUrl;
    
    // Constructors
    public UserRecordingDTO() {}
    
    public UserRecordingDTO(Long id, Long userId, Long songId, Date recordingDate, String recordingUrl) {
        this.id = id;
        this.userId = userId;
        this.songId = songId;
        this.recordingDate = recordingDate;
        this.recordingUrl = recordingUrl;
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Date getRecordingDate() {
        return recordingDate;
    }

    public void setRecordingDate(Date recordingDate) {
        this.recordingDate = recordingDate;
    }

    public String getRecordingUrl() {
        return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl;
    }
}