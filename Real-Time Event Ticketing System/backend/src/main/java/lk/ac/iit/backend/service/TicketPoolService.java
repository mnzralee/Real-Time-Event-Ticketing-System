package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Customer;
import lk.ac.iit.backend.model.Ticket;
import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

@Service
public class TicketPoolService {

    private final TicketRepository ticketRepository;
    private final TicketingLogService ticketingLogService;

    private final ConcurrentLinkedQueue<Ticket> ticketQueue = new ConcurrentLinkedQueue<>();

    private final ReentrantLock lock = new ReentrantLock();

    private final Logger logger = LoggerFactory.getLogger(TicketPoolService.class);

    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    private int maxCapacity = 10; // default 10

    @Autowired
    public TicketPoolService(TicketRepository ticketRepository, TicketingLogService ticketingLogService) {
        this.ticketRepository = ticketRepository;
        this.ticketingLogService = ticketingLogService;
    }


    /**
     * Adds a ticket to the pool. Waits if the pool is full.
     * @param vendor The vendor adding the ticket.
     * @param ticket The ticket to add.
     */
    public void addTicket(Vendor vendor, Ticket ticket) {
        lock.lock();
        try {
            while (ticketQueue.size() >= maxCapacity) {
//                logger.log(Level.INFO, vendor.getFirstName()  + ": Waiting.. Ticket pool is full");
                ticketingLogService.saveLog(": Waiting.. Ticket pool is full", vendor);
                notFull.await();
            }

            ticket.setVendor(vendor);
            ticket.setStatus("Available");
            ticket = ticketRepository.save(ticket); // Persist before adding to queue
            ticketQueue.add(ticket);
//            logger.log(Level.INFO, vendor.getFirstName() + ": Added ticket. Pool size: " + ticketQueue.size());
            ticketingLogService.saveLog(": Added a ticket to the pool. Tickets in pool - " + ticketQueue.size(), vendor);
            notEmpty.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while adding ticket", e);
        } finally {
            lock.unlock();
        }
    }


    /**
     * Purchases a ticket from the pool. Waits if the pool is empty.
     * @param customer The customer buying the ticket.
     */
    public void buyTicket(Customer customer) {
        lock.lock();
        try {
            while (ticketQueue.isEmpty()) {
//                logger.log(Level.INFO, customer.getFirstName() +  ": Waiting.. Ticket pool is empty");
                ticketingLogService.saveLog(": Waiting.. Ticket pool is empty", customer);
                notEmpty.await();
            }
            Ticket ticket = ticketQueue.poll();
            if (ticket != null) {
                ticket.setCustomer(customer);
                ticket.setStatus("SOLD");
                ticketRepository.save(ticket); // Persist the sold status
            }
//            logger.log(Level.INFO, customer.getFirstName() + ": Purchased a ticket. Pool size: " + ticketQueue.size());
            ticketingLogService.saveLog(": Purchased a ticket from the pool. Tickets in pool - " + ticketQueue.size(), customer);
            notFull.signal();
        } catch (InterruptedException e) {
            logger.error("{}: Interrupted", Thread.currentThread().getName());
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while buying ticket", e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Gets the maximum capacity of the ticket pool.
     * @return The maximum capacity.
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Sets the maximum capacity of the ticket pool.
     * @param maxCapacity The new maximum capacity.
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
