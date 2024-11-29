import java.util.ArrayList;
import java.util.List;

public class Customer implements Runnable{

    private final TicketPool ticketPool;

    private final int customerRetrievalRate;

    private final int quantity;

    private final List<Ticket> purchasedTickets;

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate * 1000;
        this.quantity = quantity;
        this.purchasedTickets = new ArrayList<>();
    }

    @Override
    public void run() {

        for (int i = 0; i < quantity; i++) {
            if(TicketingSystem.stopFlag.get()){
                break;
            }

            Ticket ticket = ticketPool.buyTickets();
            // add purchased ticket to list of tickets purchased by customer
            purchasedTickets.add(ticket);
            // print ticket details
//            System.out.println(Thread.currentThread().getName() + ": " + ticket);

            try {
                Thread.sleep(customerRetrievalRate);
            } catch (InterruptedException e) {
                System.out.println("Customer Thread Error: " + e.getMessage());
            }
        }


    }
}
