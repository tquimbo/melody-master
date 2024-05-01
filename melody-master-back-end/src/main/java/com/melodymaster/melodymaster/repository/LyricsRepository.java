package com.melodymaster.melodymaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.melodymaster.melodymaster.entity.Lyrics;

@Repository
public interface LyricsRepository extends CrudRepository<Lyrics, Long> {
}