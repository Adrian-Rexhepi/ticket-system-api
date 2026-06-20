package de.adrian.ticket_system_api.dto;

import de.adrian.ticket_system_api.enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketResponse {

    @NotBlank
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private TicketStatus status;
}
