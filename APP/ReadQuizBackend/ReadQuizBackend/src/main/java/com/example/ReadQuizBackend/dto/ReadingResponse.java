package com.example.ReadQuizBackend.dto;

import com.example.ReadQuizBackend.entity.Reading;

import java.time.LocalDateTime;

public class ReadingResponse {
    private Integer id;
    private String title;
    private String content;
    private String summary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReadingResponse(Reading reading) {
        this.id = reading.getId();
        this.title = reading.getTitle();
        this.content = reading.getContent();
        this.summary = reading.getSummary();
        this.createdAt = reading.getCreatedAt();
        this.updatedAt = reading.getUpdatedAt();
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getSummary() {
        return summary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
