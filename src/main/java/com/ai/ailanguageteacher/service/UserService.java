package com.ai.ailanguageteacher.service;

import com.ai.ailanguageteacher.dto.request.CreateUserRequest;
import com.ai.ailanguageteacher.model.ChatUser;
import com.ai.ailanguageteacher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void createUser(CreateUserRequest request) {
        ChatUser u = new ChatUser();
        u.setUsername(request.getUsername());
        u.setEmail(request.getEmail());

        repository.save(u);
    }
}
