package de.adrian.ticket_system_api.dto;

import de.adrian.ticket_system_api.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TicketResponse {

    private Long id;

    private String title;

    private String description;

    private TicketStatus status;
}
