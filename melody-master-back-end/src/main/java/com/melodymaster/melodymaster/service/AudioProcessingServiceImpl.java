package com.melodymaster.melodymaster.service;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.melodymaster.melodymaster.dto.NoteDTO;
import com.melodymaster.melodymaster.entity.AudioFile;
import com.melodymaster.melodymaster.entity.Lyrics;
import com.melodymaster.melodymaster.entity.Note;
import com.melodymaster.melodymaster.repository.AudioFileRepository;
import com.melodymaster.melodymaster.repository.LyricsRepository;
import com.melodymaster.melodymaster.repository.NoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

@Transactional
@Service
public class AudioProcessingServiceImpl implements AudioProcessingService {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(AudioProcessingServiceImpl.class);

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private AudioFileRepository audioFileRepository;

    @Autowired
    private LyricsRepository lyricsRepository;

    private final Tika tika = new Tika(); // Apache Tika to detect the file format

    @Transactional
    @Override
    public List<NoteDTO> saveFile(MultipartFile audioFile) throws IOException, UnsupportedAudioFileException {
        logger.info("Starting file analysis for {}", audioFile.getOriginalFilename());

        // Detect the file type
        String mimeType = tika.detect(audioFile.getInputStream());
        logger.info("Detected MIME type: {}", mimeType);

        // Handle file processing based on MIME type
        List<Note> notes;
        if (mimeType.startsWith("audio/")) {
            notes = analyzeFile(audioFile);
        } else {
            throw new UnsupportedAudioFileException("Unsupported file format: " + mimeType);
        }

        logger.info("File analysis complete, found {} notes.", notes.size());

        // Save the audio file and notes
        AudioFile file = new AudioFile();
        file.setTitle(audioFile.getOriginalFilename());
        file.setNotes(notes);

        Lyrics lyrics = new Lyrics(); // Creating new lyrics instance

        // Persist the changes
        audioFileRepository.save(file);
        lyricsRepository.save(lyrics);

        if (!notes.isEmpty()) {
            noteRepository.saveAll(notes);
        }

        List<NoteDTO> noteDTOs = convertNotesToNoteDTOs(notes);
        return noteDTOs;
    }

    @Transactional
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
            }
        };

        AudioDispatcher dispatcher = AudioDispatcherFactory.fromByteArray(audioBytes, format, 1024, 0);
        dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, 1024, pitchDetectionHandler));
        dispatcher.run();

        logger.info("Audio processing completed. Total notes detected: {}", notes.size());
        return notes;
    }

    @Transactional
    @Override
    public List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes) {
        logger.info("Converting notes to DTOs...");
        List<NoteDTO> dtos = notes.stream()
                                  .map(note -> new NoteDTO(note.getPitch(), note.getDuration(), 0, note.getLyrics()))
                                  .collect(Collectors.toList());
        logger.info("Conversion to DTOs complete. Total DTOs created: {}", dtos.size());
        return dtos;
    }
}

    // @Transactional
    // @Override
    // public void convertMp3ToWav(String sourcePath, String destPath) throws JavaLayerException {
    //     logger.info("Converting MP3 to WAV: {} to {}", sourcePath, destPath);
    //     Converter converter = new Converter();
    //     converter.convert(sourcePath, destPath);
    //     logger.info("Conversion complete.");
    // }

    

// @Transactional
// @Override
// public List<NoteDTO> saveFile(MultipartFile audioFile) throws IOException, UnsupportedAudioFileException {
//     logger.info("Starting file analysis for {}", audioFile.getOriginalFilename());
//     List<Note> notes = analyzeFile(audioFile);
//     logger.info("File analysis complete, found {} notes.", notes.size());

//     AudioFile file = new AudioFile();
//     file.setTitle(audioFile.getOriginalFilename()); // Setting the title from the original file name
//     file.setNotes(notes);
//     Lyrics lyrics = new Lyrics(); // Creating new lyrics instance

//     logger.info("AudioFile details before saving: {}", file);
//     logger.info("Lyrics details before saving: {}", lyrics);
//     logger.info("Notes details before saving: {}", notes);

//     // Persist changes
//     audioFileRepository.save(file);
//     lyricsRepository.save(lyrics);

//     if (!notes.isEmpty()) {
//         noteRepository.saveAll(notes);
//         logger.info("Saving notes to database...");
//         logger.info("Notes saved to database successfully.");
//     } else {
//         logger.warn("No notes found to save to database.");
//     }

//     // Verifying the saved data
//     AudioFile savedFile = audioFileRepository.findById(file.getId()).orElse(null);
//     Lyrics savedLyrics = lyricsRepository.findById(lyrics.getId()).orElse(null);

