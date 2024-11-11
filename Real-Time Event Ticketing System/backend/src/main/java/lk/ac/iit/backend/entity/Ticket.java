package lk.ac.iit.backend.entity;

import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    /**
     *  Many-to-One relationship with Event
     *  fetch = FetchType.LAZY: The Event data will only be loaded from the database when it's accessed in the code.
     *  This can improve performance by avoiding unnecessary data loading.
     *  @JoinColumn (name = "event_id") specifies the foreign key column name in the Ticket table to reference Event.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    // Getter and Setters

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
