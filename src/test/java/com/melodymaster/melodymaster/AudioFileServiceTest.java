package com.melodymaster.melodymaster;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.List;

import com.melodymaster.melodymaster.service.AudioProcessingServiceImpl;
import com.melodymaster.melodymaster.dto.NoteDTO;
import com.melodymaster.melodymaster.entity.Note;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AudioFileServiceTest {

    @Autowired
    private AudioProcessingServiceImpl audioProcessingServiceImpl;

    @Test
    public void testAnalyzeFile() throws IOException, UnsupportedAudioFileException {
        File audioFile = new File("test_audio_file.wav");
        List<Note> notes = audioProcessingServiceImpl.analyzeFile(audioFile);
        assertNotNull(notes);
        assertFalse(notes.isEmpty());
        // TODO: add more assertions to validate the output
    }

  // public void testAnalyzeFile() throws IOException, UnsupportedAudioFileException {
  //   File audioFile = new File("test_audio_file.wav");
  //   List<NoteDTO> notes = AudioProcessingServiceImpl.analyzeFile(audioFile);
  //   assertNotNull(notes);
  //   assertFalse(notes.isEmpty());
  //   // TODO: add more assertions to validate the output
  // }

  // @Test
  // public void testExtractLyrics() {
  //   byte[] audioBytes = {0x52, 0x49, 0x46, 0x46, 0x24, 0x08, 0x00, 0x00, 0x57, 0x41, 0x56, 0x45, 0x66, 0x6d, 0x74, 0x20, 0x10, 0x00, 0x00, 0x00, 0x01, 0x00, 0x02, 0x00, 0x22, 0x56, 0x00, 0x00, 0x88, 0x58, 0x01, 0x00, 0x04, 0x00, 0x10, 0x00, 0x64, 0x61, 0x74, 0x61, 0x00, 0x08, 0x00, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10};
  //   String lyrics = AudioProcessingServiceImpl.extractLyrics(audioBytes, 44, 48);
  //   assertEquals("test", lyrics);
  // }

  // @Test
  // public void testGetAveragePitch() {
  //   double[] pitches = {0.0, 0.0, 440.0, 440.0, 440.0, 0.0, 0.0};
  //   double pitch = AudioProcessingServiceImpl.getAveragePitch(pitches, 2, 4);
  //   assertEquals(440.0, pitch, 0.001);
  // }

}