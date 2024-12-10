import { Component, OnInit } from '@angular/core';
import { CustomersService } from './customers.service';
import { Customers } from './customers.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{

    newCustomer: Customers = {firstName: '', lastName: '', email: ''};
    customers: Customers[] = [];
  
    constructor(private customerService: CustomersService) { }
  
    ngOnInit(): void {
      this.getAllCustomers();
    }
    
    // method to create a customer
    createCustomer(customerForm: any): void {
      this.customerService.createCustomer(this.newCustomer).subscribe({
          next: (createdCustomer) => {
              this.newCustomer = { firstName: '', lastName: '', email: '' }; // Reset form model
              this.customers.push(createdCustomer); // Add new customer to customers list
              customerForm.resetForm(); // Reset form state
              alert('Customer added successfully!');
          },
          error: (err) => {
              console.error('Error adding customer:', err);
              alert('Failed to add customer. Please try again.');
          }
      });
      this.getAllCustomers();
    }
  
    // method to get all customers
    getAllCustomers(): void {
      this.customerService.getAllCustomers().subscribe({
        next: (customers) => this.customers = customers,
        error: (err) => console.error('Error fetching customers:', err),
      });
    }
  
    // method to delete a customer
    deleteCustomer(id: any): void {
      if(confirm('Are you sure you want to delete this customer?')) {
        this.customerService.deleteCustomer(id);
        this.customers = this.customers.filter((customer) => customer.id !== id); // Remove deleted customer from customers list
      }
    }


}
