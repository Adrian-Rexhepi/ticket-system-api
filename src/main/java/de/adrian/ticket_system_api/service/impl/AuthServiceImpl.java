package de.adrian.ticket_system_api.service.impl;

import org.springframework.stereotype.Service;

import de.adrian.ticket_system_api.dto.AuthResponse;
import de.adrian.ticket_system_api.dto.LoginRequest;
import de.adrian.ticket_system_api.dto.RegisterRequest;
import de.adrian.ticket_system_api.entity.User;
import de.adrian.ticket_system_api.repository.UserRepository;
import de.adrian.ticket_system_api.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.findAll().stream()
                .filter(user -> user.getUsername().equals(registerRequest.getUsername()))
                .findFirst()
                .isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findAll().stream()
                .filter(user -> user.getEmail().equals(registerRequest.getEmail()))
                .findFirst()
                .isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword()); // Todo hash password
        user.setEmail(registerRequest.getEmail());
        userRepository.save(user);

        // Generate JWT token (placeholder token, implement JWT generation logic)
        String token = "dummy-jwt-token-for-" + user.getUsername();

        return new AuthResponse(token, user.getUsername(), "USER");
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(loginRequest.getUsername()) && u.getPassword().equals(loginRequest.getPassword()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        // Generate JWT token (placeholder token, implement JWT generation logic)
        String token = "dummy-jwt-token-for-" + user.getUsername();

        return new AuthResponse(token, user.getUsername(), "USER");
    }
}
