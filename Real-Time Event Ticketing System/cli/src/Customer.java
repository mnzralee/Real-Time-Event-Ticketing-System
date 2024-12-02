import java.util.ArrayList;
import java.util.List;

public class Customer implements Runnable{

    private final TicketPool ticketPool; // TicketPool instance to buy tickets from
    private final int customerRetrievalRate; // Rate at which the customer buys a new ticket fom the pool
    private final int quantity; // the quantity of tickets the customer will buy

    // List of tickets purchased by the customer
    private final List<Ticket> purchasedTickets;


    // CONSTRUCTOR

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate * 1000;
        this.quantity = quantity;
        this.purchasedTickets = new ArrayList<>();
    }


    @Override
    public void run() {

        for (int i = 0; i < quantity; i++) {

            // STOPS simulation if stopFlag true
            if(TicketingSystem.stopFlag.get()){
                break;
            }

            // purchase ticket
            Ticket ticket = ticketPool.buyTickets();

            // add purchased ticket to list of tickets purchased by customer
            purchasedTickets.add(ticket);

            try {
                Thread.sleep(customerRetrievalRate);
            } catch (InterruptedException e) {
                System.out.println("Customer Thread Error: " + e.getMessage());
            }
        }

    }
}