//     logger.info("Saved AudioFile details: {}", savedFile);
//     logger.info("Saved Lyrics details: {}", savedLyrics);

//        // Manually check database content
//        List<AudioFile> files = audioFileRepository.findAll();
//        logger.info("Manual check - Number of AudioFiles in database: {}", files.size());
   
//        // Check notes count directly from the database
//        long notesCount = noteRepository.count();
//        logger.info("Manual check - Number of notes in database: {}", notesCount);

//     List<NoteDTO> noteDTOs = convertNotesToNoteDTOs(notes);
//     return noteDTOs;
// }

//     @Transactional
//     @Override
//     public List<NoteDTO> saveFile(MultipartFile audioFile) throws IOException, UnsupportedAudioFileException {
//     logger.info("Starting file analysis for {}", audioFile.getOriginalFilename());
//     List<Note> notes = analyzeFile(audioFile);
//     logger.info("File analysis complete, found {} notes.", notes.size());

    
//     AudioFile file = new AudioFile();
//     file.setTitle(audioFile.getOriginalFilename());   // Create a new audio file instance
//     file.setNotes(notes);
//     Lyrics lyrics = new Lyrics();  // Create new lyrics instance


//     if (!notes.isEmpty()) {
//         logger.info("Saving notes to database...");
//         noteRepository.saveAll(notes);
//         logger.info("Notes saved to database successfully.");
//     } else {
//         logger.warn("No notes found to save to database.");
//     }


//     // Persist changes
//         audioFileRepository.save(file);
//         lyricsRepository.save(lyrics);
//         // noteRepository.saveAll(notes);


 

//     // Manually flushing and committing
//         entityManager.flush();  // Force flush to DB
//         entityManager.clear();  // Clear the persistence context to avoid any cache effects
//      logger.info("Transaction committed successfully.");

//     // // logger.debug("Saving song: {}", song);
//     //     songRepository.save(song);
//     //     // logger.debug("Song saved successfully.");

//     // if (!notes.isEmpty()) {
//     //     logger.info("Saving notes to database...");
//     //     noteRepository.saveAll(notes);
//     //     logger.info("Notes saved to database successfully.");
//     // } else {
//     //     logger.warn("No notes found to save to database.");
//     // }
//     boolean isFileSaved = audioFileRepository.findById(file.getId()).isPresent();
//     boolean areNotesSaved = noteRepository.count() == notes.size();  // Assuming no other notes are being added concurrently
//     logger.info("Verification: Audio file saved? {}", isFileSaved);
//     logger.info("Verification: All notes saved? {}", areNotesSaved);


//     List<NoteDTO> noteDTOs = convertNotesToNoteDTOs(notes);
//     return noteDTOs;
// }

// @Transactional
//  @Override
// public List<NoteDTO> saveFile(MultipartFile audioFile) throws IOException, UnsupportedAudioFileException {
//     try {
//         List<Note> notes = new ArrayList<>(); // Declare the variable 'notes'
//         AudioFile file = new AudioFile();  // Create a new audio file instance
//         file.setNotes(notes);
//         Lyrics lyrics = new Lyrics();  // Create new lyrics instance

//         // Persist changes
//         audioFileRepository.save(file);
//         lyricsRepository.save(lyrics);
//         noteRepository.saveAll(notes);

//         // Manually flushing and committing
//         entityManager.flush();  // Force flush to DB
//         entityManager.clear();  // Clear the persistence context to avoid any cache effects

//         logger.info("Transaction committed successfully.");
//     } catch (Exception ex) {
//         entityManager.getTransaction().rollback();
//         logger.error("Transaction rolled back due to an error: {}", ex.getMessage());
//         throw ex;  // Rethrow the exception to ensure it's handled further up the stack
//     }
//     List<Note> notes = new ArrayList<>(); // Declare and initialize the variable 'notes'
//     return convertNotesToNoteDTOs(notes);
// }

// @Transactional
// @Override
// public List<Note> analyzeFile(MultipartFile audioFile) throws UnsupportedAudioFileException, IOException {
//     Logger logger = LoggerFactory.getLogger(AudioProcessingServiceImpl.class);
//     logger.info("Analyzing audio file...");

//     List<Note> notes = new ArrayList<>();
//     InputStream inputStream = new BufferedInputStream(audioFile.getInputStream());
//     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
//     AudioFormat format = audioInputStream.getFormat();
//     int sampleRate = (int) format.getSampleRate();
//     byte[] audioBytes = audioInputStream.readAllBytes();

//     logger.info("Audio stream read with sample rate: {}", sampleRate);

