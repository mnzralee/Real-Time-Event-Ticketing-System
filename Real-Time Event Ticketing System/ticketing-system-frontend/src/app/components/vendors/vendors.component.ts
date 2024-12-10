import { CommonModule, NgClass, NgFor, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { VendorService } from './vendor.service';
import { Vendor } from './vendor.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-vendors',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vendors.component.html',
  styleUrls: ['./vendors.component.css']
})
export class VendorsComponent implements OnInit {

  // dependency injection
  constructor(private vendorService: VendorService) { }

  newVendor: Vendor = {firstName: '', lastName: '', email: ''};
  vendors: Vendor[] = [];

  ngOnInit(): void {
    this.getAllVendors();
  }


  /// 'subscribe' will wait till it gets the observable Vendor data

  // method to create a vendor

  

  createVendor(vendorForm: any): void {
    this.vendorService.createVendor(this.newVendor).subscribe({
        next: (createVendor) => {
            this.newVendor = { firstName: '', lastName: '', email: '' }; // Reset form model
            this.vendors.push(createVendor); // Add new vendor to vendors list
            vendorForm.resetForm(); // Reset form state
            alert('Vendor added successfully!');
        },
        error: (err) => {
            console.error('Error adding vendor:', err);
            alert('Failed to add vendor. Please try again.');
        }
    });
    this.getAllVendors();
  }


  // method to get all vendor
  getAllVendors(): void {
    this.vendorService.getAllVendors().subscribe({
      next: (vendors) => this.vendors = vendors,
      error: (err) => console.error('Error fetching vendors:', err),
    });

  }

  // method to delete a vendor
  deleteVendor(id: any): void {
    if(confirm('Are you sure you want to delete this vendor?')) {
      this.vendorService.deleteVendor(id);
      this.vendors = this.vendors.filter((vendor) => vendor.id !== id);
    }
  }
 

}
