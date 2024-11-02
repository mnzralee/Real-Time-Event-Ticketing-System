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
     *  Method for Vendors releasing Ticket
     */
    public void addTickets() {
        synchronized (this) {
            this.availableTickets++;
            this.totalReleasedTickets++;
        }
    }

    /**
     *  Method for Customers purchasing Ticket
     */
    public void buyTickets() {
        if (this.availableTickets > 0) {
            synchronized (this) {
                this.availableTickets--;
            }
        } else {
            System.out.println("Ticket Pool is empty, waiting for vendor to release tickets.");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /// @return true if all tickets are released by vendor
    public synchronized boolean maxCapacityReached() {
        return totalReleasedTickets == maxTickets;
    }

    /// @return true if no tickets are available
    public synchronized boolean noTicketsAvailable() {
        return availableTickets == 0;
    }

}
