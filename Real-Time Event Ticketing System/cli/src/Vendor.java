import java.math.BigDecimal;

public class Vendor implements Runnable{
    private final TicketPool ticketPool;
    private final int totalTickets;
    private final int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate * 1000;
    }

    @Override
    public void run() {

        for (int i = 1; i <= totalTickets; i++) {

            if(TicketingSystem.stopFlag.get()){
                break;
            }

            Ticket ticket = new Ticket("Opera Eve", "16-12-2024", "Colombo", new BigDecimal(1000));
            ticketPool.addTickets(ticket);

            // setting the rate at which vendor adds ticket
            try {
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                System.out.println("Vendor Thread Error: " + e.getMessage());
            }
        }

        System.out.println(Thread.currentThread().getName() + ": Stopped releasing tickets");

    }
}
