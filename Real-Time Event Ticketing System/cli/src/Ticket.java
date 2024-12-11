import java.math.BigDecimal;

public class Ticket {

    private int ticketId;
    private String eventName;
    private String eventDate;
    private String eventLocation;
    private BigDecimal ticketPrice; // Use BigDecimal for prices to avoid precision issues

    // CONSTRUCTOR

    /**
     * Constructs a Ticket with the specified details.
     *
     * @param eventName the name of the event
     * @param eventDate the date of the event
     * @param eventLocation the location of the event
     * @param ticketPrice the price of the ticket
     */
    public Ticket(String eventName, String eventDate, String eventLocation, BigDecimal ticketPrice) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.ticketPrice = ticketPrice;
    }

    // GETTERS AND SETTERS

    /**
     * Gets the ticket ID.
     *
     * @return the ticket ID
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * Sets the ticket ID.
     *
     * @param ticketId the ticket ID to set
     */
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * Gets the event name.
     *
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the event name.
     *
     * @param eventName the event name to set
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * Gets the event date.
     *
     * @return the event date
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * Sets the event date.
     *
     * @param eventDate the event date to set
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Gets the event location.
     *
     * @return the event location
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * Sets the event location.
     *
     * @param eventLocation the event location to set
     */
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    /**
     * Gets the ticket price.
     *
     * @return the ticket price
     */
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Sets the ticket price.
     *
     * @param ticketPrice the ticket price to set
     */
    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /**
     * Returns a string representation of the Ticket object.
     *
     * @return string representation of the Ticket
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId= " + ticketId +
                ", eventName= '" + eventName + '\'' +
                ", eventDate= '" + eventDate + '\'' +
                ", eventLocation= '" + eventLocation + '\'' +
                ", ticketPrice= " + ticketPrice +
                '}';
    }
}
