import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vendor } from './vendor.model';

@Injectable({
  providedIn: 'root'
})

export class VendorService {
  // URL to the REST API
  private apiUrl = 'http://localhost:8080/api/vendors';

  // Inject the HttpClient service
  constructor(private httpClient: HttpClient) {}

  // create a new vendor
  createVendor(newVendor: Vendor): Observable<Vendor> {
    // POST request to create a new vendor
    return this.httpClient.post<Vendor>(this.apiUrl, newVendor);
  }

  // get all vendors
  getAllVendors(): Observable<Vendor[]> {
    // GET request to get all vendors
    return this.httpClient.get<Vendor[]>(this.apiUrl);
  }

  // delete a vendor
  deleteVendor(id: number): void {
    // DELETE request to delete a vendor
    this.httpClient.delete(this.apiUrl + '/' + id).subscribe();
  }

}
