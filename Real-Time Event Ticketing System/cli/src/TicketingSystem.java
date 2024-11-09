import java.util.Scanner;
import java.util.function.Predicate;

public class TicketingSystem {

    public static void main(String[] args) {
//
//        System.out.println("Welcome to the Ticketing System");
//
//        while (true) {
//            Scanner sc = new Scanner(System.in);
//            try {
//                System.out.println("Enter the number of total tickets: ");
//                int totalTickets = sc.nextInt();
//                System.out.println("Enter the rate of Ticket release: ");
//                int ticketReleaseRate = sc.nextInt();
//                System.out.println("Enter the rate of Customer retrieval: ");
//                int customerRetrievalRate = sc.nextInt();
//                System.out.println("Enter the maximum number of ticket capacity: ");
//                int maxTicketCapacity = sc.nextInt();
//                while (maxTicketCapacity < totalTickets){
//                    System.out.println("Enter the maximum number of ticket capacity: ");
//                    maxTicketCapacity = sc.nextInt();
//                }
//
//                Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
//                break;
//
//            } catch (InputMismatchException e) {
//                System.out.println("Please enter a valid number.");
//            }

        Scanner scanner = new Scanner(System.in);

        int totalTickets = getValidatedInput(scanner, "Enter Total Tickets: ", value -> value > 0, "Total Tickets must be a positive integer.");
        int ticketReleaseRate = getValidatedInput(scanner, "Enter Ticket Release Rate: ", value -> value > 0, "Ticket Release Rate must be a positive integer.");
        int customerRetrievalRate = getValidatedInput(scanner, "Enter Customer Retrieval Rate: ", value -> value > 0, "Customer Retrieval Rate must be a positive integer.");
        int maxTicketCapacity = getValidatedInput(scanner, "Enter Maximum Ticket Capacity: ", value -> value > totalTickets, "Maximum Ticket Capacity must be greater than Total Tickets.");

        scanner.close();

        Configuration config = new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        startSystem(config);
    }

    // method for input validation
    private static int getValidatedInput(Scanner scanner, String prompt, Predicate<Integer> condition, String errorMessage) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (condition.test(value)) {
                    return value;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }


    public static void startSystem(Configuration config) {
        // Dummy values for config
//        Configuration config = new Configuration(10, 1,1, 20);

        TicketPool ticketPool = new TicketPool(config);

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
