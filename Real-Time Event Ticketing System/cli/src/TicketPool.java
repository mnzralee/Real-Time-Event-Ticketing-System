import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketPool {

    private final int maxCapacity; // maximum capacity of tickets in the pool
    private final Queue<Ticket> ticketQueue; // Queue to manage tickets in FIFO order
    private int releasedTicketCount = 0; // counter for total released tickets

    // Logger used to maintain an organized logging in cli
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());

    // ReentrantLock used to ensure thread safe access
    private final ReentrantLock lock = new ReentrantLock();

    // Conditions to notify threads
    private final Condition notEmpty = lock.newCondition(); // notify when pool is not empty
    private final Condition notFull = lock.newCondition(); // notify when pool is not full


    // Constructor
    public TicketPool(Configuration config) {
        this.maxCapacity = config.getMaxTicketCapacity();
        this.ticketQueue = new LinkedList<>();
    }


    // Getters and Setters

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Queue<Ticket> getTicketQueue() {
        return ticketQueue;
    }

    public int getReleasedTicketCount() {
        lock.lock();
        try {
            return releasedTicketCount; // return count of released tickets
        } finally {
            lock.unlock(); // Release the lock
        }
    }

    public void setReleasedTicketCount(int releasedTicketCount) {
        this.releasedTicketCount = releasedTicketCount;
    }


    /**
     * Method for Vendors to release Tickets to the pool.
     * Block if the pool is full, notifies waiting customer threads once a ticket is released.
     * @param ticket the ticket to be added to the pool.
     */
    public  void addTickets(Ticket ticket) {
        lock.lock();
        try {
            while (ticketQueue.size() >= maxCapacity) {
                logger.log(Level.INFO, Thread.currentThread().getName() + ": Waiting.. Ticket pool is full");
                notFull.await(); // wait until not full signal
            }
            releasedTicketCount++;
            ticket.setTicketId(getReleasedTicketCount()); // set Ticket ID accordingly based on release
            ticketQueue.add(ticket); // add Ticket to Ticket Queue
            logger.log(Level.INFO, Thread.currentThread().getName() + ": Added a ticket. Tickets available in the pool - " + ticketQueue.size());
            notEmpty.signal(); // notify a waiting customer
        } catch (InterruptedException e){
            logger.log(Level.SEVERE, Thread.currentThread().getName() + ": Thread interrupted while adding a Ticket to the pool - " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock(); // Release Lock
        }
    }


    /**
     * Method for Customers to purchase a ticket from the pool.
     * Blocks if the pool is empty and notifies waiting vendor threads once a ticket is removed from the queue.
     * @return the Ticket removed from head of the queue
     */
    public Ticket buyTickets() {
        lock.lock();
        try{
            while (ticketQueue.isEmpty()) {
                logger.log(Level.INFO, Thread.currentThread().getName() + ": Waiting.. Ticket pool is empty");
                notEmpty.await();
            }
            Ticket ticket = ticketQueue.poll(); // Retrieve and remove the head of the queue
            logger.log(Level.INFO, Thread.currentThread().getName() + ": Purchased a ticket from the pool.\n" + ticket);
            notFull.signal(); // notify a waiting vendor

            return ticket;
        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, Thread.currentThread().getName() + ": Thread interrupted while buying a Ticket from the pool");
            Thread.currentThread().interrupt();

            return null;
        } finally {
            lock.unlock(); // release lock
        }
    }


}
