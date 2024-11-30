package lk.ac.iit.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String eventName;
    private String description;
    private String location;
    private Date date;
    private String eventStatus;

    /**
     * one-to-one relationship
     * Vendor for the event
     */
    @OneToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    /**
     * one-to-one relationship
     * Ticket pool for the event
     */
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;


    // CONSTRUCTOR

    public Event() {};

    public Event(Vendor vendor, Ticket ticket, String eventName, String description, String location, Date date) {
        this.vendor = vendor;
        this.ticket = ticket;
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.date = date;
    }


    // GETTERS and SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
