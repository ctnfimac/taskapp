package com.microservice.users.unit.services;

import com.microservice.users.entities.UserEntity;
import com.microservice.users.repositories.UserRepository;
import com.microservice.users.services.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
public class AuthServiceUnitTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void initialize_each_test() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test para verificar el registro exitoso de un usuario")
    void register_should_saveuserEntity(){
        // given
        UserEntity user = UserEntity.builder()
                        .email("test@gmail.com")
                        .password("test")
                        .build();

        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updateddAt = LocalDateTime.now();

        UserEntity userCreated = UserEntity.builder()
                .id(1L)
                .email("test@gmail.com")
                .password("test")
                .createdAt(createdAt)
                .updatedAt(updateddAt)
                .build();

        Mockito.when(userRepository.save(user)).thenReturn(userCreated);

        // when
        UserEntity result = authService.register(user);

        // then
        assertNotNull(result);

        verify(userRepository, Mockito.atMostOnce()).save(user);
        assertAll(() -> assertNotNull(result),
                () -> assertEquals(result.getEmail(), user.getEmail())
        );
    }


    @Test
    @DisplayName("Test para verificar que hay un error al querer registrar un email existente")
    void register_should_throw_DataIntegrityViolationException_when_duplicate_email() {
        UserEntity user = UserEntity.builder()
                .email("existing@example.com")
                .password("123456")
                .build();

        Mockito.when(userRepository.save(user))
                .thenThrow(new DataIntegrityViolationException("Duplicate"));

        assertThrows(DataIntegrityViolationException.class, () -> {
            authService.register(user);
        });
    }


    @Test
    @DisplayName("Test para verificar que existe un user con el valor de email y password")
    void login_should_return_userentity(){
        // given
        UserEntity userLogin = UserEntity.builder()
                .email("christian@gmail.com")
                .password("123456")
                .build();

        LocalDateTime dateFake = LocalDateTime.now();

        UserEntity userSearched = UserEntity.builder()
                .id(2L)
                .email("christian@gmail.com")
                .password("123456")
                .createdAt(dateFake)
                .updatedAt(dateFake)
                .build();

        Mockito.when(userRepository.getUserEntityByEmailAndPassword(
                "christian@gmail.com", "123456"
        )).thenReturn(userSearched);

        // when
        UserEntity user = authService.login(userLogin);

        // then
        verify(userRepository, Mockito.atMostOnce()).getUserEntityByEmailAndPassword("christian@gmail.com", "123456");
        assertAll(
                () -> assertNotNull(user),
                () -> assertEquals(user.getEmail(),userSearched.getEmail()),
                () -> assertEquals(user.getId(),userSearched.getId())
        );
    }

    @Test
    @DisplayName("Test para verificar que el login retorna null si no encuentra el usuario")
    void login_should_return_null_when_user_not_found(){
        // given
        UserEntity userLogin = UserEntity.builder()
                .email("christian@gmail.com")
                .password("123456")
                .build();

        Mockito.when(userRepository.getUserEntityByEmailAndPassword(
                "christia2n@gmail.com", "1234562"
        )).thenReturn(null);

        // when
        UserEntity user = authService.login(userLogin);

        // then
        verify(userRepository, Mockito.atMostOnce()).getUserEntityByEmailAndPassword("christia2n@gmail.com", "1234562");
        assertNull(user);
    }

}
