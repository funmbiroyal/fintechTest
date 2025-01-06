package com.challenge.fintech.repository;

import com.challenge.fintech.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // Custom query method to find role by name
    Optional<Role> findByName(String name);

    boolean existsByName(String roleName);
}

