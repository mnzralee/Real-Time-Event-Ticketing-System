import { Component, OnInit } from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { Log } from './log.model';
import { CommonModule } from '@angular/common';
import { LogService } from './log.service';
import { ControlPanelComponent } from '../control-panel/control-panel.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ControlPanelComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  socketClient: any = null;

  private logSubscription: any;

  logMessages: String[] = [];
  
  constructor(private logService: LogService) { }

  ngOnInit(): void {

    this.getAllLogs();

    // create a new SockJS instance
    let ws = new SockJS('http://localhost:8080/ws');
    this.socketClient = Stomp.over(ws);
    this.socketClient.connect({}, () => {
      this.logSubscription = this.socketClient.subscribe(
        '/topic/logs', 
        (message: any) => {
          const log: Log = JSON.parse(message.body);
          if (log) {
            // add the log message to the beginning of the array
            this.logMessages.unshift(log.message);
          }
        }
      )
    });
    
  }

  // method to get all logs
  getAllLogs(): void {
    this.logService.getAllLogs().subscribe({
      next: (logs) => this.logMessages = logs.map((log) => log.message),
      error: (err) => console.error('Error fetching vendors:', err),
    });

  }

  // method to delete all logs
  clearAllLogs(): void {
    if(confirm('Are you sure you want to clear all logs?')) {
      this.logService.clearAllLogs();
      this.logMessages = [];
    }
  }
  

  

}
