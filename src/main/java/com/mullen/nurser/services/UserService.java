package com.mullen.nurser.services;

import com.mullen.nurser.domain.user.User;
import com.mullen.nurser.domain.user.dto.CreateUserDTO;
import com.mullen.nurser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Mono<User> saveUser (CreateUserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.email())
                .password(passwordEncoder.encode(userDTO.password()))
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .birth(userDTO.birth())
                .build();
        return this.userRepository.save(user);
    }
}
