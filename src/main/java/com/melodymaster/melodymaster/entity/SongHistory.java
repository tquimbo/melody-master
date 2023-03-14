package com.melodymaster.melodymaster.entity;

@Entity
@Table(name = "song_history")
public class SongHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audio_recording_id", nullable = false)
    private AudioRecording audioRecording;

    @Column(name = "played_at", nullable = false)
    private LocalDateTime playedAt;

    // constructors, getters and setters

}