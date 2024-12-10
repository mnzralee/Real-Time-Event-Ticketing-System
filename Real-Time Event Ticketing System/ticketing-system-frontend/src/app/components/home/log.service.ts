import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Log } from './log.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  private apiUrl = 'http://localhost:8080/api/logs';


  constructor(private httpClient: HttpClient) {}


    // get all logs
    getAllLogs(): Observable<Log[]> {
      // GET request to get all Logs
      return this.httpClient.get<Log[]>(this.apiUrl);
    }
  
    // delete all logs
    clearAllLogs(): void {
      // DELETE request to delete all logs
      this.httpClient.delete(this.apiUrl).subscribe();
    }
}
