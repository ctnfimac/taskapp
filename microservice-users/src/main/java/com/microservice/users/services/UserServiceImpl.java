package com.microservice.users.services;

import com.microservice.users.entities.UserEntity;
import com.microservice.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
