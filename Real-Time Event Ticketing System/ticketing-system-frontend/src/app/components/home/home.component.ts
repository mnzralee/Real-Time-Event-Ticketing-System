import { Component, OnInit } from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { Log } from './log.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  socketClient: any = null;

  private logSubscription: any;

  logMessages: String[] = [];
  
  constructor() { }

  ngOnInit(): void {

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
  

  

}
