package de.adrian.ticket_system_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.adrian.ticket_system_api.dto.LoginRequest;
import de.adrian.ticket_system_api.dto.RegisterRequest;
import de.adrian.ticket_system_api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest registerRequest) throws Exception {

        return authService.register(registerRequest).getToken();
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginRequest loginRequest) throws Exception {

        return authService.login(loginRequest).getToken();
    }
}
