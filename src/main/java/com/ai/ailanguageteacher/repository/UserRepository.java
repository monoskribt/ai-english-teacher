package com.ai.ailanguageteacher.repository;

import com.ai.ailanguageteacher.model.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ChatUser, Long> {
}
