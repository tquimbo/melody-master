// package com.melodymaster.melodymaster.dto;

// public class NoteDTO {
//     private float pitch;
//     private double startTime;
//     private double endTime;
//     private String lyrics;
//     private double duration;

//     public NoteDTO() {
      
//     }

//     public NoteDTO(float pitch, double startTime, double endTime, String lyrics) {
//         this.pitch = pitch;
//         this.startTime = startTime;
//         this.endTime = endTime;
//         this.lyrics = lyrics;
//         this.duration = endTime - startTime;
//     }

//     public float getPitch() {
//         return pitch;
//     }

//     public void setPitch(float pitch) {
//         this.pitch = pitch;
//     }

//     public double getStartTime() {
//         return startTime;
//     }

//     public void setStartTime(double startTime) {
//         this.startTime = startTime;
//     }

//     public double getEndTime() {
//         return endTime;
//     }

//     public void setEndTime(double endTime) {
//         this.endTime = endTime;
//     }

//     public String getLyrics() {
//         return lyrics;
//     }

//     public void setLyrics(String lyrics) {
//         this.lyrics = lyrics;
//     }

//     public double getDuration() {
//         return duration;
//     }

//     public void setDuration(double duration) {
//         this.duration = duration;
//     }
// }
package com.melodymaster.melodymaster.dto;

public class NoteDTO {
    private float pitch;      // Pitch of the note in Hz
    private double startTime; // Start time of the note in seconds
    private double endTime;   // End time of the note in seconds
    private String lyrics;    // Lyrics associated with the note (if any)
    private double duration;  // Duration of the note in seconds

    /**
     * Default constructor.
     */
    public NoteDTO() {
        // This constructor is intentionally empty.
    }

    /**
     * Constructs a new NoteDTO with specified details.
     *
     * @param pitch the pitch of the note in Hz.
     * @param startTime the start time of the note in seconds.
     * @param endTime the end time of the note in seconds.
     * @param lyrics the lyrics associated with the note.
     */
    public NoteDTO(float pitch, double startTime, double endTime, String lyrics) {
        this.pitch = pitch;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lyrics = lyrics;
        this.duration = endTime - startTime;  // Calculate duration based on start and end times
    }

    // Getters and setters

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public double getDuration() {
        return duration;
    }

    // Note: There's typically no need to set duration explicitly in most cases,
    // as it is derived from start and end times. However, if there's a need to adjust
    // duration independently, you can uncomment the setter below.
    /*
    public void setDuration(double duration) {
        this.duration = duration;
    }
    */
}