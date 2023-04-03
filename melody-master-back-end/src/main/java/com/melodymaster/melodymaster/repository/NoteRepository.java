package com.melodymaster.melodymaster.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.melodymaster.melodymaster.entity.Note;

@Repository
public interface NoteRepository extends CrudRepository <Note, Long> {
  
}