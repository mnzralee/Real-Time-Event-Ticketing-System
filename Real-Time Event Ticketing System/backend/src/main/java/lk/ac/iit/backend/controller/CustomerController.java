package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.Customer;
import lk.ac.iit.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Constructor for CustomerController.
     *
     * @param customerService The service layer for handling customer operations.
     */
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Adds a new customer to the system.
     *
     * @param customer The customer object to be added.
     * @return The saved customer object.
     */
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    /**
     * Retrieves a specific customer by their ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return The customer object with the specified ID.
     */
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    /**
     * Retrieves all customers from the system.
     *
     * @return A list of all customer objects.
     */
    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    /**
     * Updates an existing customer by their ID.
     *
     * @param id       The ID of the customer to update.
     * @param customer The updated customer object.
     * @return The updated customer object.
     */
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    /**
     * Deletes a specific customer by their ID.
     *
     * @param id The ID of the customer to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }

}