//     PitchDetectionHandler pitchDetectionHandler = (pitchDetectionResult, audioEvent) -> {
//         if (pitchDetectionResult.getPitch() > 0) {
//             Note note = new Note();
//             note.setPitch(pitchDetectionResult.getPitch());
//             note.setStartTime(audioEvent.getTimeStamp() - (double) audioEvent.getBufferSize() / audioEvent.getSampleRate());
//             note.setEndTime(audioEvent.getTimeStamp());

//             notes.add(note);

            // Detailed logging for each note
//             logger.debug("Detected note: Pitch = {}, Start Time = {}, End Time = {}",
//                          pitchDetectionResult.getPitch(), note.getStartTime(), note.getEndTime());
//         }
//     };

//     AudioDispatcher dispatcher = AudioDispatcherFactory.fromByteArray(audioBytes, format, 1024, 0);
//     dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, 1024, pitchDetectionHandler));
//     dispatcher.run();

//     logger.info("Audio processing completed. Total notes detected: {}", notes.size());

//     return notes;
// }

    // @Transactional
    // @Override
    // public List<Note> analyzeFile(MultipartFile audioFile) throws UnsupportedAudioFileException, IOException {
    //     logger.info("Analyzing audio file...");
    //     List<Note> notes = new ArrayList<>();
    //     InputStream inputStream = new BufferedInputStream(audioFile.getInputStream());
    //     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
    //     AudioFormat format = audioInputStream.getFormat();
    //     int sampleRate = (int) format.getSampleRate();
    //     byte[] audioBytes = audioInputStream.readAllBytes();
    //     logger.info("Audio stream read with sample rate: {}", sampleRate);

    //     PitchDetectionHandler pitchDetectionHandler = (pitchDetectionResult, audioEvent) -> {
    //         if (pitchDetectionResult.getPitch() > 0) {
    //             Note note = new Note();
    //             note.setPitch(pitchDetectionResult.getPitch());
    //             note.setStartTime(audioEvent.getTimeStamp() - (double) audioEvent.getBufferSize() / audioEvent.getSampleRate());
    //             note.setEndTime(audioEvent.getTimeStamp());
    //             notes.add(note);
    //             logger.debug("Detected note: Pitch = {}, Start Time = {}, End Time = {}",
    //                          pitchDetectionResult.getPitch(), note.getStartTime(), note.getEndTime());
    //         }
    //     };

    //     AudioDispatcher dispatcher = AudioDispatcherFactory.fromByteArray(audioBytes, format, 1024, 0);
    //     dispatcher.addAudioProcessor(new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, 1024, pitchDetectionHandler));
    //     dispatcher.run();
    //     logger.info("Audio processing completed. Total notes detected: {}", notes.size());

    //     return notes;
    // }

    // @Override
    // public List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes) {
    //     logger.info("Converting notes to DTOs...");
    //     List<NoteDTO> dtos = notes.stream()
    //                               .map(note -> new NoteDTO(note.getPitch(), note.getDuration(), 0, note.getLyrics()))
    //                               .collect(Collectors.toList());
    //     logger.info("Conversion to DTOs complete. Total DTOs created: {}", dtos.size());
    //     return dtos;
    // }

//     @Override
//     @Transactional
// public List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes) {
//     logger.info("Converting notes to DTOs...");
//     List<NoteDTO> dtos = notes.stream()
//                               .map(note -> {
//                                   Double duration = note.getDuration();
//                                   if (duration == null) {
//                                       // handle the case where duration is null
//                                       // for example, you can set a default value
//                                       duration = 0.0;
//                                   }
//                                   return new NoteDTO(note.getPitch(), duration, 0, note.getLyrics(), duration, null);
//                               })
//                               .collect(Collectors.toList());
//     logger.info("Conversion to DTOs complete. Total DTOs created: {}", dtos.size());
//     return dtos;
// }

// @Transactional
//  @Override
// public Note toEntity(NoteDTO noteDTO) {
//     Note note = new Note();
//     note.setPitch(noteDTO.getPitch());
//     note.setDuration(noteDTO.getDuration());
//     note.setLyrics(noteDTO.getLyrics());

//     Double duration = note.getDuration();
//     if (duration != null) {
//         double durationValue = duration.doubleValue();
//         // use durationValue
//     } else {
//         // handle the case where duration is null
//     }

//     logger.info("Converted DTO to entity: Pitch = {}, Duration = {}", note.getPitch(), note.getDuration());
//     return note;
// }



    // @Override
    // public Note toEntity(NoteDTO noteDTO) {
    //     Note note = new Note();
    //     note.setPitch(noteDTO.getPitch());
    //     note.setDuration(noteDTO.getDuration());
    //     note.setLyrics(noteDTO.getLyrics());
    //     logger.info("Converted DTO to entity: Pitch = {}, Duration = {}", note.getPitch(), note.getDuration());
    //     return note;
    // }
}


    // handle the case where duration is null
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


