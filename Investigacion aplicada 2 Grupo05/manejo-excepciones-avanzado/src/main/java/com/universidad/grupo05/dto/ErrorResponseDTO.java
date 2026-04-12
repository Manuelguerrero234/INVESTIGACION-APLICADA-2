package com.universidad.grupo05.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private int status;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;

    public ErrorResponseDTO(int status, String message, List<String> details) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }
}