
package com.melodymaster.melodymaster.service;

import com.melodymaster.melodymaster.dto.NoteDTO; // Corrected import statement
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;

public interface AudioProcessingService {
    List<NoteDTO> saveFile(File audioFile) throws IOException, UnsupportedAudioFileException;
    // Other methods
}