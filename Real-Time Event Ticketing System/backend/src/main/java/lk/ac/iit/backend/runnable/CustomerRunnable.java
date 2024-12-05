package lk.ac.iit.backend.runnable;

import lk.ac.iit.backend.model.Customer;
import lk.ac.iit.backend.service.TicketPoolService;

public class CustomerRunnable implements Runnable {

    private final TicketPoolService ticketPoolService;
    private final Customer customer;
    private final int customerRetrievalRate;
    private final int quantity;

    public CustomerRunnable(TicketPoolService ticketPoolService, Customer customer, int customerRetrievalRate, int quantity) {
        this.ticketPoolService = ticketPoolService;
        this.customer = customer;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            ticketPoolService.buyTicket(customer);
            try {
                Thread.sleep(customerRetrievalRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
