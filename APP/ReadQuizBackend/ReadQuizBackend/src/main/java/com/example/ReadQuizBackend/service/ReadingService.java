package com.example.ReadQuizBackend.service;

import com.example.ReadQuizBackend.dto.ReadingRequest;
import com.example.ReadQuizBackend.entity.Reading;
import com.example.ReadQuizBackend.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    public List<Reading> findAll() {
        return readingRepository.findAllByOrderByCreatedAtDesc();
    }

    public Reading findById(Integer id) {
        return readingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khong tim thay bai doc"));
    }

    public Reading create(ReadingRequest request) {
        Reading reading = new Reading();
        applyRequest(reading, request);
        return readingRepository.save(reading);
    }

    public Reading update(Integer id, ReadingRequest request) {
        Reading reading = findById(id);
        applyRequest(reading, request);
        return readingRepository.save(reading);
    }

    public void delete(Integer id) {
        Reading reading = findById(id);
        readingRepository.delete(reading);
    }

    private void applyRequest(Reading reading, ReadingRequest request) {
        String title = clean(request.getTitle());
        String content = clean(request.getContent());
        String summary = clean(request.getSummary());

        if (title.isEmpty() || content.isEmpty()) {
            throw new IllegalArgumentException("Vui long nhap tieu de va noi dung bai doc");
        }

        reading.setTitle(title);
        reading.setContent(content);
        reading.setSummary(summary.isEmpty() ? buildSummary(content) : summary);
    }

    private String buildSummary(String content) {
        if (content.length() <= 200) {
            return content;
        }

        return content.substring(0, 200).trim() + "...";
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
