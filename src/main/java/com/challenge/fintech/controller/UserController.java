package com.challenge.fintech.controller;

import com.challenge.fintech.dto.CreateUserRequest;
import com.challenge.fintech.dto.UpdateRequest;
import com.challenge.fintech.model.User;
import com.challenge.fintech.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {

     System.out.println("Creating user with email: " + createUserRequest.getEmail());
    System.out.println("Creating user with phone number: " + createUserRequest.getPhoneNumber());
        User newUser = userService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }



    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get")
    public ResponseEntity<User> getUserById(@RequestParam Long id) {
        return ResponseEntity.of(userService.getUserById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UpdateRequest user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
