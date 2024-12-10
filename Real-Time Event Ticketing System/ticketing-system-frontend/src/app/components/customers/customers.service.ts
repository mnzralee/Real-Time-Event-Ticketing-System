import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customers } from './customers.model';

@Injectable({
  providedIn: 'root'
})
export class CustomersService {
  // URL to the REST API
  private apiUrl = 'http://localhost:8080/api/customers';

  constructor(private httpClient: HttpClient) { }

  createCustomer(newCustomer: Customers): Observable<Customers> {
    return this.httpClient.post<Customers>(this.apiUrl, newCustomer);
  }

  getAllCustomers(): Observable<Customers[]> {
    return this.httpClient.get<Customers[]>(this.apiUrl);
  }

  deleteCustomer(id: number): void {
    this.httpClient.delete(this.apiUrl + '/' + id).subscribe();
  }


}
