package com.melodymaster.melodymaster.service;

public package com.melodymaster.melodymaster.service;

import com.melodymaster.melodymaster.dto.NotesDTO;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.sound.sampled.UnsupportedAudioFileException;

public interface AudioFileService  {
    List<Note> saveFile(File audioFile) throws IOException, UnsupportedAudioFileException
    getAveragePitch(double[] pitches, int startIndex, int endIndex);
    List<Note> analyzeFile(File audioFile) throws IOException, UnsupportedAudioFileException
    String extractLyrics(byte[] audioBytes, int startIndex, int endIndex);
} 