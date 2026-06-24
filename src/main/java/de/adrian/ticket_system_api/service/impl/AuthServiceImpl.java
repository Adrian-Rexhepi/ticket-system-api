package de.adrian.ticket_system_api.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import de.adrian.ticket_system_api.dto.AuthResponse;
import de.adrian.ticket_system_api.dto.LoginRequest;
import de.adrian.ticket_system_api.dto.RegisterRequest;
import de.adrian.ticket_system_api.entity.User;
import de.adrian.ticket_system_api.enums.UserRole;
import de.adrian.ticket_system_api.exception.InvalidCredentialsException;
import de.adrian.ticket_system_api.exception.UserAlreadyExistsException;
import de.adrian.ticket_system_api.repository.UserRepository;
import de.adrian.ticket_system_api.security.JwtService;
import de.adrian.ticket_system_api.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setRole(UserRole.USER);
        userRepository.save(user);

        return new AuthResponse(jwtService.generateToken(user), user.getUsername(), user.getRole().toString());
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
            .orElseThrow(() ->
                new InvalidCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return new AuthResponse(jwtService.generateToken(user), user.getUsername(), user.getRole().toString());
    }

}
