// package com.melodymaster.melodymaster.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;
// import com.melodymaster.melodymaster.repository.NoteRepository;
// import java.io.File;
// import java.io.IOException;
// import java.io.InputStream;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;
// // import com.melodymaster.melodymaster.exception.UnsupportedAudioFileException;


// // import javax.sound.sampled.UnsupportedAudioFileException;
// import javax.sound.sampled.AudioFormat;
// import javax.sound.sampled.AudioInputStream;
// import javax.sound.sampled.AudioSystem;
// // import javax.sound.sampled.UnsupportedFileException;
// import javax.sound.sampled.UnsupportedAudioFileException;




// // import be.tarsos.dsp.AudioFloatConverter;
// // import be.tarsos.dsp.pitch.PitchDetector;
// // import be.tarsos.dsp.pitch.Yin;
// import be.tarsos.dsp.AudioDispatcher;
// import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
// import be.tarsos.dsp.pitch.PitchDetectionHandler;
// import be.tarsos.dsp.pitch.PitchDetectionResult;
// import be.tarsos.dsp.pitch.PitchProcessor;
// import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;
// import be.tarsos.dsp.AudioEvent;


// import com.melodymaster.melodymaster.dto.NoteDTO;
// import com.melodymaster.melodymaster.entity.Note;



// @Service
// public class AudioProcessingServiceImpl implements AudioProcessingService {

//   @Autowired
//   private NoteRepository noteRepository;


//   private static final double PITCH_THRESHOLD = 0.2; // adjust as needed
  

  
// @Override
// public List<NoteDTO> saveFile(MultipartFile  audioFile) throws IOException, UnsupportedAudioFileException {
//   // Analyze the audio file and create a list of Note objects
//   List<Note> notes = new ArrayList<>();
//   // ...
  

//   // Convert the list of Note objects to NoteDTO objects
//   List<NoteDTO> noteDTOs = new ArrayList<>();
//   for (Note note : notes) {
//       NoteDTO noteDTO = new NoteDTO();
//       noteDTO.setPitch(note.getPitch());
//       noteDTO.setDuration(note.getDuration());
//       noteDTO.setLyrics(note.getLyrics());
//       noteDTOs.add(noteDTO);
//   }

//   // Convert the list of NoteDTO objects to Note entity objects
//   List<Note> noteEntities = noteDTOs.stream().map(this::toEntity).collect(Collectors.toList());

//   // Save the list of Note entity objects to the database
//   noteRepository.saveAll(noteEntities);

//   return noteDTOs;
// }

// public List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes) {
//   List<NoteDTO> noteDTOs = new ArrayList<>();
//   for (Note note : notes) {
//       NoteDTO noteDTO = new NoteDTO();
//       noteDTO.setPitch(note.getPitch());
//       noteDTO.setDuration(note.getDuration());
//       noteDTO.setLyrics(note.getLyrics());
//       noteDTOs.add(noteDTO);
//   }
//   return noteDTOs;
// }


// public Note toEntity(NoteDTO noteDTO) {
//   Note note = new Note();
//   note.setPitch(noteDTO.getPitch());
//   note.setDuration(noteDTO.getDuration());
//   return note;
// }

// public List<Note> analyzeFile(MultipartFile audioFile) throws UnsupportedAudioFileException, IOException {
  
//   // List<Note> notes = new ArrayList<>();
//   // AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
//   // AudioFormat format = audioInputStream.getFormat();
//   // int sampleRate = (int) format.getSampleRate();
//   // int sampleSizeInBits = format.getSampleSizeInBits();
//   // int channels = format.getChannels();
//   // boolean isBigEndian = format.isBigEndian();
//   // byte[] audioBytes = audioInputStream.readAllBytes();
//   List<Note> notes = new ArrayList<>();
//   InputStream inputStream = audioFile.getInputStream();
//   AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
//   AudioFormat format = audioInputStream.getFormat();
//   int sampleRate = (int) format.getSampleRate();
//   int sampleSizeInBits = format.getSampleSizeInBits();
//   int channels = format.getChannels();
//   boolean isBigEndian = format.isBigEndian();
//   byte[] audioBytes = audioInputStream.readAllBytes();

