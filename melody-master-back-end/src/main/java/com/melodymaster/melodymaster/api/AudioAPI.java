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
import java.util.List;
import com.melodymaster.melodymaster.dto.NoteDTO;


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
    
    // Validate the incoming file
    if (audioFile.isEmpty()) {
        logger.warn("Received empty audio file.");
        return ResponseEntity.badRequest().body("No file received.");
    }
    
    try {
        logger.debug("Starting to process the audio file.");
        
        // Process the file
        List<NoteDTO> noteDTOs = audioProcessingService.saveFile(audioFile);
        
        logger.info("Audio file processed successfully with {} notes", noteDTOs.size());
        return ResponseEntity.ok("Audio processed successfully: " + noteDTOs.size() + " notes processed.");
        
    } catch (UnsupportedAudioFileException e) {
        logger.error("Unsupported audio file format: {}", audioFile.getOriginalFilename(), e);
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                             .body("Unsupported audio format: " + e.getMessage());
    } catch (IOException e) {
        logger.error("I/O error while processing audio file: {}", audioFile.getOriginalFilename(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("I/O error processing audio: " + e.getMessage());
    } catch (Exception e) {
        logger.error("Unexpected error during audio processing: {}", audioFile.getOriginalFilename(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Unexpected error: " + e.getMessage());
    }
}
    }

//     @PostMapping("/upload")
// public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile audioFile) {
//     logger.info("Received audio file: {}", audioFile.getOriginalFilename());
//     logger.debug("Received file: {}", file.getOriginalFilename());
//     try {
//         logger.debug("Starting to process the audio file.");
//         List<NoteDTO> noteDTOs = audioProcessingService.saveFile(audioFile);
//         logger.info("Audio file processed successfully with {} notes", noteDTOs.size());
//         return ResponseEntity.ok().body("Audio processed successfully: " + noteDTOs.size() + " notes processed.");
//     } catch (UnsupportedAudioFileException | IOException e) {
//         logger.error("Error processing audio file", e);
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
//     } catch (Exception e) {
//         logger.error("Unexpected error during audio processing", e);
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
//     }
// }
    // public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile audioFile) {
    //     logger.info("Received audio file: {}", audioFile.getOriginalFilename());
    //     try {
    //         logger.debug("Starting to process the audio file.");
    //         audioProcessingService.analyzeFile(audioFile);
    //         logger.info("Audio file processed successfully.");
    //         return ResponseEntity.ok().body("Audio processed successfully");
    //     } catch (UnsupportedAudioFileException e) {
    //         logger.error("Unsupported audio file error", e);
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: Unsupported file type.");
    //     } catch (IOException e) {
    //         logger.error("I/O error while processing audio", e);
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: I/O error.");
    //     } catch (Exception e) {
    //         logger.error("General error while processing audio", e);
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
    //     }
    // }
