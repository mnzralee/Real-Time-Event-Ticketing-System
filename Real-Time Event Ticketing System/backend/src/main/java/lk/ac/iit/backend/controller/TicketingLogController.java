package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.TicketingLog;
import lk.ac.iit.backend.service.TicketPoolService;
import lk.ac.iit.backend.service.TicketingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/logs")
public class TicketingLogController {

    private final TicketingLogService ticketingLogService;

    @Autowired
    public TicketingLogController(TicketingLogService ticketingLogService) {
        this.ticketingLogService = ticketingLogService;
    }

    @GetMapping
    public List<TicketingLog> getAllTicketingLogs() {
        return ticketingLogService.getAllLogs();
    }

    @DeleteMapping
    public ResponseEntity<String> clearAllLogs() {
        ticketingLogService.clearAllLogs();
        return ResponseEntity.ok("All logs cleared.");
    }


}
