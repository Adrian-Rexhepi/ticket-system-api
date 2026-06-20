package de.adrian.ticket_system_api.dto;

import de.adrian.ticket_system_api.enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTicketRequest {

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private TicketStatus status;
}
