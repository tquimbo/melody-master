package com.melodymaster.melodymaster;

@SpringBootTest
public class AudioFileServiceTest {

    @Autowired
    private AudioFileService audioFileService;

    @Test
    public void testProcessAudioFile() {
        // TODO: Write test case for processAudioFile method
    }

    // Add more test cases here
    @Test
public void testProcessAudioFile() {
    // Create a mock audio file for testing
    MockMultipartFile mockFile = new MockMultipartFile("audio.mp3", getClass().getResourceAsStream("/audio.mp3"));

    // Create an AudioFileDto object from the mock file
    AudioFileDto audioFileDto = new AudioFileDto(mockFile);

    // Call the processAudioFile method and assert the result
    audioFileService.processAudioFile(audioFileDto);

    // TODO: Add assertions to check the results of the processing

    @Test
public void testValidateAudioFile() {
    // Create a mock audio file for testing
    MockMultipartFile mockFile = new MockMultipartFile("audio.mp3", getClass().getResourceAsStream("/audio.mp3"));

    // Create an AudioFileDto object from the mock file
    AudioFileDto audioFileDto = new AudioFileDto(mockFile);

    // Call the validateAudioFile method and assert that no exception is thrown
    audioFileService.validateAudioFile(audioFileDto);
}

@Test
public void testExtractVocals() {
    // Create a mock audio file for testing
    MockMultipartFile mockFile = new MockMultipartFile("audio.mp3", getClass().getResourceAsStream("/audio.mp3"));

    // Create an AudioFileDto object from the mock file
    AudioFileDto audioFileDto = new AudioFileDto(mockFile);

    // Call the extractVocals method and assert that the returned AudioFileDto object is not null
    AudioFileDto vocalsFileDto = audioFileService.extractVocals(audioFileDto);
    assertNotNull(vocalsFileDto);
}

@Test
public void testAnalyzeVocals() {
    // Create a mock audio file for testing
    MockMultipartFile mockFile = new MockMultipartFile("audio.mp3", getClass().getResourceAsStream("/audio.mp3"));

    // Create an AudioFileDto object from the mock file
    AudioFileDto audioFileDto = new AudioFileDto(mockFile);

    // Extract the vocals from the audio file
    AudioFileDto vocalsFileDto = audioFileService.extractVocals(audioFileDto);

    // Call the analyzeVocals method and assert that the returned list of NoteDto objects is not null or empty
    List<NoteDto> noteDtos = audioFileService.analyzeVocals(vocalsFileDto);
    assertNotNull(noteDtos);
    assertFalse(noteDtos.isEmpty());
}
}

}