//   PitchEstimationAlgorithm pitchEstimationAlgorithm = PitchEstimationAlgorithm.YIN;
//   int bufferSize = 1024;
//   int overlap = 0;
//   PitchDetectionHandler pitchDetectionHandler = new PitchDetectionHandler() {
//       double timestamp = 0;

     
//     @Override
//     public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
//         double timestampIncrement = (double) bufferSize / (double) sampleRate;
//         if (pitchDetectionResult.getPitch() > 0) {
//             if (notes.isEmpty() || timestamp - notes.get(notes.size() - 1).getEndTime() >= 0.2) {
//                 Note note = new Note();
//                 note.setPitch(pitchDetectionResult.getPitch());
//                 note.setStartTime(timestamp);
//                 note.setEndTime(timestamp + timestampIncrement);
//                 notes.add(note);
//             } else {
//                 Note note = notes.get(notes.size() - 1);
//                 note.setPitch((note.getPitch() + pitchDetectionResult.getPitch()) / 2);
//                 note.setEndTime(timestamp + timestampIncrement);
//             }
//         }
//         timestamp += timestampIncrement;
//     }
// };

//   AudioDispatcher dispatcher = AudioDispatcherFactory.fromByteArray(audioBytes, format, bufferSize, overlap);
//   dispatcher.addAudioProcessor(new PitchProcessor(pitchEstimationAlgorithm, sampleRate, bufferSize, pitchDetectionHandler));
//   dispatcher.run();

//   return notes;
// }


//   private double getAveragePitch(double[] pitches, int startIndex, int endIndex) {
//     double sum = 0;
//     int count = 0;
//     for (int i = startIndex; i <= endIndex; i++) {
//       if (pitches[i] > 0) {
//         sum += pitches[i];
//         count++;
//       }
//     }
//     return sum / count;
//   }

//   private String extractLyrics(byte[] audioBytes, int startIndex, int endIndex) {
//     // TODO: implement lyrics extraction algorithm
//     return "";
//   }

// }

// package com.melodymaster.melodymaster.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;
// import com.melodymaster.melodymaster.repository.NoteRepository;
// import com.melodymaster.melodymaster.entity.Note;
// import com.melodymaster.melodymaster.dto.NoteDTO;
// import javax.sound.sampled.*;
// import java.io.*;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;
// import be.tarsos.dsp.AudioDispatcher;
// import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
// import be.tarsos.dsp.pitch.PitchDetectionHandler;
// import be.tarsos.dsp.pitch.PitchDetectionResult;
// import be.tarsos.dsp.pitch.PitchProcessor;
// import be.tarsos.dsp.AudioEvent;

// @Service
// public class AudioProcessingServiceImpl implements AudioProcessingService {

//     @Autowired
//     private NoteRepository noteRepository;

//     @Override
//     public List<NoteDTO> analyzeFile(MultipartFile audioFile) throws UnsupportedAudioFileException, IOException {
//         List<Note> notes = new ArrayList<>();
//         InputStream inputStream = new BufferedInputStream(audioFile.getInputStream());
//         inputStream.mark(Integer.MAX_VALUE);
//         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
//         AudioFormat format = audioInputStream.getFormat();
//         int sampleRate = (int) format.getSampleRate();
//         byte[] audioBytes = audioInputStream.readAllBytes();

//         PitchDetectionHandler pitchDetectionHandler = (pitchDetectionResult, audioEvent) -> {
//             if (pitchDetectionResult.isPitched()) {
//                 Note note = new Note();
//                 note.setPitch(pitchDetectionResult.getPitch());
//                 note.setStartTime(audioEvent.getTimeStamp() - audioEvent.getDuration());
//                 note.setEndTime(audioEvent.getTimeStamp());
//                 notes.add(note);
//             }
//         };

//         AudioDispatcher dispatcher = AudioDispatcherFactory.fromByteArray(audioBytes, format, 1024, 0);
//         dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, 1024, pitchDetectionHandler));
//         dispatcher.run();

//         List<NoteDTO> noteDTOs = notes.stream()
//                                       .map(note -> new NoteDTO(note.getPitch(), note.getDuration(), note.getLyrics()))
//                                       .collect(Collectors.toList());

//         noteRepository.saveAll(notes);
//         return noteDTOs;
//     }

//     private Note toEntity(NoteDTO noteDTO) {
//         Note note = new Note();
//         note.setPitch(noteDTO.getPitch());
//         note.setDuration(noteDTO.getDuration());
//         note.setLyrics(noteDTO.getLyrics());
//         return note;
//     }

