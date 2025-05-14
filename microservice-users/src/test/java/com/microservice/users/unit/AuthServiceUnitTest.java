package com.microservice.users.unit;

import com.microservice.users.entities.UserEntity;
import com.microservice.users.repositories.UserRepository;
import com.microservice.users.services.AuthServiceImpl;
import net.bytebuddy.asm.Advice;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.control.MappingControl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
