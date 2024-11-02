public class Vendor implements Runnable{
    private TicketPool ticketPool;
    private int maxTickets;
    private int totalTickets;

    public Vendor(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.maxTickets = config.getMaxTicketCapacity();
        this.totalTickets = config.getTotalTickets();
    }

    @Override
    public void run() {
        ticketPool.addTickets();

    }
}
