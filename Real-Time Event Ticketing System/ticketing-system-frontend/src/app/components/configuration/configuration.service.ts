import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Config } from './config.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
    // URL to the REST API
    private apiUrl = 'http://localhost:8080/api/config';

    constructor(private httpClient: HttpClient) { }

    // create a new vendor
    createConfig(newconfig: Config): Observable<Config> {
      // POST request to create a new vendor
      return this.httpClient.post<Config>(this.apiUrl, newconfig);
    }
  
    // get configurations
    getConfig(): Observable<Config> {
      // GET request to get all vendors
      return this.httpClient.get<Config>(this.apiUrl);
    }
}
