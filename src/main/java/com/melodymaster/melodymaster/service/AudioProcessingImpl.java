package com.melodymaster.melodymaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.melodymaster.melodymaster.repository.NoteRepository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
// import be.tarsos.dsp.AudioFloatConverter;
// import be.tarsos.dsp.pitch.PitchDetector;
// import be.tarsos.dsp.pitch.Yin;
import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;


import com.melodymaster.melodymaster.dto.NoteDTO;
import com.melodymaster.melodymaster.entity.Note;



@Service
public class AudioProcessingImpl implements AudioProcessingService {

  @Autowired
  private NoteRepository noteRepository;


  private static final double PITCH_THRESHOLD = 0.2; // adjust as needed

  
@Override
public List<NoteDTO> saveFile(File audioFile) throws IOException, UnsupportedAudioFileException {
  // Analyze the audio file and create a list of Note objects
  List<Note> notes = new ArrayList<>();
  // ...

  // Convert the list of Note objects to NoteDTO objects
  List<NoteDTO> noteDTOs = new ArrayList<>();
  for (Note note : notes) {
      NoteDTO noteDTO = new NoteDTO();
      noteDTO.setPitch(note.getPitch());
      noteDTO.setDuration(note.getDuration());
      noteDTO.setLyrics(note.getLyrics());
      noteDTOs.add(noteDTO);
  }

  // Convert the list of NoteDTO objects to Note entity objects
  List<Note> noteEntities = noteDTOs.stream().map(this::toEntity).collect(Collectors.toList());

  // Save the list of Note entity objects to the database
  noteRepository.saveAll(noteEntities);

  return noteDTOs;
}


private Note toEntity(NoteDTO noteDTO) {
  Note note = new Note();
  note.setPitch(noteDTO.getPitch());
  note.setDuration(noteDTO.getDuration());
  return note;
}

public List<Note> analyzeFile(File audioFile) throws UnsupportedAudioFileException, IOException {
  List<Note> notes = new ArrayList<>();
  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
  AudioFormat format = audioInputStream.getFormat();
  int sampleRate = (int) format.getSampleRate();
  int sampleSizeInBits = format.getSampleSizeInBits();
  int channels = format.getChannels();
  boolean isBigEndian = format.isBigEndian();
  byte[] audioBytes = audioInputStream.readAllBytes();

  PitchEstimationAlgorithm pitchEstimationAlgorithm = PitchEstimationAlgorithm.YIN;
  int bufferSize = 1024;
  int overlap = 0;
  PitchDetectionHandler pitchDetectionHandler = new PitchDetectionHandler() {
      double timestamp = 0;

      @Override
      public void handlePitch(PitchDetectionResult pitchDetectionResult) {
          double timestampIncrement = (double) bufferSize / (double) sampleRate;
          if (pitchDetectionResult.getPitch() > 0) {
              if (notes.isEmpty() || timestamp - notes.get(notes.size() - 1).getEndTime() >= 0.2) {
                  Note note = new Note();
                  note.setPitch(pitchDetectionResult.getPitch());
                  note.setStartTime(timestamp);
                  note.setEndTime(timestamp + timestampIncrement);
                  notes.add(note);
              } else {
                  Note note = notes.get(notes.size() - 1);
                  note.setPitch((note.getPitch() + pitchDetectionResult.getPitch()) / 2);
                  note.setEndTime(timestamp + timestampIncrement);
              }
          }
          timestamp += timestampIncrement;
      }
  };

  AudioDispatcher dispatcher = AudioDispatcherFactory.fromByteArray(audioBytes, sampleRate, bufferSize, overlap);
  dispatcher.addAudioProcessor(new PitchProcessor(pitchEstimationAlgorithm, sampleRate, bufferSize, pitchDetectionHandler));
  dispatcher.run();

  return notes;
}

  AudioDispatcher dispatcher = AudioDispatcherFactory.fromByteArray(audioBytes, sampleRate, bufferSize, overlap);
  dispatcher.addAudioProcessor(new PitchProcessor(pitchEstimationAlgorithm, sampleRate, bufferSize, pitchDetectionHandler));
  dispatcher.run();

  return notes;
}

// PitchDetectionHandler pitchDetectionHandler = new PitchDetectionHandler() {
//   double timestamp = 0;

//   @Override
//   public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
//       double timestampIncrement = (double) bufferSize / (double) sampleRate;
//       if (pitchDetectionResult.getPitch() > 0) {
//           if (notes.isEmpty() || timestamp - notes.get(notes.size() - 1).getEndTime() >= 0.2) {
//               Note note = new Note();
//               note.setPitch(pitchDetectionResult.getPitch());
//               note.setStartTime(timestamp);
//               note.setEndTime(timestamp + timestampIncrement);
//               notes.add(note);
//           } else {
//               Note note = notes.get(notes.size() - 1);
//               note.setPitch((note.getPitch() + pitchDetectionResult.getPitch()) / 2);
//               note.setEndTime(timestamp + timestampIncrement);
//           }
//       }
//       timestamp += timestampIncrement;
//   }
// };
  
  // @Override
  // public List<Note> analyzeFile(File audioFile) throws IOException, UnsupportedAudioFileException {
  //   List<Note> notes = new ArrayList<>();
  //   AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
  //   AudioFormat format = audioInputStream.getFormat();
  //   float sampleRate = format.getSampleRate();
  //   float frameRate = format.getFrameRate();
  //   int channels = format.getChannels();
  //   int sampleSizeInBits = format.getSampleSizeInBits();
  //   boolean isSigned = format.isSigned();
  //   boolean isBigEndian = format.isBigEndian();
  //   byte[] audioBytes = audioInputStream.readAllBytes();
  //   float[] audioData = AudioFloatConverter.getConverter(format).toFloatArray(audioBytes);

  //   PitchDetector detector = new Yin(sampleRate, audioData.length);
  //   detector.setThreshold(PITCH_THRESHOLD);

  //   double[] pitches = new double[audioData.length / channels];
  //   for (int i = 0; i < audioData.length; i += channels) {
  //     float[] frame = new float[channels];
  //     for (int j = 0; j < channels; j++) {
  //       frame[j] = audioData[i + j];
  //     }
  //     double pitch = detector.getPitch(frame);
  //     pitches[i / channels] = pitch;
  //   }

  //   int noteStart = 0;
  //   double previousPitch = 0;
  //   for (int i = 0; i < pitches.length; i++) {
  //     if (pitches[i] > 0) {
  //       if (previousPitch == 0) {
  //         noteStart = i;
  //       }
  //     } else {
  //       if (previousPitch > 0) {
  //         double pitch = getAveragePitch(pitches, noteStart, i - 1);
  //         double duration = (i - noteStart) * frameRate / sampleRate;
  //         String lyrics = extractLyrics(audioBytes, noteStart * channels * sampleSizeInBits / 8, i * channels * sampleSizeInBits / 8);
  //         Note note = new Note();
  //         note.setPitch(pitch);
  //         note.setDuration(duration);
  //         note.setLyrics(lyrics);
  //         notes.add(note);
  //       }
  //     }
  //     previousPitch = pitches[i];
  //   }

  //   return notes;
  // }

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

