package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Customer;
import lk.ac.iit.backend.model.TicketingLog;
import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.repository.TicketingLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketingLogService {

    private final TicketingLogRepository ticketingLogRepository;

    @Autowired
    public TicketingLogService(TicketingLogRepository ticketingLogRepository) {
        this.ticketingLogRepository = ticketingLogRepository;
    }

    public TicketingLog saveLog(String message, Customer customer) {
        TicketingLog log = new TicketingLog();
        log.setMessage(message);
        log.setCustomer(customer);
        log.setTimestamp(LocalDateTime.now());
        return ticketingLogRepository.save(log);
    }

    public TicketingLog saveLog(String message, Vendor vendor) {
        TicketingLog log = new TicketingLog();
        log.setMessage(message);
        log.setVendor(vendor);
        log.setTimestamp(LocalDateTime.now());
        return ticketingLogRepository.save(log);
    }
}
