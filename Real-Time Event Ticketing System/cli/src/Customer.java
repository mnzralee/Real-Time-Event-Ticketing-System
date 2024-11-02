public class Customer implements Runnable{
    private TicketPool ticketPool;
    private int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate * 1000;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        while (!ticketPool.noTicketsAvailable()) {
            ticketPool.buyTickets();
            System.out.println(threadName + " purchased a ticket, Total available tickets: " + ticketPool.getAvailableTickets());
            try {
                Thread.sleep(this.customerRetrievalRate);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println("cannot purchase anymore, all tickets sold: " + ticketPool.noTicketsAvailable());
    }
}
