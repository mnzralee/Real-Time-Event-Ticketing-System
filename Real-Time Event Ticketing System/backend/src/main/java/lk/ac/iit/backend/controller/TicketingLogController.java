package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.TicketingLog;
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

    /**
     * Retrieves all ticketing logs in the system.
     * This method handles the HTTP GET request to fetch a list of all ticketing logs.
     *
     * @return A list of all ticketing log objects currently in the system.
     */
    @GetMapping
    public List<TicketingLog> getAllTicketingLogs() {
        return ticketingLogService.getAllLogs();
    }

    /**
     * Clears all ticketing logs in the system.
     * This method handles the HTTP DELETE request to remove all logs from the system.
     *
     * @return A ResponseEntity indicating that the logs have been cleared successfully.
     */
    @DeleteMapping
    public ResponseEntity<String> clearAllLogs() {
        ticketingLogService.clearAllLogs();
        return ResponseEntity.ok("All logs cleared.");
    }
}

