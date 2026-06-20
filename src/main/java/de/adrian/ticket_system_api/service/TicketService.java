package de.adrian.ticket_system_api.service;

import java.util.List;
import de.adrian.ticket_system_api.entity.Ticket;

public interface TicketService {

    Ticket createTicket(Ticket ticket);

    List<Ticket> getAllTickets();

    Ticket updateTicket(Long id, Ticket ticket);
    
    void deleteTicket(Long id);
}
