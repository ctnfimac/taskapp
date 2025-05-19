package com.microservice.users.unit.controllers;

import com.microservice.users.controllers.AuthController;
import com.microservice.users.dtos.auth.RequestAuthLoginDto;
import com.microservice.users.dtos.auth.RequestAuthRegisterDto;
import com.microservice.users.dtos.auth.ResponseAuthLoginDto;
import com.microservice.users.dtos.auth.ResponseAuthRegisterDto;
import com.microservice.users.entities.UserEntity;
import com.microservice.users.mappers.AuthMapper;
import com.microservice.users.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthControllerUnitTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Mock
    private AuthMapper authMapper;

    private UserEntity user;
    private RequestAuthRegisterDto requestDto;
    private ResponseAuthRegisterDto responseDto;

    private RequestAuthLoginDto requestLoginDto;
    private ResponseAuthLoginDto responseLoginDto;

    @BeforeEach
    void initialize_each_test() {
        LocalDateTime dateFake = LocalDateTime.now();
        user = new UserEntity();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setPassword("test");
        user.setCreatedAt(dateFake);
        user.setUpdatedAt(dateFake);

        requestDto = new RequestAuthRegisterDto();
        requestDto.setEmail("test@gmail.com");
        requestDto.setPassword("test");

        responseDto = new ResponseAuthRegisterDto();
        responseDto.setEmail("test@gmail.com");
        responseDto.setId("1L");

        requestLoginDto = new RequestAuthLoginDto();
        requestLoginDto.setEmail("test@gmail.com");
        requestLoginDto.setPassword("test");

        responseLoginDto = new ResponseAuthLoginDto();
        responseLoginDto.setId(1);
        responseLoginDto.setEmail("test@gmail.com");

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_ReturnsCreatedUser() {
        // Given
        when(authMapper.requestAuthRegisterDtoToUserEntity(requestDto)).thenReturn(user);
        when(authService.register(user)).thenReturn(user);
        when(authMapper.userEntityToResponseAuthRegisterDto(user)).thenReturn(responseDto);

        // When
        ResponseEntity<ResponseAuthRegisterDto> response = authController.register(requestDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());

        verify(authMapper).requestAuthRegisterDtoToUserEntity(requestDto);
        verify(authService).register(user);
        verify(authMapper).userEntityToResponseAuthRegisterDto(user);
    }

    @Test
    void testLogin_ReturnsOk() {
        // Given
        UserEntity foundUser = user;

        when(authMapper.requestAuthLoginDtoToUserEntity(requestLoginDto)).thenReturn(user);
        when(authService.login(user)).thenReturn(foundUser);
        when(authMapper.userEntityToResponseAuthLoginDto(foundUser)).thenReturn(responseLoginDto);

        // When
        ResponseEntity<?> response = authController.login(requestLoginDto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseLoginDto, response.getBody());
        verify(authService).login(user);
    }

    @Test
    void testLogin_ReturnsUnauthorized() {
        // Given
        when(authMapper.requestAuthLoginDtoToUserEntity(requestLoginDto)).thenReturn(user);
        when(authService.login(user)).thenReturn(null); // Simula credenciales inválidas

        // When
        ResponseEntity<?> response = authController.login(requestLoginDto);

        // Then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertTrue(response.getBody().toString().contains("Credenciales inválidas"));
    }
}
