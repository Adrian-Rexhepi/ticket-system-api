package de.adrian.ticket_system_api.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    @NotBlank
    private String message;

    @NotBlank
    private int status;

    @NotBlank
    private LocalDateTime timestamp;

}
