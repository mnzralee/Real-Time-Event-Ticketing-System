public class Vendor implements Runnable{
    private TicketPool ticketPool;
    private int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate * 1000;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        while (!ticketPool.maxCapacityReached()) {
            ticketPool.addTickets();
            System.out.println(threadName + " released a ticket, Total tickets released: " + ticketPool.getTotalReleasedTickets());
            try {
                Thread.sleep(this.ticketReleaseRate);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println("Max capacity reached: " + ticketPool.maxCapacityReached());
    }
}
