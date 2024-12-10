import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ControlPanelService {
    // URL to the REST API
    private apiUrl = 'http://localhost:8080/api/simulation';

  constructor(private httpClient: HttpClient) { }

  // start the simulation
  startSimulation(): void {
    this.httpClient.post(this.apiUrl + '/start', {}).subscribe();
  }

  // stop the simulation
  stopSimulation(): void {
    this.httpClient.post(this.apiUrl + '/stop', {}).subscribe();
  }
}
