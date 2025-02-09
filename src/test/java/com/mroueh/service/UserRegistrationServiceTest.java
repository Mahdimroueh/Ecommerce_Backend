package com.mroueh.service;

import com.mroueh.dto.UserDto;
import com.mroueh.entity.ShoppingCart;
import com.mroueh.entity.User;
import com.mroueh.exception.DuplicateUsernameException;
import com.mroueh.mapper.UserMapper;
import com.mroueh.repository.ShoppingCartRepository;
import com.mroueh.repository.UserRepository;
import com.mroueh.response.ApiResponse;
import com.mroueh.service.impl.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Registration Service Tests")
class UserRegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private AuthService userRegistrationService;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Captor
    private ArgumentCaptor<ShoppingCart> cartCaptor;

    private UserDto validUserDto;
    private User mappedUser;

    @BeforeEach
    void setUp() {
        // Setup valid user DTO
        validUserDto = new UserDto();
        validUserDto.setUsername("testuser");
        validUserDto.setPassword("password123");
        validUserDto.setFirstName("Test");
        validUserDto.setLastName("User");

        // Setup mapped user
        mappedUser = new User();
        mappedUser.setUsername("testuser");
        mappedUser.setFirstName("Test");
        mappedUser.setLastName("User");
    }

    @Nested
    @DisplayName("When registering a new user")
    class RegisterNewUser {

        @Test
        @DisplayName("Should successfully register when username is unique")
        void shouldRegisterSuccessfully() {
            // Arrange
            when(userRepository.existsByUsername(validUserDto.getUsername())).thenReturn(false);
            when(userMapper.toEntity(validUserDto)).thenReturn(mappedUser);
            when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
            when(userRepository.save(any(User.class))).thenReturn(mappedUser);

            // Act
            ApiResponse response = userRegistrationService.registerUser(validUserDto);

            // Assert
            assertAll(
                    () -> assertTrue(response.isSuccess()),
                    () -> assertEquals("Successfully Registered", response.getMessage()),
                    () -> verify(userRepository).save(userCaptor.capture()),
                    () -> verify(shoppingCartRepository).save(cartCaptor.capture())
            );

            // Verify user properties
            User savedUser = userCaptor.getValue();
            assertAll(
                    () -> assertEquals(validUserDto.getUsername(), savedUser.getUsername()),
                    () -> assertEquals("encodedPassword", savedUser.getPassword()),
                    () -> assertEquals("ROLE_USER", savedUser.getRole()),
                    () -> assertEquals(validUserDto.getFirstName(), savedUser.getFirstName()),
                    () -> assertEquals(validUserDto.getLastName(), savedUser.getLastName())
            );

            // Verify shopping cart
            ShoppingCart savedCart = cartCaptor.getValue();
            assertEquals(mappedUser, savedCart.getUser());
        }

        @Test
        @DisplayName("Should throw exception when username already exists")
        void shouldThrowExceptionForDuplicateUsername() {
            // Arrange
            when(userRepository.existsByUsername(validUserDto.getUsername())).thenReturn(true);

            // Act & Assert
            DuplicateUsernameException exception = assertThrows(
                    DuplicateUsernameException.class,
                    () -> userRegistrationService.registerUser(validUserDto)
            );

            assertAll(
                    () -> assertEquals("Username Already Used", exception.getMessage()),
                    () -> verify(userRepository, never()).save(any()),
                    () -> verify(shoppingCartRepository, never()).save(any()),
                    () -> verify(passwordEncoder, never()).encode(any())
            );
        }

        @Test
        @DisplayName("Should handle null user DTO")
        void shouldHandleNullUserDto() {
            // Act & Assert
            assertThrows(
                    IllegalArgumentException.class,
                    () -> userRegistrationService.registerUser(null)
            );
        }
    }

    @Nested
    @DisplayName("Edge cases and validation")
    class EdgeCases {

        @Test
        @DisplayName("Should handle mapper returning null")
        void shouldHandleMapperReturningNull() {
            // Arrange
            when(userRepository.existsByUsername(validUserDto.getUsername())).thenReturn(false);
            when(userMapper.toEntity(validUserDto)).thenReturn(mappedUser);

            // Act & Assert
            assertThrows(
                    IllegalStateException.class,
                    () -> userRegistrationService.registerUser(validUserDto)
            );

            verify(userRepository, never()).save(any());
            verify(shoppingCartRepository, never()).save(any());
        }

        @Test
        @DisplayName("Should handle password encoder failure")
        void shouldHandlePasswordEncoderFailure() {
            // Arrange
            when(userRepository.existsByUsername(validUserDto.getUsername())).thenReturn(false);
            when(userMapper.toEntity(validUserDto)).thenReturn(mappedUser);
            when(passwordEncoder.encode(any())).thenThrow(new IllegalArgumentException("Invalid password"));

            // Act & Assert
            assertThrows(
                    IllegalArgumentException.class,
                    () -> userRegistrationService.registerUser(validUserDto)
            );

            verify(userRepository, never()).save(any());
            verify(shoppingCartRepository, never()).save(any());
        }
    }
}