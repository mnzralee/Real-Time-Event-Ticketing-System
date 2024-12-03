package lk.ac.iit.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;


    @OneToMany(mappedBy = "customer")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "customer")
    private List<TicketingLog> ticketingLogs;


    // CONSTRUCTOR

    public Customer() {};

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // GETTERS and SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<TicketingLog> getTicketingLogs() {
        return ticketingLogs;
    }

    public void setTicketingLogs(List<TicketingLog> ticketingLogs) {
        this.ticketingLogs = ticketingLogs;
    }
}
