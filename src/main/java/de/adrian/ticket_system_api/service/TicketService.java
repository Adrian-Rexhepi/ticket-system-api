package de.adrian.ticket_system_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import de.adrian.ticket_system_api.entity.Ticket;

public interface TicketService {

    Ticket createTicket(Ticket ticket);

    Page<Ticket> getAllTickets(Pageable pageable);

    Ticket updateTicket(Long id, Ticket ticket);
    
    void deleteTicket(Long id);
}
