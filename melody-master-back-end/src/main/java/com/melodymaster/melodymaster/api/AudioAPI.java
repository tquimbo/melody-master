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
// @CrossOrigin
// public class AudioAPI {

//     private final AudioProcessingService audioProcessingService; // Changed variable name to lowercase

//     @Autowired
//     public AudioAPI(AudioProcessingServiceImpl audioProcessingServiceImpl) {
//         this.audioProcessingService = audioProcessingServiceImpl; // Changed variable name to lowercase
//     }

//     @GetMapping("/upload")
//     public ResponseEntity<?> getUploadPage() {
//     // Return some response, for example, a view name, a message, or data
//     return ResponseEntity.ok().body("This is the audio upload page.");
//     }

//     @PostMapping("/upload")
//     public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile audioFile) {
//         try {
//             // Call the audioService to process the audio file
//             audioProcessingService.analyzeFile(audioFile); // Updated variable name to lowercase
//             return ResponseEntity.ok().body("Audio processed successfully");
//         } catch (UnsupportedAudioFileException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
//         } catch (IOException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
//         }
//     }
// }

package com.melodymaster.melodymaster.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.melodymaster.melodymaster.service.AudioProcessingServiceImpl;
import com.melodymaster.melodymaster.service.AudioProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/audio")
@CrossOrigin
public class AudioAPI {

    private static final Logger logger = LoggerFactory.getLogger(AudioAPI.class);
    private final AudioProcessingService audioProcessingService;

    @Autowired
    public AudioAPI(AudioProcessingServiceImpl audioProcessingServiceImpl) {
        this.audioProcessingService = audioProcessingServiceImpl;
    }

    @GetMapping("/upload")
    public ResponseEntity<?> getUploadPage() {
        logger.info("Accessing the audio upload page.");
        return ResponseEntity.ok().body("This is the audio upload page.");
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile audioFile) {
        logger.info("Received audio file: {}", audioFile.getOriginalFilename());
        try {
            logger.debug("Starting to process the audio file.");
            audioProcessingService.analyzeFile(audioFile);
            logger.info("Audio file processed successfully.");
            return ResponseEntity.ok().body("Audio processed successfully");
        } catch (UnsupportedAudioFileException e) {
            logger.error("Unsupported audio file error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: Unsupported file type.");
        } catch (IOException e) {
            logger.error("I/O error while processing audio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: I/O error.");
        } catch (Exception e) {
            logger.error("General error while processing audio", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
        }
    }
}