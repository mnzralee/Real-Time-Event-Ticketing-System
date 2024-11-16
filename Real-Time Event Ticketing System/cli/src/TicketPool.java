public class TicketPool {
    private int availableTickets;
    private int maxTickets;


    private int totalReleasedTickets;

    // Constructor
    public TicketPool(Configuration config) {
        this.availableTickets = config.getTotalTickets();
        this.maxTickets = config.getMaxTicketCapacity();
        this.totalReleasedTickets = config.getTotalTickets();
    }

    // Getters and Setters
    public synchronized int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public int getMaxTickets() {
        return maxTickets;
    }

    public void setMaxTickets(int maxTickets) {
        this.maxTickets = maxTickets;
    }

    public synchronized int getTotalReleasedTickets() {
        return totalReleasedTickets;
    }

    public void setTotalReleasedTickets(int totalReleasedTickets) {
        this.totalReleasedTickets = totalReleasedTickets;
    }



    /**
     * Method for Vendors releasing Ticket
     */
    public synchronized void addTickets() {
        if (totalReleasedTickets < maxTickets) {
            availableTickets++;
            totalReleasedTickets++;
            System.out.println("Vendor released a ticket. Available tickets: " + availableTickets);
            notifyAll(); // Notify waiting customers that tickets are available
        } else {
            System.out.println("Max ticket capacity reached. No more tickets can be added.");
        }
    }
//    public void addTickets() {
//        synchronized (this) {
//            this.availableTickets++;
//            this.totalReleasedTickets++;
//        }
//    }

    /**
     *
     * Method for Customers purchasing Ticket
     */
    public synchronized void buyTickets() {
        while (availableTickets == 0) {
            if (maxCapacityReached()) {
                System.out.println(Thread.currentThread().getName() + " - Max capacity reached. No more tickets available.");
                return; // Exit if no more tickets will be available
            }

            try {
                System.out.println(Thread.currentThread().getName() + " is waiting for a ticket.");
                wait(); // Wait until a ticket is released
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
                return; // Exit on interruption
            }
        }

        availableTickets--; // Purchase the ticket
        System.out.println(Thread.currentThread().getName() + " purchased a ticket. Tickets left: " + availableTickets);
    }
//    public void buyTickets() {
//        synchronized (this) {
//            if (this.availableTickets > 0) {
//                this.availableTickets--;
//            } else {
//                System.out.println("Ticket Pool is empty, waiting for vendor to release tickets.");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    /// @return true if all tickets are released by vendor
    public synchronized boolean maxCapacityReached() {
        return totalReleasedTickets >= maxTickets;
    }

    /// @return true if no tickets are available
    public synchronized boolean noTicketsAvailable() {
        return availableTickets == 0;
    }

}
