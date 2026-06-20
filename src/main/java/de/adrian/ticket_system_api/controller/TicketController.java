package de.adrian.ticket_system_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.adrian.ticket_system_api.dto.CreateTicketRequest;
import de.adrian.ticket_system_api.dto.TicketResponse;
import de.adrian.ticket_system_api.entity.Ticket;
import de.adrian.ticket_system_api.enums.TicketStatus;
import de.adrian.ticket_system_api.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public TicketResponse createTicket(@RequestBody @Valid CreateTicketRequest ticketRequest) {
        Ticket ticket = new Ticket();
        
        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setStatus(TicketStatus.OPEN);

        Ticket createdTicket = ticketService.createTicket(ticket);
        
        return new TicketResponse(createdTicket.getId(), 
        createdTicket.getTitle(), 
        createdTicket.getDescription(), 
        createdTicket.getStatus());
    }
}
