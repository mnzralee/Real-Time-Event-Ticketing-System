package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Ticket;
import lk.ac.iit.backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     * Saves a new ticket to the database.
     * @param ticket The ticket to save.
     * @return The saved ticket.
     */
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    /**
     * Retrieves a ticket by its ID.
     * @param id The ID of the ticket.
     * @return The ticket with the given ID.
     * @throws IllegalArgumentException if no ticket is found.
     */
    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Ticket ID: " + id));
    }

    /**
     * Retrieves all tickets from the database.
     * @return A list of all tickets.
     */
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }

    /**
     * Updates an existing ticket.
     * @param id The ID of the ticket to update.
     * @param updatedTicket The new ticket details.
     * @return The updated ticket.
     * @throws IllegalArgumentException if no ticket is found.
     */
    public Ticket updateTicket(Integer id, Ticket updatedTicket) {
        Ticket existingTicket = getTicketById(id);
        existingTicket.setEventName(updatedTicket.getEventName());
        existingTicket.setDescription(updatedTicket.getDescription());
        return ticketRepository.save(existingTicket);
    }

    /**
     * Deletes a ticket by its ID.
     * @param id The ID of the ticket to delete.
     * @throws IllegalArgumentException if no ticket is found.
     */
    public void deleteTicket(Integer id) {
        if (!ticketRepository.existsById(id)) {
            throw new IllegalArgumentException("Invalid Ticket ID: " + id);
        }
        ticketRepository.deleteById(id);
    }
}
