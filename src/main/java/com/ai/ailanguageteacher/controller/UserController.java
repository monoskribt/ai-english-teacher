package com.ai.ailanguageteacher.controller;

import com.ai.ailanguageteacher.dto.request.CreateUserRequest;
import com.ai.ailanguageteacher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping()
    public void createUser(@Valid @RequestBody CreateUserRequest request) {
        service.createUser(request);
    }
}
