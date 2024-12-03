package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.Ticket;
import lk.ac.iit.backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public Ticket addTicket(Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Integer id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTicket();
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Integer id, Ticket ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }
}
