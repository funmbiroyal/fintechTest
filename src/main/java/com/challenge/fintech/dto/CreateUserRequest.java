package com.challenge.fintech.dto;
import jakarta.validation.constraints.*;

import java.util.Set;


public class CreateUserRequest {

    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    private String fullName;
    private Set<String> roles;


    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this. phoneNumber =  phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

