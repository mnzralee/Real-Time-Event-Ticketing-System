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

    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Ticket ID: " + id));
    }

    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(Integer id, Ticket updatedTicket) {
        Ticket existingTicket = getTicketById(id);
        existingTicket.setEventName(updatedTicket.getEventName());
        existingTicket.setDescription(updatedTicket.getDescription());
        return ticketRepository.save(existingTicket);
    }

    public void deleteTicket(Integer id) {
        if (!ticketRepository.existsById(id)) {
            throw new IllegalArgumentException("Invalid Ticket ID: " + id);
        }
        ticketRepository.deleteById(id);
    }
}
