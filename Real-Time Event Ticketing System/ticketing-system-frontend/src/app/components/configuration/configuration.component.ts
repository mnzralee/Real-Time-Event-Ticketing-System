import { CommonModule } from '@angular/common';
import { Component, NgModule, OnInit } from '@angular/core';
import { ConfigurationService } from './configuration.service';
import { Config } from './config.model';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-configuration',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css'] 
})
export class ConfigurationComponent implements OnInit {


  newConfig: Config = { totalTickets: 0, ticketReleaseRate: 0, customerRetrievalRate: 0, maxTicketCapacity: 0 };
  config: Config = { totalTickets: 0, ticketReleaseRate: 0, customerRetrievalRate: 0, maxTicketCapacity: 0 };

  constructor(private configService: ConfigurationService) {}

  ngOnInit(): void {
    this.getConfig(); // Fetch initial configuration on load
  }

  createConfig(configForm: any): void {
      this.configService.createConfig(this.newConfig).subscribe({
          next: (createdConfig) => {
              this.newConfig = { totalTickets: 0, ticketReleaseRate: 0, customerRetrievalRate: 0, maxTicketCapacity: 0 }; // Reset form model
              this.config = createdConfig; // Update the current configuration
              configForm.resetForm(); // Reset form state
              alert('Configuration added successfully!');
          },
          error: (err) => {
              console.error('Error adding configuration:', err);
              alert('Failed to add configuration. Please try again.');  
          }
      });
      this.getConfig(); // Fetch updated configuration
  }
  

  // Fetch the current configuration
  getConfig(): void {
    this.configService.getConfig().subscribe((config) => {
      if (Array.isArray(config) && config.length > 0) {
        this.config = config[0]; // Extract the first element from the array
      } else {
        console.warn('No configuration found.');
      }
      console.log('Configuration fetched: ', this.config);
    });
  }
}
