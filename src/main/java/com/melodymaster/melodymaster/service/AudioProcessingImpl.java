package com.melodymaster.melodymaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.melodymaster.melodymaster.repository.NoteRepository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

import com.melodymaster.melodymaster.dto.NotesDTO;


@Service
public class AudioProcessingImpl implements AudioFileService {

  @Autowired
  private NoteRepository noteRepository;


  private static final double PITCH_THRESHOLD = 0.2; // adjust as needed

  @Override
  public List<NotesDTO> saveFile(File audioFile) throws IOException, UnsupportedAudioFileException {
    // Analyze the audio file and create a list of Note objects
    List<Note> notes = new ArrayList<>();
    // ...

    // Save the list of notes to the database
    List<Note> noteEntities = new ArrayList<>();
    for (Note note : notes) {
      NoteEntity noteEntity = new NoteEntity();
      noteEntity.setPitch(note.getPitch());
      noteEntity.setDuration(note.getDuration());
      noteEntity.setLyrics(note.getLyrics());
      noteEntities.add(noteEntity);
    }
    noteRepository.saveAll(noteEntities);

    return notes;
  }

  
  @Override
  public List<Note> analyzeFile(File audioFile) throws IOException, UnsupportedAudioFileException {
    List<Note> notes = new ArrayList<>();
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
    AudioFormat format = audioInputStream.getFormat();
    float sampleRate = format.getSampleRate();
    float frameRate = format.getFrameRate();
    int channels = format.getChannels();
    int sampleSizeInBits = format.getSampleSizeInBits();
    boolean isSigned = format.isSigned();
    boolean isBigEndian = format.isBigEndian();
    byte[] audioBytes = audioInputStream.readAllBytes();
    float[] audioData = AudioFloatConverter.getConverter(format).toFloatArray(audioBytes);

    PitchDetector detector = new Yin(sampleRate, audioData.length);
    detector.setThreshold(PITCH_THRESHOLD);

    double[] pitches = new double[audioData.length / channels];
    for (int i = 0; i < audioData.length; i += channels) {
      float[] frame = new float[channels];
      for (int j = 0; j < channels; j++) {
        frame[j] = audioData[i + j];
      }
      double pitch = detector.getPitch(frame);
      pitches[i / channels] = pitch;
    }

    int noteStart = 0;
    double previousPitch = 0;
    for (int i = 0; i < pitches.length; i++) {
      if (pitches[i] > 0) {
        if (previousPitch == 0) {
          noteStart = i;
        }
      } else {
        if (previousPitch > 0) {
          double pitch = getAveragePitch(pitches, noteStart, i - 1);
          double duration = (i - noteStart) * frameRate / sampleRate;
          String lyrics = extractLyrics(audioBytes, noteStart * channels * sampleSizeInBits / 8, i * channels * sampleSizeInBits / 8);
          Note note = new Note();
          note.setPitch(pitch);
          note.setDuration(duration);
          note.setLyrics(lyrics);
          notes.add(note);
        }
      }
      previousPitch = pitches[i];
    }

    return notes;
  }

  private double getAveragePitch(double[] pitches, int startIndex, int endIndex) {
    double sum = 0;
    int count = 0;
    for (int i = startIndex; i <= endIndex; i++) {
      if (pitches[i] > 0) {
        sum += pitches[i];
        count++;
      }
    }
    return sum / count;
  }

  private String extractLyrics(byte[] audioBytes, int startIndex, int endIndex) {
    // TODO: implement lyrics extraction algorithm
    return "";
  }

}

