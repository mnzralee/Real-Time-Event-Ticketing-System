import { Component, OnInit } from '@angular/core';
import { ControlPanelService } from './control-panel.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-control-panel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './control-panel.component.html',
  styleUrl: './control-panel.component.css'
})
export class ControlPanelComponent implements OnInit {

  isSimulationRunning: boolean = false;

  constructor(private controlPanelService: ControlPanelService) { }

  ngOnInit(): void {
  }

  startSimulation(): void {
    this.controlPanelService.startSimulation();
    this.isSimulationRunning = true;
  }

  stopSimulation(): void {
    this.controlPanelService.stopSimulation();
    this.isSimulationRunning = false;
  }

}
