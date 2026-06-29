package com.example.ReadQuizBackend.controller;

import com.example.ReadQuizBackend.dto.ApiResponse;
import com.example.ReadQuizBackend.dto.ReadingRequest;
import com.example.ReadQuizBackend.dto.ReadingResponse;
import com.example.ReadQuizBackend.entity.Reading;
import com.example.ReadQuizBackend.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/readings")
public class ReadingController {

    @Autowired
    private ReadingService readingService;

    @GetMapping
    public ApiResponse<List<ReadingResponse>> findAll() {
        List<ReadingResponse> readings = readingService.findAll()
                .stream()
                .map(ReadingResponse::new)
                .toList();

        return new ApiResponse<>(true, "Lay danh sach bai doc thanh cong", readings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReadingResponse>> findById(@PathVariable Integer id) {
        try {
            Reading reading = readingService.findById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Lay bai doc thanh cong", new ReadingResponse(reading)));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReadingResponse>> create(@RequestBody ReadingRequest request) {
        try {
            Reading reading = readingService.create(request);
            return ResponseEntity.ok(new ApiResponse<>(true, "Tao bai doc thanh cong", new ReadingResponse(reading)));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReadingResponse>> update(
            @PathVariable Integer id,
            @RequestBody ReadingRequest request
    ) {
        try {
            Reading reading = readingService.update(id, request);
            return ResponseEntity.ok(new ApiResponse<>(true, "Cap nhat bai doc thanh cong", new ReadingResponse(reading)));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        try {
            readingService.delete(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Xoa bai doc thanh cong", null));
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, exception.getMessage(), null));
        }
    }
}
