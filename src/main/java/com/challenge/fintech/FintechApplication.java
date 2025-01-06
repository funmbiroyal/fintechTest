package com.challenge.fintech;

import com.challenge.fintech.model.Role;
import com.challenge.fintech.repository.RoleRepository;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FintechApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		SpringApplication.run(FintechApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;

	@PostConstruct
	@Transactional
	public void initializeRoles() {
		List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");

		roles.forEach(roleName -> {
			if (!roleRepository.existsByName(roleName)) {
				Role role = new Role();
				role.setName(roleName);
				roleRepository.save(role);
			}
		});
	}

}
