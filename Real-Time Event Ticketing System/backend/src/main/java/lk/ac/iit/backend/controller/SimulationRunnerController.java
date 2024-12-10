package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.service.SimulationRunnerService;
import lk.ac.iit.backend.service.TicketingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/simulation")
public class SimulationRunnerController {

    private final SimulationRunnerService simulationRunnerService;

    private final TicketingLogService ticketingLogService;

    @Autowired
    public SimulationRunnerController(SimulationRunnerService simulationRunnerService, TicketingLogService ticketingLogService) {
        this.simulationRunnerService = simulationRunnerService;
        this.ticketingLogService = ticketingLogService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSimulation() {
        ticketingLogService.saveLog("Requested to Start Simulation");
        simulationRunnerService.startSimulation();
        ticketingLogService.saveLog("Simulation Started");
        return ResponseEntity.ok("Simulation started");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopSimulation() {
        simulationRunnerService.stopSimulation();
        ticketingLogService.saveLog("Simulation Stopped");
        return ResponseEntity.ok("Simulation stopped");
    }


    @GetMapping("/status")
    public ResponseEntity<String> getSimulationStatus() {
        boolean isRunning = SimulationRunnerService.stopFlag.get();
        return ResponseEntity.ok(isRunning ? "Simulation is running" : "Simulation is stopped");
    }


}
