package de.adrian.ticket_system_api.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;
import de.adrian.ticket_system_api.dto.AuthResponse;
import de.adrian.ticket_system_api.dto.LoginRequest;
import de.adrian.ticket_system_api.dto.RegisterRequest;
import de.adrian.ticket_system_api.entity.User;
import de.adrian.ticket_system_api.repository.UserRepository;
import de.adrian.ticket_system_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponse register(RegisterRequest registerRequest) throws NoSuchAlgorithmException {
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
        user.setPassword(hashPassword(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        userRepository.save(user);

        return new AuthResponse(generateToken(user), user.getUsername(), "USER");
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) throws NoSuchAlgorithmException {
        String hashedPassword = hashPassword(loginRequest.getPassword());
        User user = userRepository.findAll().stream()
            .filter(u -> u.getUsername().equals(loginRequest.getUsername()) && u.getPassword().equals(hashedPassword))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        return new AuthResponse(generateToken(user), user.getUsername(), "USER");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash = md.digest(password.getBytes());

        BigInteger bitInt = new BigInteger(1, hash);

        return bitInt.toString(16);
    }

    private String generateToken(User user) {
        
         String token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim("role", "USER")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
                .sign(Algorithm.HMAC256("secret"));

        return token;
    }
}
