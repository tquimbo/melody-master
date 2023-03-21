import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.melodymaster.melodymaster.service.AudioProcessingServiceImpl;
import com.melodymaster.melodymaster.service.AudioProcessingService;
import org.springframework.http.ResponseEntity;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.springframework.http.HttpStatus;
import java.io.IOException;

@RestController
@RequestMapping("/api/audio")
public class AudioAPI {

    private final AudioProcessingServiceImpl AudioProcessingServiceImpl;
    private final AudioProcessingService AudioProcessingService;


    public AudioAPI(AudioProcessingServiceImpl AudioProcessingServiceImpl) {
        this.AudioProcessingServiceImpl = AudioProcessingServiceImpl;
        this.AudioProcessingService = AudioProcessingServiceImpl;
    }
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
@PostMapping("/upload")
public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile audioFile) {
    try {
        // Call the audioService to process the audio file
        AudioProcessingServiceImpl.analyzeFile(audioFile);
        return ResponseEntity.ok().body("Audio processed successfully");
    } catch (UnsupportedAudioFileException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing audio: " + e.getMessage());
    }
}
}