//     public NoteDTO toDTO(Note note) {
//         return new NoteDTO(note.getPitch(), note.getDuration(), note.getLyrics());
//     }
// }
package com.melodymaster.melodymaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.melodymaster.melodymaster.repository.NoteRepository;
import com.melodymaster.melodymaster.entity.Note;
import com.melodymaster.melodymaster.dto.NoteDTO;
import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.AudioEvent;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.converter.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AudioProcessingServiceImpl implements AudioProcessingService {

    private static final Logger logger = LoggerFactory.getLogger(AudioProcessingServiceImpl.class);

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void convertMp3ToWav(String sourcePath, String destPath) throws JavaLayerException {
        logger.info("Converting MP3 to WAV: {} to {}", sourcePath, destPath);
        Converter converter = new Converter();
        converter.convert(sourcePath, destPath);
        logger.info("Conversion complete.");
    }

    @Override
    public List<NoteDTO> saveFile(MultipartFile audioFile) throws IOException, UnsupportedAudioFileException {
        logger.info("Starting file analysis for {}", audioFile.getOriginalFilename());
        List<Note> notes = analyzeFile(audioFile);
        logger.info("File analysis complete, found {} notes.", notes.size());

        if (!notes.isEmpty()) {
            logger.info("Saving notes to database...");
            noteRepository.saveAll(notes);
            logger.info("Notes saved to database successfully.");
        } else {
            logger.warn("No notes found to save to database.");
        }

        List<NoteDTO> noteDTOs = convertNotesToNoteDTOs(notes);
        return noteDTOs;
    }

    @Override
    public List<Note> analyzeFile(MultipartFile audioFile) throws UnsupportedAudioFileException, IOException {
        logger.info("Analyzing audio file...");
        List<Note> notes = new ArrayList<>();
        InputStream inputStream = new BufferedInputStream(audioFile.getInputStream());
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
        AudioFormat format = audioInputStream.getFormat();
        int sampleRate = (int) format.getSampleRate();
        byte[] audioBytes = audioInputStream.readAllBytes();
        logger.info("Audio stream read with sample rate: {}", sampleRate);

        PitchDetectionHandler pitchDetectionHandler = (pitchDetectionResult, audioEvent) -> {
            if (pitchDetectionResult.getPitch() > 0) {
                Note note = new Note();
                note.setPitch(pitchDetectionResult.getPitch());
                note.setStartTime(audioEvent.getTimeStamp() - (double) audioEvent.getBufferSize() / audioEvent.getSampleRate());
                note.setEndTime(audioEvent.getTimeStamp());
                notes.add(note);
                logger.debug("Detected note: Pitch = {}, Start Time = {}, End Time = {}",
                             pitchDetectionResult.getPitch(), note.getStartTime(), note.getEndTime());
            }
        };

        AudioDispatcher dispatcher = AudioDispatcherFactory.fromByteArray(audioBytes, format, 1024, 0);
        dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, 1024, pitchDetectionHandler));
        dispatcher.run();
        logger.info("Audio processing completed. Total notes detected: {}", notes.size());

        return notes;
    }

    @Override
    public List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes) {
        logger.info("Converting notes to DTOs...");
        List<NoteDTO> dtos = notes.stream()
                                  .map(note -> new NoteDTO(note.getPitch(), note.getDuration(), 0, note.getLyrics()))
                                  .collect(Collectors.toList());
        logger.info("Conversion to DTOs complete. Total DTOs created: {}", dtos.size());
        return dtos;
    }

    @Override
    public Note toEntity(NoteDTO noteDTO) {
        Note note = new Note();
        note.setPitch(noteDTO.getPitch());
        note.setDuration(noteDTO.getDuration());
        note.setLyrics(noteDTO.getLyrics());
        logger.info("Converted DTO to entity: Pitch = {}, Duration = {}", note.getPitch(), note.getDuration());
        return note;
    }
}


// @Service
// public class AudioProcessingServiceImpl implements AudioProcessingService {

//     @Autowired
//     private NoteRepository noteRepository;

//     @Override
//     public void convertMp3ToWav(String sourcePath, String destPath) throws JavaLayerException {
//         Converter converter = new Converter();
//         converter.convert(sourcePath, destPath);
//     }

