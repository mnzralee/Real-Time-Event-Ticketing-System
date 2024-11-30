package lk.ac.iit.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    /**
     * one-to-one relationship
     * Vendor - Event
     */
    @OneToOne
    @JoinColumn(name = "event_id")
    private Event event;

    /**
     * one-to-many relationship
     * List of Logs for Vendor
     */
    @OneToMany(mappedBy = "vendor")
    private List<TicketingLog> ticketingLogs;


    // CONSTRUCTOR

    public Vendor() {}

    public Vendor(String firstName, String lastName, String email) {
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<TicketingLog> getTicketingLogs() {
        return ticketingLogs;
    }

    public void setTicketingLogs(List<TicketingLog> ticketingLogs) {
        this.ticketingLogs = ticketingLogs;
    }
}
