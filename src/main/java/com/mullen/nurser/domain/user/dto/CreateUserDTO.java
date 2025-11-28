package com.mullen.nurser.domain.user.dto;

import java.time.LocalDateTime;

public record CreateUserDTO(String firstName, String lastName, String email, String password, LocalDateTime birth) {
}
