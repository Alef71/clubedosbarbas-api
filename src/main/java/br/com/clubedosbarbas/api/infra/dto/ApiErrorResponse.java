package br.com.clubedosbarbas.api.infra.dto;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        String status,
        String message,
        LocalDateTime timestamp
) {
    public static ApiErrorResponse error(String message) {
        return new ApiErrorResponse("error", message, LocalDateTime.now());
    }
}
