package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Configuration;
import lk.ac.iit.backend.model.Customer;
import lk.ac.iit.backend.model.Vendor;
import lk.ac.iit.backend.runnable.CustomerRunnable;
import lk.ac.iit.backend.runnable.VendorRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class SimulationRunnerService {

    private final TicketPoolService ticketPoolService;

    private final CustomerService customerService;

    private final VendorService vendorService;

    private final ConfigurationService configurationService;

    @Autowired
    public SimulationRunnerService(TicketPoolService ticketPoolService, CustomerService customerService, VendorService vendorService, ConfigurationService configurationService) {
        this.ticketPoolService = ticketPoolService;
        this.customerService = customerService;
        this.vendorService = vendorService;
        this.configurationService = configurationService;
    }

    private ExecutorService executorService;

    // An atomic boolean which can be accessed by all threads, to start/stop threads
    public static final AtomicBoolean stopFlag = new AtomicBoolean(false);

    /**
     * Starts the simulation by creating and submitting VendorRunnable and CustomerRunnable tasks.
     * Configures the simulation using the configuration service.
     */
    public void startSimulation() {
        stopFlag.set(false);
        Configuration config = configurationService.getConfig();

        executorService =  Executors.newCachedThreadPool();

        //INCLUDE CODE FOR VIP_CUSTOMERS

        List<Vendor> vendors = vendorService.getAllVendors();
        for (Vendor vendor : vendors) {
            VendorRunnable vRun = new VendorRunnable(ticketPoolService, vendor, config.getTicketReleaseRate(), config.getTotalTickets());
            executorService.submit(vRun);
        }

        List<Customer> customers = customerService.getAllCustomers();
        for (Customer customer : customers) {
            CustomerRunnable cRun = new CustomerRunnable(ticketPoolService, customer, config.getCustomerRetrievalRate(), 4);
            executorService.submit(cRun);
        }

    }

    /**
     * Stops the simulation by setting the stop flag and shutting down the executor service.
     */
    public void stopSimulation() {
        stopFlag.set(true);
        executorService.shutdown();
    }



}