//     @Override
// public List<NoteDTO> saveFile(MultipartFile audioFile) throws IOException, UnsupportedAudioFileException {
//     logger.info("Starting file analysis for {}", audioFile.getOriginalFilename());
//     List<Note> notes = analyzeFile(audioFile);  // Analyze the file to get notes
//     logger.info("File analysis complete, found {} notes", notes.size());

//     if (!notes.isEmpty()) {
//         logger.info("Saving notes to database...");
//         noteRepository.saveAll(notes);  // Persist the notes
//         logger.info("Notes saved to database successfully.");
//     } else {
//         logger.warn("No notes found to save to database.");
//     }

//     List<NoteDTO> noteDTOs = convertNotesToNoteDTOs(notes);  // Convert notes to DTOs
//     return noteDTOs;  // Return the DTOs
// }
//     // public List<NoteDTO> saveFile(MultipartFile audioFile) throws IOException, UnsupportedAudioFileException {
//     //     List<Note> notes = analyzeFile(audioFile);  // Analyze the file to get notes
//     //     List<NoteDTO> noteDTOs = convertNotesToNoteDTOs(notes);  // Convert notes to DTOs
//     //     noteRepository.saveAll(notes);  // Persist the notes
//     //     return noteDTOs;  // Return the DTOs
//     // }

//     @Override
// public List<Note> analyzeFile(MultipartFile audioFile) throws UnsupportedAudioFileException, IOException {
//     List<Note> notes = new ArrayList<>();
//     InputStream inputStream = new BufferedInputStream(audioFile.getInputStream());
//     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
//     AudioFormat format = audioInputStream.getFormat();
//     int sampleRate = (int) format.getSampleRate();
//     byte[] audioBytes = audioInputStream.readAllBytes();

//     PitchDetectionHandler pitchDetectionHandler = (pitchDetectionResult, audioEvent) -> {
//         // Calculate the duration of the audio buffer in seconds
//         double durationInSeconds = (double) audioEvent.getBufferSize() / audioEvent.getSampleRate();

//         if (pitchDetectionResult.getPitch() > 0) {
//             Note note = new Note();
//             note.setPitch(pitchDetectionResult.getPitch());
//             // Use the duration to calculate start and end times correctly
//             note.setStartTime(audioEvent.getTimeStamp() - durationInSeconds);
//             note.setEndTime(audioEvent.getTimeStamp());
//             notes.add(note);
//         }
//     };

//     AudioDispatcher dispatcher = AudioDispatcherFactory.fromByteArray(audioBytes, format, 1024, 0);
//     dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, 1024, pitchDetectionHandler));
//     dispatcher.run();

//     return notes;
// }

//     // @Override
//     // public List<Note> analyzeFile(MultipartFile audioFile) throws UnsupportedAudioFileException, IOException {
//     //     List<Note> notes = new ArrayList<>();
//     //     InputStream inputStream = new BufferedInputStream(audioFile.getInputStream());
//     //     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
//     //     AudioFormat format = audioInputStream.getFormat();
//     //     int sampleRate = (int) format.getSampleRate();
//     //     byte[] audioBytes = audioInputStream.readAllBytes();

//     //     PitchDetectionHandler pitchDetectionHandler = (pitchDetectionResult, audioEvent) -> {
//     //         if (pitchDetectionResult.getPitch() > 0) {
//     //             Note note = new Note();
//     //             note.setPitch(pitchDetectionResult.getPitch());
//     //             note.setStartTime(audioEvent.getTimeStamp() - audioEvent.getDuration());
//     //             note.setEndTime(audioEvent.getTimeStamp());
//     //             notes.add(note);
//     //         }
//     //     };

//     //     AudioDispatcher dispatcher = AudioDispatcherFactory.fromByteArray(audioBytes, format, 1024, 0);
//     //     dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, 1024, pitchDetectionHandler));
//     //     dispatcher.run();

//     //     return notes;
//     // }

//     @Override
//     public List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes) {
//         return notes.stream()
//                     .map(note -> new NoteDTO(note.getPitch(), note.getDuration().doubleValue(), 0, note.getLyrics()))
//                     .collect(Collectors.toList());
//     }

//     @Override
//     public Note toEntity(NoteDTO noteDTO) {
//         Note note = new Note();
//         note.setPitch(noteDTO.getPitch());
//         note.setDuration(noteDTO.getDuration());
//         note.setLyrics(noteDTO.getLyrics());
//         return note;
//     }
// }