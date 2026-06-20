package de.adrian.ticket_system_api.service;

import de.adrian.ticket_system_api.dto.AuthResponse;
import de.adrian.ticket_system_api.dto.LoginRequest;
import de.adrian.ticket_system_api.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
