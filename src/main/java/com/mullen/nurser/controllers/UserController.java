package com.mullen.nurser.controllers;

import com.mullen.nurser.domain.user.User;
import com.mullen.nurser.domain.user.dto.CreateUserDTO;
import com.mullen.nurser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/api/user")
@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping
    public Mono<User> create(@RequestBody CreateUserDTO user) {
        return this.userService.saveUser(user);
    }
}
