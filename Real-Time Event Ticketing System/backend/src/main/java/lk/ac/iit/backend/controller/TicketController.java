package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.Ticket;
import lk.ac.iit.backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Creates and adds a new ticket to the system.
     * This method handles the HTTP POST request to create a ticket.
     *
     * @param ticket The ticket object to be created. It is passed in the request body.
     * @return The created ticket object, including the generated ID.
     */
    @PostMapping
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    /**
     * Retrieves a ticket by its unique ID.
     * This method handles the HTTP GET request to fetch a ticket by ID.
     *
     * @param id The ID of the ticket to be retrieved from the database.
     * @return The ticket object corresponding to the provided ID.
     */
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Integer id) {
        return ticketService.getTicketById(id);
    }

    /**
     * Retrieves all tickets in the system.
     * This method handles the HTTP GET request to fetch a list of all tickets.
     *
     * @return A list of all ticket objects currently in the system.
     */
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTicket();
    }

    /**
     * Updates an existing ticket in the system.
     * This method handles the HTTP PUT request to update a ticket's details.
     *
     * @param id The ID of the ticket to be updated.
     * @param ticket The updated ticket object with new information.
     * @return The updated ticket object after modification.
     */
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Integer id, @RequestBody Ticket ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    /**
     * Deletes a ticket from the system.
     * This method handles the HTTP DELETE request to remove a ticket by its ID.
     *
     * @param id The ID of the ticket to be deleted from the system.
     */
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }
}

