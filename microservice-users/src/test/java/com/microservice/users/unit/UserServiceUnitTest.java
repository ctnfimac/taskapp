package com.microservice.users.unit;

import com.microservice.users.connector.TaskBlockConnector;
import com.microservice.users.connector.TaskConnector;
import com.microservice.users.connector.response.TaskBlockDTO;
import com.microservice.users.connector.response.TaskDTO;
import com.microservice.users.entities.UserEntity;
import com.microservice.users.enums.APIError;
import com.microservice.users.exceptions.GlobalTaskException;
import com.microservice.users.repositories.UserRepository;
import com.microservice.users.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskConnector taskConnector;

    @Mock
    private TaskBlockConnector taskBlockConnector;

    @InjectMocks
    private UserServiceImpl userService;

    private LocalDateTime dateFake;
    private TaskDTO task1;
    private TaskDTO task2;

    @BeforeEach
    void initialize_each_test(){
        MockitoAnnotations.openMocks(this);
        dateFake = LocalDateTime.now();

        task1 = new TaskDTO();
        task1.setId(1L);
        task1.setTitle("Implementar RabbitMq");

        task2 = new TaskDTO();
        task2.setId(2L);
        task2.setTitle("Implementar ApacheKafka");
    }


    @Test
    @DisplayName("Test para obtener un taskBlock por id correctamente")
    public void getById_should_OneTaskBlock(){
        // given
        Long id = 1L;

        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("test@gmail.com")
                .password("123456")
                .createdAt(dateFake)
                .updatedAt(dateFake)
                .build();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // when
        UserEntity userResponse = userService.getById(id).get();

        // then
        verify(userRepository, Mockito.atMostOnce()).findById(id);
        assertAll(
            () -> assertNotNull(userResponse),
            () -> assertEquals(userResponse.getEmail(), user.getEmail()),
            () -> assertEquals(userResponse.getPassword(), user.getPassword())
        );
    }

    @Test
    @DisplayName("Test para obtener un taskBlock vacio por que no existe")
    public void getById_should_Empty(){
        // given
        Long id = 1L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        // when
        Optional<UserEntity> userResponse = userService.getById(id);

        // then
        verify(userRepository, Mockito.atMostOnce()).findById(id);
        assertEquals(userResponse, Optional.empty());
    }

    @Test
    @DisplayName("Test para obtener los Taskblock de un usuario determinado")
    void getTaskBlockByUser_should_ReturnTaskBlocks() {
        // Given
        Long userId = 1L;

        TaskBlockDTO taskBlockDTO = new TaskBlockDTO();
        taskBlockDTO.setId(1L);
        taskBlockDTO.setTitle("Block 1");

        List<TaskBlockDTO> expectedBlocks = List.of(taskBlockDTO);

        Mockito.when(userRepository.existsById(userId)).thenReturn(true);
        Mockito.when(taskBlockConnector.getTaskBlocks(userId)).thenReturn(expectedBlocks);

        // When
        List<TaskBlockDTO> result = userService.getTaskBlockByUser(userId);

        // Then
        assertAll(
                () -> assertEquals(expectedBlocks, result),
                () -> assertEquals(result.size(), 1),
                () -> verify(userRepository).existsById(userId),
                () -> verify(taskBlockConnector).getTaskBlocks(userId)
        );
    }


    @Test
    @DisplayName("Test para verificar el error cuando no existe el usuario")
    void getTaskBlockByUser_whenUserDoesNotExist_shouldThrowException() {
        // Given
        Long userId = 1L;

        Mockito.when(userRepository.existsById(userId)).thenReturn(false);

        // When
        GlobalTaskException exception = assertThrows(GlobalTaskException.class,
                () -> userService.getTaskBlockByUser(userId));

        // Then
        assertAll(
                () -> assertEquals(APIError.USER_NOT_FOUND.getMessage(), exception.getDescription()),
                () -> verify(userRepository).existsById(userId),
                () -> verifyNoInteractions(taskBlockConnector)
        );
    }

    @Test
    @DisplayName("Test para obtener los Task de un usuario y bloque de tareas determinado")
    void getTaskByUserAndBlock_should_ReturnTasks() {
        // Given
        Long userId = 1L;
        Long blockId = 10L;

        List<TaskDTO> expectedTasks = List.of(task1, task2);

        Mockito.when(userRepository.existsById(userId)).thenReturn(true);
        Mockito.when(taskConnector.getTasksByUserAndBlock(blockId, userId)).thenReturn(expectedTasks);

        // When
        List<TaskDTO> result = userService.getTaskByUserAndBlock(blockId, userId);

        // Then
        assertAll(
                () -> assertEquals(expectedTasks, result),
                () -> assertEquals(result.size(), 2),
                () -> verify(userRepository).existsById(userId),
                () -> verify(taskConnector).getTasksByUserAndBlock(blockId, userId)
        );
    }

}
