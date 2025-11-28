package com.mullen.nurser.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "_user")
@Getter
@Setter
@Builder
public class User {
    @Id
    @Column(value = "user_id")
    private UUID id;

    @Column(value = "first_name")
    private String firstName;

    @Column(value = "last_name")
    private String lastName;

    private String email;

    private String password;

    @Column(value = "created_at")
    private LocalDateTime  createdAt;

    @Column(value = "updated_at")
    private LocalDateTime updatedAt;

    @Column(value = "birth_date")
    private LocalDateTime birth;

    @Column(value = "coren")
    private String coren;
}
