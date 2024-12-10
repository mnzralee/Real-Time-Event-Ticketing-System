import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { LeftSidebarComponent } from "./left-sidebar/left-sidebar.component";
import { HttpClientModule } from '@angular/common/http';
import { VendorsComponent } from './components/vendors/vendors.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [LeftSidebarComponent, RouterOutlet, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent {
  title = 'ticketing-system-frontend';
}
