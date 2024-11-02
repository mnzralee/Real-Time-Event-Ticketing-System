public class TicketPool {
    private int availableTickets;
    private int maxTickets;
    private int totalReleasedTickets;

    // Constructor
    public TicketPool(Configuration config) {
        this.availableTickets = config.getTotalTickets();
        this.maxTickets = config.getMaxTicketCapacity();
    }

    // Getters and Setters
    public int getAvailableTickets() {
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



    /**
     *  Method for Vendors releasing Ticket
     */
    public void addTickets() {
        if (availableTickets < maxTickets) {
            synchronized (this) {
                this.availableTickets++;
            }
        }
        // INCLUDE ELSE BLOCK TO THROW A CUSTOM RUNTIME_EXCEPTION
    }

    /**
     *  Method for Customers purchasing Ticket
     */
    public void buyTickets() {
        if (availableTickets > 0) {
            synchronized (this) {
                this.availableTickets--;
            }
        }
        // INCLUDE ELSE BLOCK TO THROW A CUSTOM RUNTIME_EXCEPTION
    }

}
