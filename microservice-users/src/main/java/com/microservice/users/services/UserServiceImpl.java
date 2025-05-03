package com.microservice.users.services;

import com.microservice.users.entities.UserEntity;
import com.microservice.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    /*@Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }*/

    @Override
    public Optional<UserEntity> getById(Long id) {
        return userRepository.findById(id);
    }
/*
    @Override
    public UserEntity create(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity update(Long id, UserEntity user) {
        if(userRepository.existsById(id)){
            UserEntity userCurrent = userRepository.findById(id).get();
            userCurrent.setEmail( user.getEmail() != null ? user.getEmail() : userCurrent.getEmail() );
            userCurrent.setUsername( user.getUsername() != null ? user.getUsername() : userCurrent.getUsername() );
            return userRepository.save(userCurrent);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }*/
}
