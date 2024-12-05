package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Customer;
import lk.ac.iit.backend.model.Ticket;
import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TicketPoolService {

    private final TicketRepository ticketRepository;
    private final TicketingLogService ticketingLogService;

    private final ConcurrentLinkedQueue<Ticket> ticketQueue = new ConcurrentLinkedQueue<>();

    private final Logger logger = Logger.getLogger(TicketPoolService.class.getName());
    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    private int maxCapacity = 10; // default 10

    @Autowired
    public TicketPoolService(TicketRepository ticketRepository, TicketingLogService ticketingLogService) {
        this.ticketRepository = ticketRepository;
        this.ticketingLogService = ticketingLogService;
    }


    public void addTicket(Vendor vendor, Ticket ticket) {
        lock.lock();
        try {
            while (ticketQueue.size() >= maxCapacity) {
                logger.log(Level.INFO, Thread.currentThread().getName() + ": Waiting.. Ticket pool is full");
                notFull.await();
            }

            ticket.setVendor(vendor);
            ticket.setStatus("Available");
            ticket = ticketRepository.save(ticket); // Persist before adding to queue
            ticketQueue.add(ticket);
            logger.log(Level.INFO, vendor.getFirstName() + ": Added ticket. Pool size: " + ticketQueue.size());
            ticketingLogService.saveLog(vendor.getFirstName() + ": Added a ticket to the pool. Tickets in pool - " + ticketQueue.size(), vendor);
            notEmpty.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while adding ticket", e);
        } finally {
            lock.unlock();
        }
    }

    public void buyTicket(Customer customer) {
        lock.lock();
        try {
            while (ticketQueue.isEmpty()) {
                logger.log(Level.INFO, Thread.currentThread().getName() + ": Waiting.. Ticket pool is full");
                notEmpty.await();
            }
            Ticket ticket = ticketQueue.poll();
            if (ticket != null) {
                ticket.setCustomer(customer);
                ticket.setStatus("SOLD");
                ticketRepository.save(ticket); // Persist the sold status
            }
            logger.log(Level.INFO, customer.getFirstName() + ": Purchased a ticket. Pool size: " + ticketQueue.size());
            ticketingLogService.saveLog(customer.getFirstName() + ": Added a ticket to the pool. Tickets in pool - " + ticketQueue.size(), customer);
            notFull.signal();
        } catch (InterruptedException e) {
            logger.log(Level.INFO, Thread.currentThread().getName() + ": Interrupted");
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while buying ticket", e);
        } finally {
            lock.unlock();
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
