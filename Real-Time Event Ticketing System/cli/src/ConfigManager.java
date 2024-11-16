import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    // filename
    private static final String CONFIG_FILE = "config.json";

    /**
     * SAVE file
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
     * LOAD file
     * Reads a JSON file and deserializes it into a configuration object.
     */
    public static Configuration loadConfiguration() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            System.out.println("Config file not found.");
            return null; // Return null
        }
    }


}
