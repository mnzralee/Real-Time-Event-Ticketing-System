import java.util.ArrayList;
import java.util.List;

public class Customer implements Runnable {

    private final TicketPool ticketPool; // TicketPool instance to buy tickets from
    private final int customerRetrievalRate; // Rate at which the customer buys a new ticket from the pool (milliseconds)
    private final int quantity; // The number of tickets the customer will buy

    // List of tickets purchased by the customer
    private final List<Ticket> purchasedTickets;


    // CONSTRUCTOR
    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate * 1000; // Convert seconds to milliseconds
        this.quantity = quantity;
        this.purchasedTickets = new ArrayList<>();
    }

    @Override
    public void run() {

        // Prioritize customer threads containing '_vip' in their name (VIP customers)
        if (Thread.currentThread().getName().contains("_vip")) {
            Thread.currentThread().setPriority(10); // Set highest priority for VIP customers
        } else {
            Thread.currentThread().setPriority(5); // Set normal priority for regular customers
        }

        for (int i = 0; i < quantity; i++) {

            // Stop simulation if stopFlag is true
            if (TicketingSystem.stopFlag.get()) {
                break;
            }

            // Purchase ticket from the ticket pool
            Ticket ticket = ticketPool.buyTickets();

            // Add purchased ticket to the list of tickets purchased by the customer
            purchasedTickets.add(ticket);

            try {
                // Set the rate at which the customer buys tickets (simulating delay)
                Thread.sleep(customerRetrievalRate); // Sleep for the configured retrieval rate
            } catch (InterruptedException e) {
                System.out.println("Customer Thread Error: " + e.getMessage()); // Handle thread interruption
            }
        }

    }
}
