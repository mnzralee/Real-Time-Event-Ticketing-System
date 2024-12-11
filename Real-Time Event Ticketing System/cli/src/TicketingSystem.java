import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class TicketingSystem {

    // An atomic boolean which can be accessed by all threads, to start/stop threads
    public static final AtomicBoolean stopFlag = new AtomicBoolean(false);

    public static void main(String[] args) {

        System.out.println("Welcome to the Ticketing System Simulation!");

        Scanner scanner = new Scanner(System.in);

        // Main Loop
        while (true) {

            // Display the menu
            System.out.println("Enter a number to make a selection from the menu below");
            System.out.println("-------------------------------------------------------");
            System.out.println("1. Create new Configurations for the system ");
            System.out.println("2. Load existing Configurations for the system ");
            System.out.println("3. Quit system");
            System.out.println("-------------------------------------------------------");
            System.out.print("Enter your choice : ");

            int choice;

            // Local configuration variable
            Configuration config = null;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    config = getConfiguration(scanner); // Get user configuration input
                    Configuration.saveConfiguration(config); // Save the configuration
                    System.out.println(config); // Display configuration
                    break;
                case 2:
                    config = Configuration.loadConfiguration(); // Load existing configuration
                    if (config == null) {
                        System.out.println("No configuration found, creating new configuration...");
                        config = getConfiguration(scanner);
                        Configuration.saveConfiguration(config);
                        System.out.println(config);
                    } else {
                        System.out.println(config);
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using Ticketing System!");
                    System.out.println("Quitting the application...");
                    System.exit(0); // Exit the program
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
                    continue;
            }

            // Command input for running the simulation
            String userCmd;
            boolean runSimulation = true;

            // Simulation Loop
            while (runSimulation) {
                System.out.println("\nPlease enter a command : [ 'start', 'stop', 'menu', 'quit' ] ");
                try {
                    userCmd = scanner.next().toLowerCase();
                } catch (Exception e) {
                    System.out.println("Error, try again.");
                    continue;
                }

                switch (userCmd){
                    case "start":
                        stopFlag.set(false);
                        startSystem(config, userCmd); // Start the simulation
                        System.out.println("Simulation running.");
                        break;
                    case "stop":
                        stopFlag.set(true); // Stop the simulation
                        System.out.println("Simulation stopped.");
                        break;
                    case "menu":
                        runSimulation = false; // Exit to the main menu
                        break;
                    case "quit":
                        System.out.println("Exiting the system...");
                        runSimulation = false;
                        System.exit(0); // Exit the program
                        break;
                    default:
                        System.out.println("Invalid command. Try 'start', 'stop', 'menu', or 'quit'. ");
                }
            }
        }
    }

    /**
     * Gets validated configuration values and stores them in a Configuration instance.
     * @param scanner Scanner instance for user input
     * @return a Configuration instance with user inputs
     */
    public static Configuration getConfiguration(Scanner scanner) {

        // Validate and store configuration values
        int totalTickets = validateConfig(scanner, "Enter Total Tickets: ", value -> value > 0, "Total Tickets must be a positive integer.");
        int ticketReleaseRate = validateConfig(scanner, "Enter Ticket Release Rate: ", value -> value > 0, "Ticket Release Rate must be a positive integer.");
        int customerRetrievalRate = validateConfig(scanner, "Enter Customer Retrieval Rate: ", value -> value > 0, "Customer Retrieval Rate must be a positive integer.");
        int maxTicketCapacity = validateConfig(scanner, "Enter Maximum Ticket Capacity: ", value -> value > 0, "Maximum Ticket Capacity must be a positive integer.");

        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity); // Return new Configuration
    }

    /**
     * Validates user input for configuration values.
     *
     * @param scanner Scanner instance for user input
     * @param prompt  Message to prompt the user
     * @param condition Validation condition for input
     * @param errorMessage Error message if the condition fails
     * @return A validated integer value
     */
    public static int validateConfig(Scanner scanner, String prompt, Predicate<Integer> condition, String errorMessage) {
        int value;
        while (true) {
            System.out.print(prompt); // Prompt user for input
            try {
                value = scanner.nextInt(); // Get input value
                if (condition.test(value)) { // Validate input
                    return value;
                } else {
                    System.out.println(errorMessage); // Display error message if validation fails
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // Dispose of invalid input
            }
        }
    }

    /**
     * Starts the simulation by creating and running customer and vendor threads.
     * @param config Configuration object with system settings
     * @param userCmd User command to start the system
     */
    public static void startSystem(Configuration config, String userCmd) {

        // Initialize the ticket pool with the configuration
        TicketPool ticketPool = new TicketPool(config);

        // Thread to listen for the "stop" command and stop the simulation
        new Thread(() -> {
            System.out.println("Type 'stop' to end the simulation...");
            while (true) {
                if ("stop".equalsIgnoreCase(userCmd)) {
                    System.out.println("Stopping all threads...");
                    stopFlag.set(true); // Stop all threads by setting stop flag
                    break;
                }
            }
        }).start();

        // Start VIP customer thread with higher priority
        Thread vipCustomerThread = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), 5), "Customer_vip");
        vipCustomerThread.start();

        // Start vendor and regular customer threads
        for (int i = 1; i <= 5; i++) {
            Thread vendorThread = new Thread(new Vendor(ticketPool, config.getTotalTickets(), config.getTicketReleaseRate()), "Vendor" + i);
            vendorThread.start();

            Thread customerThread = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), 5), "Customer" + i);
            customerThread.start();
        }
    }
}
