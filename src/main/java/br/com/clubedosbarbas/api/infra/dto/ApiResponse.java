package br.com.clubedosbarbas.api.infra.dto;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        String status,
        T data,
        LocalDateTime timestamp
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", data, LocalDateTime.now());
    }
}
