package com.krispam.property.services;

import com.krispam.property.domain.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserEntity createUser(UserEntity user);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> findById(Long userId);
}
