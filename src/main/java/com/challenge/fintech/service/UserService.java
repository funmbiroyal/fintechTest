package com.challenge.fintech.service;

import com.challenge.fintech.dto.CreateUserRequest;
import com.challenge.fintech.dto.UpdateRequest;
import com.challenge.fintech.model.Role;
import com.challenge.fintech.model.User;
import com.challenge.fintech.repository.RoleRepository;
import com.challenge.fintech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest createUserRequest) {
        // Fetch the "ROLE_ADMIN" role from the database
        Role role = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role 'ROLE_ADMIN' not found"));

        // Create a new user and set its properties using setters
        User newUser = new User();
        newUser.setUsername(createUserRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setFullName(createUserRequest.getFullName());
        newUser.setPhoneNumber(createUserRequest.getPhoneNumber());

        // Ensure the user has the ROLE_ADMIN role
        Set<Role> roles = new HashSet<>();
        roles.add(role);  // Add the ROLE_ADMIN to the user's roles
        newUser.setRoles(roles); // Assign roles to the user

        // Save the user and return
        return userRepository.save(newUser);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {

        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with the id" + id + " does not exist!!!")));
    }

    public User updateUser(UpdateRequest request) {
        User existingUser = userRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setEmail(request.getEmail());
        existingUser.setPhoneNumber(request.getPhoneNumber());
        existingUser.setUsername(request.getUsername());
        existingUser.setFullName(request.getFullName());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        Set<Role> updatedRoles = new HashSet<>();
        for (String roleName : request.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role " + roleName + " not found"));
            updatedRoles.add(role);
        }
        existingUser.setRoles(updatedRoles);

        return userRepository.save(existingUser);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "user with the id " + id + " has been successfully deleted";
    }
}
