import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/audio")
public class AudioAPI {

    private final AudioProcessingServiceImpl AudioProcessingServiceImpl;

    public AudioAPI(AudioProcessingServiceImpl AudioProcessingServiceImpl) {
        this.AudioProcessingServiceImpl = AudioProcessingServiceImpl;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAudio(@RequestParam("file") MultipartFile file) {
        // Call the audioService to process the audio file
        audioService.processAudio(file);

        return ResponseEntity.ok().body("Audio processed successfully");
    }
}