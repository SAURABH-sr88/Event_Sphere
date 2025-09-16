package com.eventsphere.service;

import com.eventsphere.model.Role;
import com.eventsphere.model.User;
import com.eventsphere.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeeder {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seed() {
        if (!userRepository.existsByUsername("admin")) {
            var admin = User.builder()
                    .username("admin")
                    .email("admin@eventsphere.local")
                    .password(passwordEncoder.encode("Admin@123"))
                    .roles(Set.of(Role.ROLE_ADMIN, Role.ROLE_USER))
                    .build();
            userRepository.save(admin);
            System.out.println("Seeded admin user");
        }
    }
}
