package com.melodymaster.melodymaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.melodymaster.melodymaster.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {
}