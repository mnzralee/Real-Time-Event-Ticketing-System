import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Configuration file name
    private static final String CONFIG_FILE = "config.json";

    // Constructor

    /**
     * Constructs a Configuration with specified values.
     *
     * @param totalTickets the total number of tickets
     * @param ticketReleaseRate the rate at which tickets are released
     * @param customerRetrievalRate the rate at which customers retrieve tickets
     * @param maxTicketCapacity the maximum capacity for tickets
     */
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Getters and Setters

    /**
     * Gets the total number of tickets.
     *
     * @return the total number of tickets
     */
    public int getTotalTickets() {
        return totalTickets;
    }

    /**
     * Sets the total number of tickets.
     *
     * @param totalTickets the total number of tickets to set
     */
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    /**
     * Gets the ticket release rate.
     *
     * @return the ticket release rate
     */
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    /**
     * Sets the ticket release rate.
     *
     * @param ticketReleaseRate the ticket release rate to set
     */
    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    /**
     * Gets the customer retrieval rate.
     *
     * @return the customer retrieval rate
     */
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    /**
     * Sets the customer retrieval rate.
     *
     * @param customerRetrievalRate the customer retrieval rate to set
     */
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    /**
     * Gets the maximum ticket capacity.
     *
     * @return the maximum ticket capacity
     */
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    /**
     * Sets the maximum ticket capacity.
     *
     * @param maxTicketCapacity the maximum ticket capacity to set
     */
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    /**
     * Returns a string representation of the Configuration object.
     *
     * @return string representation of the Configuration
     */
    @Override
    public String toString() {
        return "Configuration{" +
                "totalTickets= " + totalTickets +
                ", ticketReleaseRate= " + ticketReleaseRate +
                ", customerRetrievalRate= " + customerRetrievalRate +
                ", maxTicketCapacity= " + maxTicketCapacity +
                '}';
    }

    /**
     * Saves the Configuration to a JSON file.
     *
     * @param config the Configuration object to save
     */
    public static void saveConfiguration(Configuration config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            gson.toJson(config, writer);
            System.out.println("Configuration saved successfully.");
        } catch (IOException e) {
            System.out.println("Configuration save failed.");
        }
    }

    /**
     * Loads the Configuration from a JSON file.
     *
     * @return the Configuration object, or null if loading fails
     */
    public static Configuration loadConfiguration() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            System.out.println("Config file not found.");
            return null; // Return null if loading fails
        }
    }
}
