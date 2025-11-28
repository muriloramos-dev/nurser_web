package com.mullen.nurser.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "user_entity")
@Getter
@Setter
@Builder
public class User {
    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;


}
