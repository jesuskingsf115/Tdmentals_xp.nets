package br.com.tdmxp.dto.user;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String email,
        String city,
        String state,
        LocalDateTime createdAt
) {}
