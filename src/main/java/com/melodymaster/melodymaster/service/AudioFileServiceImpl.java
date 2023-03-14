package com.melodymaster.melodymaster.service;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;

@Service
public class AudioFileService {

    @Autowired
    private NoteRepository noteRepository;

    public void processAudioFile(AudioFileDto audioFileDto) {
        // Validate the audio file and extract the vocals
        validateAudioFile(audioFileDto);
        AudioFileDto vocalsFileDto = extractVocals(audioFileDto);

        // Analyze the vocals and save the pitch, length, and lyrics information
        List<NoteDto> noteDtos = analyzeVocals(vocalsFileDto);
        saveNoteDtos(noteDtos);
    }

    private void validateAudioFile(AudioFileDto audioFileDto) {
        // Check that the audio file is in a supported format and contains vocals
        // Throw an exception if the audio file is invalid
    
        String filePath = audioFileDto.getFilePath();
        File file = new File(filePath);
    
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file)) {
            AudioFormat format = audioInputStream.getFormat();
    
            if (!format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED)) {
                throw new IllegalArgumentException("Audio file format not supported.");
            }
    
            int numChannels = format.getChannels();
            int sampleSize = format.getSampleSizeInBits() / 8;
    
            if (numChannels < 2 || sampleSize < 2) {
                throw new IllegalArgumentException("Audio file does not contain vocals.");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error validating audio file: " + e.getMessage());
        }
    }

    private AudioFileDto extractVocals(AudioFileDto audioFileDto) {
        // Use a source separation algorithm to extract the vocals from the audio file
        // Save the vocals to a new audio file and return the audio file DTO
    
        // Here's an example implementation using the TarsosDSP library:
        try {
            String filePath = audioFileDto.getFilePath();
            File file = new File(filePath);
            AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(file, 2048, 0);
            AudioProcessor silenceDetector = new SilenceDetector(1, false);
            AudioProcessor outputWriter = new WriterProcessor(new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 1, 2, 44100, false), new File("vocals.wav"));
            dispatcher.addAudioProcessor(silenceDetector);
            dispatcher.addAudioProcessor(outputWriter);
            dispatcher.run();
        } catch (Exception e) {
            throw new RuntimeException("Error extracting vocals from audio file: " + e.getMessage());
        }
    
        // Return the vocals as a new audio file DTO
        String vocalsFilePath = "vocals.wav";
        AudioFileDto vocalsFileDto = new AudioFileDto(vocalsFilePath);
        return vocalsFileDto;
    }

    private AudioFileDto extractVocals(AudioFileDto audioFileDto) {
        // Use a source separation algorithm to extract the vocals from the audio file
        // Save the vocals to a new audio file and return the audio file DTO
    
        // Here's an example implementation using the TarsosDSP library:
        try {
            String filePath = audioFileDto.getFilePath();
            File file = new File(filePath);
            AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(file, 2048, 0);
            AudioProcessor silenceDetector = new SilenceDetector(1, false);
            AudioProcessor outputWriter = new WriterProcessor(new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 1, 2, 44100, false), new File("vocals.wav"));
            dispatcher.addAudioProcessor(silenceDetector);
            dispatcher.addAudioProcessor(outputWriter);
            dispatcher.run();
        } catch (Exception e) {
            throw new RuntimeException("Error extracting vocals from audio file: " + e.getMessage());
        }
    
        // Return the vocals as a new audio file DTO
        String vocalsFilePath = "vocals.wav";
        AudioFileDto vocalsFileDto = new AudioFileDto(vocalsFilePath);
        return vocalsFileDto;
    }

    private List<NoteDto> analyzeVocals(AudioFileDto vocalsFileDto) {
        // Use a pitch analysis algorithm to identify the pitch and length of each note
        // Use a lyrics recognition algorithm to identify the lyrics of each note
        // Return a list of note DTOs
    
        List<NoteDto> noteDtos = new ArrayList<>();
    
        // Here's an example implementation using the TarsosDSP library for pitch analysis:
        try {
            String vocalsFilePath = vocalsFileDto.getFilePath();
            File file = new File(vocalsFilePath);
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            float sampleRate = stream.getFormat().getSampleRate();
            float[] samples = new float[stream.available()];
            int read = stream.read(samples);
    
            PitchDetectionHandler pitchHandler = new PitchDetectionHandler() {
                @Override
                public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
                    float pitch = pitchDetectionResult.getPitch();
                    double timestamp = audioEvent.getTimeStamp();
                    double pitchConfidence = pitchDetectionResult.getProbability();
                    // TODO: Use the pitch and timestamp to determine the length of the note
                    // TODO: Use a lyrics recognition algorithm to determine the lyrics of the note
                    NoteDto noteDto = new NoteDto(pitch, timestamp, pitchConfidence);
                    noteDtos.add(noteDto);
                }
            };
    
            AudioDispatcher dispatcher = new AudioDispatcher(new AudioFloatArrayReader(samples), sampleRate, 2048, 0);
            AudioProcessor pitchProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.YIN, sampleRate, 2048, pitchHandler);
            dispatcher.addAudioProcessor(pitchProcessor);
            dispatcher.run();
        } catch (Exception e) {
            throw new RuntimeException("Error analyzing vocals: " + e.getMessage());
        }
    
        return noteDtos;
    }

    private void saveNoteDtos(List<NoteDto> noteDtos) {
        // Convert the note DTOs to note entities and save them to the database
        List<NoteEntity> noteEntities = noteDtos.stream()
            .map(noteDto -> new NoteEntity(noteDto.getPitch(), noteDto.getLength(), noteDto.getLyrics()))
            .collect(Collectors.toList());
        noteRepository.saveAll(noteEntities);
    }
}

@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
}
// In this updated code, @Service annotation indicates that the AudioFileService is a Spring service component. The @Autowired annotation is used to inject an instance of the NoteRepository interface into the service class.

// The @Repository annotation is used to mark the NoteRepository interface as a Spring repository component. This interface extends the JpaRepository interface, which provides CRUD operations for the NoteEntity class.

// Note that this is a simplified example and the actual implementation may differ depending on the specific requirements of the Melody Master app.





