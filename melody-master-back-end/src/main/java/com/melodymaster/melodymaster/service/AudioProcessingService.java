
// package com.melodymaster.melodymaster.service;

// import com.melodymaster.melodymaster.dto.NoteDTO; // Corrected import statement
// import java.io.File;
// import java.io.IOException;
// import java.util.List;
// import javax.sound.sampled.UnsupportedAudioFileException;
// import com.melodymaster.melodymaster.entity.Note;
// import com.melodymaster.melodymaster.dto.NoteDTO;

// public interface AudioProcessingService {
//     List<NoteDTO> saveFile(File audioFile) throws IOException, UnsupportedAudioFileException;
//     List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes);
//     Note toEntity(NoteDTO noteDTO);
//     List<Note> analyzeFile(File audioFile) throws UnsupportedAudioFileException, IOException;
//     // Other methods
// }
package com.melodymaster.melodymaster.service;

import com.melodymaster.melodymaster.dto.NoteDTO;
import org.springframework.web.multipart.MultipartFile;
import com.melodymaster.melodymaster.entity.Note;
import com.melodymaster.melodymaster.exception.UnsupportedFileException;

import java.io.IOException;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;

public interface AudioProcessingService {
    List<NoteDTO> saveFile(MultipartFile audioFile) throws IOException, UnsupportedAudioFileException;
    List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes);
    Note toEntity(NoteDTO noteDTO);
    List<Note> analyzeFile(MultipartFile audioFile) throws UnsupportedAudioFileException, IOException;
    // Other methods
}