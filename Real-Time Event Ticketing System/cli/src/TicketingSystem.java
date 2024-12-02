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

            System.out.println("Enter a number to make a selection from the menu below");
            System.out.println("-------------------------------------------------------");
            System.out.println("1. Create new Configurations for the system ");
            System.out.println("2. Load existing Configurations for the system ");
            System.out.println("3. Quit system");
            System.out.println("-------------------------------------------------------");
            System.out.print("Enter your choice : ");

            int choice;

            // local configuration variable
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
                    Configuration.saveConfiguration(config);
                    System.out.println(config);
                    break;
                case 2:
                    config = Configuration.loadConfiguration();
                    if (config == null) {
                        System.out.println("Since No configuration found, Create new Configurations for the system");
                        config = getConfiguration(scanner);
                        Configuration.saveConfiguration(config);
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
                    break;
                default:
                    System.out.println("Please enter a valid choice.");
                    continue;
            }

            // user command variable
            String userCmd;

            // control simulation loop
            boolean runSimulation = true;

            // Simulation Loop
            while (runSimulation) {
                System.out.println("\nPlease enter a command : [ 'start', 'stop', 'menu', 'quit' ] ");
                try {
                    userCmd = scanner.next();
                } catch (Exception e) {
                    System.out.println("error try again..");
                    continue;
                }

                switch (userCmd){
                    case "start":
                        stopFlag.set(false);
                        startSystem(config, userCmd);
                        System.out.println("Simulation running.");
                        break;
                    case "stop":
                        stopFlag.set(true);
                        System.out.println("Simulation stopped.");
                        break;
                    case "menu":
                        // starts program from menu
                        runSimulation = false;
                        break;

                    case "quit":
                        System.out.println("Exiting the system...");
                        runSimulation = false;
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid command. Try 'start', 'stop', 'menu' or 'quit'. ");
                }
            }
        }
    }

    /**
     * Method to get validated configuration values and store them appropriately
     * @param scanner Scanner instance
     * @return a Configuration instance
     */
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
                value = scanner.nextInt();
                if (condition.test(value)) {
                    return value;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // dispose the input
            }
        }
    }


    /**
     * method to start the simulation of customer and vendor threads
     * @param config Configuration Object with the config values
     * @param userCmd user input command to start/stop the simulation
     */
    public static void startSystem(Configuration config, String userCmd) {

        // ticket pool instance
        TicketPool ticketPool = new TicketPool(config);

        // Thread which listens for the "stop" command to stop simulation
        new Thread(() -> {
            System.out.println("Type 'stop' to end the simulation...");
            while (true) {
                if ("stop".equalsIgnoreCase(userCmd)) {
                    System.out.println("Stopping all threads...");
                    stopFlag.set(true); // Set the flag to stop all threads
                    break;
                }
            }
        }).start();

        // Loop to create a said number of customer and vendor threads
        for (int i=1; i<=5; i++){
            Thread vendorThread = new Thread(new Vendor(ticketPool, config.getTotalTickets(), config.getTicketReleaseRate()), "vendor"+i);
            vendorThread.start();

            Thread customerThread = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), 5), "customer"+i);
            customerThread.start();
        }
    }
}
