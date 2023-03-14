package com.melodymaster.melodymaster.entity;

@Entity
@Table(name = "user_recording")
public class UserRecording {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id")
    private Song song;
    
    @Column(name = "recording_date")
    private Date recordingDate;
    
    @Column(name = "recording_url")
    private String recordingUrl;
    
    // Constructors
    public UserRecording() {}
    
    public UserRecording(User user, Song song, Date recordingDate, String recordingUrl) {
        this.user = user;
        this.song = song;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
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