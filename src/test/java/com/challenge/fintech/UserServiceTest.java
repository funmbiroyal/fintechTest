package com.challenge.fintech;

import com.challenge.fintech.dto.CreateUserRequest;
import com.challenge.fintech.dto.UpdateRequest;
import com.challenge.fintech.model.Role;
import com.challenge.fintech.model.User;
import com.challenge.fintech.repository.RoleRepository;
import com.challenge.fintech.repository.UserRepository;
import com.challenge.fintech.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Role role;
    private User existingUser;
    private CreateUserRequest createUserRequest;
    private UpdateRequest updateRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock Role
        role = new Role();
        role.setName("ROLE_ADMIN");

        // Mock CreateUserRequest
        createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("testuser");
        createUserRequest.setPassword("password");
        createUserRequest.setEmail("test@example.com");
        createUserRequest.setFullName("Test User");
        createUserRequest.setPhoneNumber("1234567890");
    }

    @Test
    void testCreateUser() {
        // Mock roleRepository to return the "ROLE_ADMIN" role
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(Optional.of(role));

        // Mock passwordEncoder to return the same password as encoded
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        // Mock userRepository to return the saved user
        User mockedUser = new User();
        mockedUser.setUsername(createUserRequest.getUsername());
        mockedUser.setPassword("encodedPassword");
        mockedUser.setEmail(createUserRequest.getEmail());
        mockedUser.setFullName(createUserRequest.getFullName());
        mockedUser.setPhoneNumber(createUserRequest.getPhoneNumber());
        mockedUser.setRoles(Set.of(role));

        when(userRepository.save(any(User.class))).thenReturn(mockedUser);

        // Call the method under test
        User createdUser = userService.createUser(createUserRequest);

        // Verify the results
        assertNotNull(createdUser);
        assertEquals("testuser", createdUser.getUsername());
        assertEquals("encodedPassword", createdUser.getPassword());
        assertEquals("test@example.com", createdUser.getEmail());
        assertEquals("Test User", createdUser.getFullName());
        assertEquals("1234567890", createdUser.getPhoneNumber());
        assertTrue(createdUser.getRoles().contains(role));

        // Verify the interactions with mocks
        verify(roleRepository).findByName("ROLE_ADMIN");
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
    }
    @Test
    void testGetUserById() {
        // Given
        User user = new User();
        user.setUsername("Matilge");
        user.setEmail("george@gmail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // When
        Optional<User> foundUser = userService.getUserById(1L);

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals("Matilge", foundUser.get().getUsername());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteUser() {
        // Given
        Long userId = 1L;

        // When
        userService.deleteUser(userId);

        // Then
        verify(userRepository, times(1)).deleteById(userId);
    }
}
