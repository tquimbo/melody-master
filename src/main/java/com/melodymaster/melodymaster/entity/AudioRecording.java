package com.melodymaster.melodymaster.entity;

@Entity
@Table(name = "audio_recordings")
public class AudioRecording {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String genre;

    @Column(name = "audio_url", nullable = false)
    private String audioUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_recording_id", nullable = false)
    private UserRecording userRecording;

    // constructors, getters and setters

}