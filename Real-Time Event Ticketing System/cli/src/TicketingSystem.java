import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

public class TicketingSystem {

    public static void main(String[] args) {

        System.out.println("Welcome to the Ticketing System Simulation!");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a number to make a selection from the menu below");
            System.out.println("-------------------------------------------------------");
            System.out.println("1. Create new Configurations for the system ");
            System.out.println("2. Load existing Configurations for the system ");
            System.out.println("3. Quit system");
            System.out.println("-------------------------------------------------------");
            System.out.println("Enter your choice : ");

            int choice;
            Configuration config = null;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    config = getConfiguration(scanner);
                    ConfigManager.saveConfiguration(config);
                    System.out.println(config);
                    break;
                case 2:
                    config = ConfigManager.loadConfiguration();
                    if (config == null) {
                        System.out.println("Since No configuration found, Create new Configurations for the system");
                        config = getConfiguration(scanner);
                        ConfigManager.saveConfiguration(config);
                        System.out.println(config);
                        break;
                    } else {
                        System.out.println(config);
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using Ticketing System!");
                    System.out.println("Quitting the application...");
                    System.exit(0);
            }

//            startSystem(config);
            break;

        }






    }

    public static Configuration getConfiguration(Scanner scanner) {

        // Get user input and store in appropriate attributes
        int totalTickets = validateConfig(scanner, "Enter Total Tickets: ", value -> value > 0, "Total Tickets must be a positive integer.");
        int ticketReleaseRate = validateConfig(scanner, "Enter Ticket Release Rate: ", value -> value > 0, "Ticket Release Rate must be a positive integer.");
        int customerRetrievalRate = validateConfig(scanner, "Enter Customer Retrieval Rate: ", value -> value > 0, "Customer Retrieval Rate must be a positive integer.");
        int maxTicketCapacity = validateConfig(scanner, "Enter Maximum Ticket Capacity: ", value -> value > totalTickets, "Maximum Ticket Capacity must be greater than Total Tickets.");

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }


    /**
     * Validates user input for configuration values.
     *
     * @param scanner Scanner instance for input
     * @param prompt  Message to prompt the user
     * @param condition Validation condition for input
     * @param errorMessage Error message if the condition fails
     * @return A validated integer value
     */
    public static int validateConfig(Scanner scanner, String prompt, Predicate<Integer> condition, String errorMessage) {
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
