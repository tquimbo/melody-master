
// import org.junit.Test;
// import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
// import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.List;
// import com.melodymaster.melodymaster.service.AudioFileService;
// import com.melodymaster.melodymaster.dto.Note;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AudioFileServiceTest {

  @Autowired
  private AudioFileService audioFileService;

  @Test
  public void testAnalyzeFile() throws IOException, UnsupportedAudioFileException {
    File audioFile = new File("test_audio_file.wav");
    List<Note> notes = audioFileService.analyzeFile(audioFile);
    assertNotNull(notes);
    assertFalse(notes.isEmpty());
    // TODO: add more assertions to validate the output
  }

  @Test
  public void testExtractLyrics() {
    byte[] audioBytes = {0x52, 0x49, 0x46, 0x46, 0x24, 0x08, 0x00, 0x00, 0x57, 0x41, 0x56, 0x45, 0x66, 0x6d, 0x74, 0x20, 0x10, 0x00, 0x00, 0x00, 0x01, 0x00, 0x02, 0x00, 0x22, 0x56, 0x00, 0x00, 0x88, 0x58, 0x01, 0x00, 0x04, 0x00, 0x10, 0x00, 0x64, 0x61, 0x74, 0x61, 0x00, 0x08, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10};
    String lyrics = audioFileService.extractLyrics(audioBytes, 44, 48);
    assertEquals("test", lyrics);
  }

  @Test
  public void testGetAveragePitch() {
    double[] pitches = {0.0, 0.0, 440.0, 440.0, 440.0, 0.0, 0.0};
    double pitch = audioFileService.getAveragePitch(pitches, 2, 4);
    assertEquals(440.0, pitch, 0.001);
  }

}