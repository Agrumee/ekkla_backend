package com.agrumee.backend.controller;

import com.agrumee.backend.dto.RegisterRequest;
import com.agrumee.backend.model.User;
import com.agrumee.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User createdUser = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
