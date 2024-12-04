package lk.ac.iit.backend.util;

import lk.ac.iit.backend.model.Ticket;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {

    private ConcurrentLinkedQueue<Ticket> ticketQueue;

    private int maxCapacity; // Maximum tickets the pool can hold

    private final ReentrantLock lock = new ReentrantLock();;

    public TicketPool(int maxCapacity) {
        this.ticketQueue = new ConcurrentLinkedQueue<>();
        this.maxCapacity = maxCapacity;
    }



}
