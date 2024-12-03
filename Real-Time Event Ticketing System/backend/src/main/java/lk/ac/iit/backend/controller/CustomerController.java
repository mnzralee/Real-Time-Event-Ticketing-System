package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.Customer;
import lk.ac.iit.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer addCustomer(Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Integer id, Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }

}
