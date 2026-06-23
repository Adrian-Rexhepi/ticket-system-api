package de.adrian.ticket_system_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    @NotBlank
    private String token;

    @NotBlank
    private String username;

    @NotBlank
    private String role;
}
