package com.springexercise.repository;

import com.springexercise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByName(String name);
    Boolean existsByEmail(String email);

}
