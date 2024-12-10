package lk.ac.iit.backend.runnable;

import lk.ac.iit.backend.model.Ticket;
import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.service.TicketPoolService;

import static lk.ac.iit.backend.service.SimulationRunnerService.stopFlag;

public class VendorRunnable implements Runnable {

    private final TicketPoolService ticketPoolService;
    private final Vendor vendor;
    private final int ticketReleaseRate;
    private final int totalTickets;

    public VendorRunnable(TicketPoolService ticketPoolService, Vendor vendor, int ticketReleaseRate, int totalTickets) {
        this.ticketPoolService = ticketPoolService;
        this.vendor = vendor;
        this.ticketReleaseRate = ticketReleaseRate * 1000;
        this.totalTickets = totalTickets;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            if(stopFlag.get() || Thread.currentThread().isInterrupted()){
                break;
            }
            ticketPoolService.addTicket(vendor, new Ticket("DevFest", "An Event Organized by Google"));
            try {
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
