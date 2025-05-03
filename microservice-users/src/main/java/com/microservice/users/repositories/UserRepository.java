package com.microservice.users.repositories;

import com.microservice.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getUserEntityByEmailAndPassword(String email, String password);
}
