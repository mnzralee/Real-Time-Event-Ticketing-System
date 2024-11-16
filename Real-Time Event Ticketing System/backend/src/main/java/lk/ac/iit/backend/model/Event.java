package lk.ac.iit.backend.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

// anything that is an entity will be stored in database
@Entity
public class Event {

    // auto generated id value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String eventName;
    private Date eventDate;
    private String eventLocation;

    private int totalTickets;
    private int maxCapacity;
    private int ticketReleaseRate;
    private int ticketsPerRelease;
    private int ticketsSold;

    /**
     *  Many-to-One relationship with Vendor
     *  fetch = FetchType.LAZY: The Vendor data will only be loaded from the database when it's accessed in the code.
     *  This can improve performance by avoiding unnecessary data loading.
     *  @JoinColumn (name = "vendorId") specifies the foreign key column name in the Event table to reference Vendor.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    /**
     *  One-to-Many relationship with Ticket.
     *  mappedBy = "event" specifies that the "event" field in Ticket is responsible for managing this relationship.
     *  This makes Event the inverse side, and Ticket the owner side of the relationship.
     */
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    public void generateTickets(){
        for (int i = 0; i < getTotalTickets(); i++){
            Ticket ticket = new Ticket();
            ticket.setTicketStatus("AVAILABLE");
            ticket.setEvent(this);
            tickets.add(ticket);
        }
    }

    // Getters and Setters

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getTicketsPerRelease() {
        return ticketsPerRelease;
    }

    public void setTicketsPerRelease(int ticketsPerRelease) {
        this.ticketsPerRelease = ticketsPerRelease;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold = ticketsSold;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
