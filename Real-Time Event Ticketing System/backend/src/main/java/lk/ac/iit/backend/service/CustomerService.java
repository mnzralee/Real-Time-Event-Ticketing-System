package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Customer;
import lk.ac.iit.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Adds a new customer to the repository.
     * @param customer The customer to add.
     * @return The saved customer.
     */
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Retrieves a customer by their ID.
     * @param id The ID of the customer to fetch.
     * @return The customer with the given ID.
     * @throws IllegalArgumentException If the ID is invalid.
     */
    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID:" + id));
    }

    /**
     * Retrieves all customers from the repository.
     * @return A list of all customers.
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Updates the details of an existing customer.
     * @param id The ID of the customer to update.
     * @param updatedCustomer The new customer details.
     * @return The updated customer.
     * @throws IllegalArgumentException If the ID is invalid.
     */
    public Customer updateCustomer(Integer id, Customer updatedCustomer) {
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setFirstName(updatedCustomer.getFirstName());
        existingCustomer.setLastName(updatedCustomer.getLastName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        return customerRepository.save(existingCustomer);
    }

    /**
     * Deletes a customer by their ID.
     * @param id The ID of the customer to delete.
     * @throws IllegalArgumentException If the ID is invalid.
     */
    public void deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)){
            throw new IllegalArgumentException("Invalid ID: " + id);
        }
        customerRepository.deleteById(id);
    }

}
