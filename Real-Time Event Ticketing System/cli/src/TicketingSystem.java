import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

public class TicketingSystem {

    public static final AtomicBoolean stopFlag = new AtomicBoolean(false);

    public static void main(String[] args) {

        System.out.println("Welcome to the Ticketing System Simulation!");

//        boolean runSystem = true;
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Enter a number to make a selection from the menu below");
            System.out.println("-------------------------------------------------------");
            System.out.println("1. Create new Configurations for the system ");
            System.out.println("2. Load existing Configurations for the system ");
            System.out.println("3. Quit system");
            System.out.println("-------------------------------------------------------");
            System.out.print("Enter your choice : ");

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
//                    ConfigManager.saveConfiguration(config);
                    Configuration.saveConfiguration(config);
                    System.out.println(config);
                    break;
                case 2:
//                    config = ConfigManager.loadConfiguration();
                    config = Configuration.loadConfiguration();
                    if (config == null) {
                        System.out.println("Since No configuration found, Create new Configurations for the system");
                        config = getConfiguration(scanner);
//                        ConfigManager.saveConfiguration(config);
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


            String userCmd;
            boolean runSimulation = true;
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
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }


    public static void startSystem(Configuration config, String userCmd) {

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



        Thread vendor1 = new Thread(new Vendor(ticketPool, config.getTotalTickets(), config.getTicketReleaseRate()), "vendor1");
        Thread vendor2 = new Thread(new Vendor(ticketPool, config.getTotalTickets(), config.getTicketReleaseRate()), "vendor2");
        Thread vendor3 = new Thread(new Vendor(ticketPool, config.getTotalTickets(), config.getTicketReleaseRate()), "vendor3");
        vendor1.start();
        vendor2.start();
        vendor3.start();

        Thread customer1 = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), 10), "customer1");
        Thread customer2 = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), 5), "customer2");
        Thread customer3 = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), 5), "customer3");
        customer1.start();
        customer2.start();
        customer3.start();


    }
}
