package com.melodymaster.melodymaster.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.melodymaster.melodymaster.service.AudioProcessingServiceImpl;
import com.melodymaster.melodymaster.service.AudioProcessingService;
import org.springframework.http.ResponseEntity;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/audio")
public class AudioAPI {

    private final AudioProcessingService audioProcessingService; // Changed variable name to lowercase

    @Autowired
    public AudioAPI(AudioProcessingServiceImpl audioProcessingServiceImpl) {
        this.audioProcessingService = audioProcessingServiceImpl; // Changed variable name to lowercase
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile audioFile) {
        try {
            // Call the audioService to process the audio file
            audioProcessingService.analyzeFile(audioFile); // Updated variable name to lowercase
            return ResponseEntity.ok().body("Audio processed successfully");
        } catch (UnsupportedAudioFileException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
        }
    }
}
// package com.melodymaster.melodymaster.api;

// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;
// import com.melodymaster.melodymaster.service.AudioProcessingServiceImpl;
// import com.melodymaster.melodymaster.service.AudioProcessingService;
// import org.springframework.http.ResponseEntity;
// import javax.sound.sampled.UnsupportedAudioFileException;
// import org.springframework.http.HttpStatus;
// import java.io.IOException;
// import org.springframework.beans.factory.annotation.Autowired;

// @RestController
// @RequestMapping("/api/audio")
// public class AudioAPI {

// //    @Autowired

// //     private final AudioProcessingServiceImpl AudioProcessingServiceImpl;
 
//     private final AudioProcessingService AudioProcessingService;

//     @Autowired
//     public AudioAPI(AudioProcessingServiceImpl AudioProcessingServiceImpl) {
//         this.AudioProcessingService = AudioProcessingServiceImpl;
//     }

//     @PostMapping("/upload")
//     public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile audioFile) {
//         try {
//             // Call the audioService to process the audio file
//             AudioProcessingService.analyzeFile(audioFile); // Change this line
//             return ResponseEntity.ok().body("Audio processed successfully");
//         } catch (UnsupportedAudioFileException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
//         } catch (IOException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
//         }
//     }
    
// }


    // @PostMapping("/upload")
    // public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile file) {
    //     // Call the audioService to process the audio file
    //     AudioProcessingServiceImpl.processAudio(file);

    //     return ResponseEntity.ok().body("Audio processed successfully");
    // }
//     @PostMapping("/upload")
//     public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile audioFile) {
//     // Call the audioService to process the audio file
//     AudioProcessingServiceImpl.analyzeFile(audioFile);

//     return ResponseEntity.ok().body("Audio processed successfully");
// }