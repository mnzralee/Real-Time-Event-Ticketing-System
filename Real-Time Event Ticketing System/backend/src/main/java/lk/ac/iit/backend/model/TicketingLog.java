package lk.ac.iit.backend.model;

import jakarta.persistence.*;

@Entity
public class TicketingLog {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;


    // CONSTRUCTOR

    public TicketingLog() {};

    public TicketingLog(String description, Customer customer, Vendor vendor) {
        this.description = description;
        this.customer = customer;
        this.vendor = vendor;
    }


    // GETTER and SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
