package lk.ac.iit.backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
public class TicketingLog {
    @Id
    @GeneratedValue
    private Integer id;

    private String message;

//    @Column(nullable = false)
//    private LocalDateTime timestamp = LocalDateTime.now();
//
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Vendor vendor;




    // CONSTRUCTOR

    public TicketingLog() {};

    public TicketingLog(String message) {
        this.message = message;

    }


    // GETTER and SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }


    @Override
    public String toString() {
        return "TicketingLog{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
