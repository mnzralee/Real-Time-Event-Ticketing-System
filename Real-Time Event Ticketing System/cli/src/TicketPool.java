import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketPool {

    private final int maxCapacity; // Maximum capacity of tickets in the pool
    private final Queue<Ticket> ticketQueue; // Queue to manage tickets in FIFO order
    private int releasedTicketCount = 0; // Counter for total released tickets

    // Logger used to maintain an organized logging in CLI
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());

    // ReentrantLock used to ensure thread-safe access
    private final ReentrantLock lock = new ReentrantLock();

    // Conditions to notify threads
    private final Condition notEmpty = lock.newCondition(); // Notify when pool is not empty
    private final Condition notFull = lock.newCondition(); // Notify when pool is not full

    // Constructor to initialize the pool based on configuration
    public TicketPool(Configuration config) {
        this.maxCapacity = config.getMaxTicketCapacity(); // Set max capacity from config
        this.ticketQueue = new LinkedList<>(); // Initialize the ticket queue
    }

    // Getters and Setters

    public int getMaxCapacity() {
        return maxCapacity; // Return the max ticket capacity
    }

    public Queue<Ticket> getTicketQueue() {
        return ticketQueue; // Return the
        // current ticket queue
    }

    public int getReleasedTicketCount() {
        lock.lock();
        try {
            return releasedTicketCount; // Return the count of released tickets
        } finally {
            lock.unlock(); // Release the lock after fetching the count
        }
    }

    public void setReleasedTicketCount(int releasedTicketCount) {
        this.releasedTicketCount = releasedTicketCount; // Set the released ticket count
    }

    /**
     * Method for Vendors to release Tickets to the pool.
     * Blocks if the pool is full, notifies waiting customer threads once a ticket is released.
     * @param ticket the ticket to be added to the pool.
     */
    public void addTickets(Ticket ticket) {
        lock.lock(); // Acquire lock for thread safety

        try {
            while (ticketQueue.size() >= maxCapacity) { // If pool is full, wait
                logger.log(Level.INFO, Thread.currentThread().getName() + ": Waiting.. Ticket pool is full");
                notFull.await(); // Wait until pool is not full
            }

            releasedTicketCount++; // Increment released ticket count
            ticket.setTicketId(getReleasedTicketCount()); // Set Ticket ID based on released count
            ticketQueue.add(ticket); // Add the ticket to the queue
            logger.log(Level.INFO, Thread.currentThread().getName() + ": Added a ticket. Tickets available in the pool - " + ticketQueue.size());
            notEmpty.signal(); // Notify waiting customers that a ticket is available

        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, Thread.currentThread().getName() + ": Thread interrupted while adding a Ticket to the pool - " + e.getMessage());
            Thread.currentThread().interrupt(); // Interrupt the thread if interrupted during ticket addition

        } finally {
            lock.unlock(); // Release lock after operation
        }
    }

    /**
     * Method for Customers to purchase a ticket from the pool.
     * Blocks if the pool is empty and notifies waiting vendor threads once a ticket is removed from the queue.
     * @return the Ticket removed from head of the queue
     */
    public Ticket buyTickets() {
        lock.lock(); // Acquire lock for thread safety

        try {
            while (ticketQueue.isEmpty()) { // If pool is empty, wait
                logger.log(Level.INFO, Thread.currentThread().getName() + ": Waiting.. Ticket pool is empty");
                notEmpty.await(); // Wait until pool has tickets from a vendor
            }

            Ticket ticket = ticketQueue.poll(); // Remove and return the first ticket in the queue
            logger.log(Level.INFO, Thread.currentThread().getName() + ": Purchased a ticket from the pool.\n" + ticket + "\nTickets available in the pool - " + ticketQueue.size());
            notFull.signal(); // Notify waiting vendors that a space has been freed in the pool

            return ticket;

        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, Thread.currentThread().getName() + ": Thread interrupted while buying a Ticket from the pool");
            Thread.currentThread().interrupt(); // Interrupt thread if purchasing fails

            return null; // Return null in case of failure

        } finally {
            lock.unlock(); // Release lock after operation
        }
    }
}
