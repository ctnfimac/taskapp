package com.microservice.users.services;

import com.microservice.users.entities.UserEntity;
import com.microservice.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity register(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
