package de.adrian.ticket_system_api.dto;

import de.adrian.ticket_system_api.enums.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTicketRequest {

    private String title;

    private String description;

    private TicketStatus status;
}
