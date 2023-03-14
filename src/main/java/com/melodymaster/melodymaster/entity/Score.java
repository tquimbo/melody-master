package com.melodymaster.melodymaster.entity;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "song_id", referencedColumnName = "id")
    private Song song;
    
    private int score;
    
    // constructors, getters and setters
}