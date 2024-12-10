package lk.ac.iit.backend.service;

import jakarta.transaction.Transactional;
import lk.ac.iit.backend.model.Customer;
import lk.ac.iit.backend.model.TicketingLog;
import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.repository.TicketingLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketingLogService {

    private final TicketingLogRepository ticketingLogRepository;
    private final SimpMessagingTemplate messagingTemplate;

    private final Logger logger = LoggerFactory.getLogger(TicketingLogService.class);

    @Autowired
    public TicketingLogService(TicketingLogRepository ticketingLogRepository, SimpMessagingTemplate messagingTemplate) {
        this.ticketingLogRepository = ticketingLogRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public void saveLog(String message, Customer customer) {
        TicketingLog log = new TicketingLog();
        message = "Customer " + customer.getFirstName() + message;
        log.setMessage(message);
//        log.setTimestamp(LocalDateTime.now());
        ticketingLogRepository.save(log);
        logger.info(message);

        // Send the log message to WebSocket clients
        messagingTemplate.convertAndSend("/topic/logs", log);
    }

    public void saveLog(String message, Vendor vendor) {
        TicketingLog log = new TicketingLog();
        message = "Vendor " + vendor.getFirstName() + message;
        log.setMessage(message);
//        log.setTimestamp(LocalDateTime.now());
        ticketingLogRepository.save(log);
        logger.info(message);

        // Send the log message to WebSocket clients
        messagingTemplate.convertAndSend("/topic/logs", log);
    }

    public List<TicketingLog> getAllLogs() {
        return ticketingLogRepository.findAllByOrderByIdDesc();
    }

    @Transactional
    public void clearAllLogs() {
        ticketingLogRepository.deleteAllInBatch();
    }

    public void saveLog(String message) {
        TicketingLog log = new TicketingLog();
        log.setMessage("System: " + message);
//        log.setTimestamp(LocalDateTime.now());
        ticketingLogRepository.save(log);
        logger.info("System: {}", message);

        // Send the log message to WebSocket clients
        messagingTemplate.convertAndSend("/topic/logs", log);
    }
}
