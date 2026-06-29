package com.example.ReadQuizBackend.repository;

import com.example.ReadQuizBackend.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Integer> {
    List<Reading> findAllByOrderByCreatedAtDesc();
}
