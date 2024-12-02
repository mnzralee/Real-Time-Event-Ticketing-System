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

    // configuration file name
    private static final String CONFIG_FILE = "config.json";

//  Constructor
    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }


//  Getters and Setters

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

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
     * SAVE Configuration as a json file
     * Serializes the configuration object to a JSON file.
     * @param config Configuration object
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
     * LOAD json configuration file
     * Reads a JSON file and deserializes it into a configuration object.
     * @return config object
     */
    public static Configuration loadConfiguration() {

        Gson gson = new Gson();

        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            System.out.println("Config file not found.");
            return null; // Return null on error
        }
    }


}
