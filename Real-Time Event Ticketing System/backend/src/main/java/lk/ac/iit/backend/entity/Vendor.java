package lk.ac.iit.backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorId;
    private String vendorName;
    private String vendorEmail;

    /**
     One-to-Many relationship with Event.
     mappedBy = "vendor" indicates that the "vendor" field in the Event entity owns this relationship.
     This means that Vendor is not responsible for managing the foreign key in the database; Event is.
     CascadeType.ALL: When a Vendor is saved or deleted, associated Events are also saved or deleted.
     orphanRemoval = true: When an Event is removed from the list, it will be deleted from the database.
     */
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


}
