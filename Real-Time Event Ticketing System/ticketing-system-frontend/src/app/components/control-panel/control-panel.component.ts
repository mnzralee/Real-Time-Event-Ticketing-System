import { Component, OnInit } from '@angular/core';
import { ControlPanelService } from './control-panel.service';

@Component({
  selector: 'app-control-panel',
  standalone: true,
  imports: [],
  templateUrl: './control-panel.component.html',
  styleUrl: './control-panel.component.css'
})
export class ControlPanelComponent implements OnInit {

  constructor(private controlPanelService: ControlPanelService) { }

  ngOnInit(): void {
  }

  startSimulation(): void {
    this.controlPanelService.startSimulation();
  }

  stopSimulation(): void {
    this.controlPanelService.stopSimulation();
  }

}
