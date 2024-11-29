import java.math.BigDecimal;
import java.util.Date;

public class Ticket {

    private int ticketId;

    private String eventName;

    private String eventDate;

    private String eventLocation;

    private BigDecimal ticketPrice; // Use big decimal when dealing with prices


    // CONSTRUCTOR

    public Ticket(String eventName, String eventDate, String eventLocation, BigDecimal ticketPrice) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.ticketPrice = ticketPrice;
    }


    // GETTER n SETTERS

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

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
