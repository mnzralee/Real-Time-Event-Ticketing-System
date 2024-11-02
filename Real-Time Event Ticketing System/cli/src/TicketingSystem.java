import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketingSystem {

    public static void main(String[] args) {

        System.out.println("Welcome to the Ticketing System");

        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("Enter the number of total tickets: ");
                int totalTickets = sc.nextInt();
                System.out.println("Enter the rate of Ticket release: ");
                int ticketReleaseRate = sc.nextInt();
                System.out.println("Enter the rate of Customer retrieval: ");
                int customerRetrievalRate = sc.nextInt();
                System.out.println("Enter the maximum number of ticket capacity: ");
                int maxTicketCapacity = sc.nextInt();
                while (maxTicketCapacity < totalTickets){
                    System.out.println("Enter the maximum number of ticket capacity: ");
                    maxTicketCapacity = sc.nextInt();
                }

                Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
            }

        configurationMenu();

    }







    }

    public static void start(Configuration configuration){
        // Dummy values for config
//        Configuration config = new Configuration(10, 1,1, 20);

        TicketPool ticketPool = new TicketPool(configuration);

        Vendor vendor = new Vendor(ticketPool, config.getTicketReleaseRate());
        Thread vendor1 = new Thread(vendor, "vendor1");
        Thread vendor2 = new Thread(vendor, "vendor2");
        Thread vendor3 = new Thread(vendor, "vendor3");
        vendor1.start();
        vendor2.start();
        vendor3.start();

        Customer customer = new Customer(ticketPool, config.getCustomerRetrievalRate());
        Thread customer1 = new Thread(customer, "customer1");
        Thread customer2 = new Thread(customer, "customer2");
        Thread customer3 = new Thread(customer, "customer3");
        customer1.start();
        customer2.start();
        customer3.start();
    }
}
