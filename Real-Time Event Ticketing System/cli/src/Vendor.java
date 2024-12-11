import java.math.BigDecimal;

public class Vendor implements Runnable {

    private final TicketPool ticketPool; // TicketPool instance to add tickets to
    private final int totalTickets; // Total tickets the vendor holds
    private final int ticketReleaseRate; // The rate at which tickets are added to the pool (milliseconds)


    // CONSTRUCTOR
    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate * 1000; // Convert seconds to milliseconds
    }

    @Override
    public void run() {
        // Loop through all tickets to add them to the pool
        for (int i = 1; i <= totalTickets; i++) {

            // Check if the simulation is stopped, and break the loop if so
            if (TicketingSystem.stopFlag.get()) {
                break;
            }

            // Create a new ticket object with ticket details
            Ticket ticket = new Ticket("Opera Eve", "16-12-2024", "Colombo", new BigDecimal(1000));
            ticketPool.addTickets(ticket); // Add the created ticket to the pool

            // Set the rate at which the vendor adds tickets (simulating delay)
            try {
                Thread.sleep(ticketReleaseRate); // Sleep for the configured release rate
            } catch (InterruptedException e) {
                System.out.println("Vendor Thread Error: " + e.getMessage()); // Handle thread interruption
            }
        }

        // Log when the vendor stops releasing tickets
        System.out.println(Thread.currentThread().getName() + ": Stopped releasing tickets");
    }
}
