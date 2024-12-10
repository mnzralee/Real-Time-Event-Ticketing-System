package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Configuration;
import lk.ac.iit.backend.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    /**
     * Adds a new configuration. Updates the existing one if already present.
     * @param config The configuration to add or update.
     * @return The saved configuration.
     */
    public Configuration addConfig(Configuration config) {
        if (configurationRepository.existsById(1)){
            config = updateConfig(1, config);
        }
        config.setId(1);
        return configurationRepository.save(config);
    }

    /**
     * Retrieves the configuration with ID 1.
     * @return The configuration with ID 1.
     * @throws IllegalArgumentException If no configuration is found.
     */
    public Configuration getConfig() {
        return configurationRepository.findById(1)
                .orElseThrow(() -> new IllegalArgumentException("No configuration found"));
    }

    /**
     * Retrieves a configuration by its ID.
     * @param id The ID of the configuration to fetch.
     * @return The configuration with the given ID.
     * @throws IllegalArgumentException If the ID is invalid.
     */
    public Configuration getConfigById(Integer id) {
        return configurationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
    }

    /**
     * Retrieves all configurations.
     * @return A list of all configurations.
     */
    public List<Configuration> getAllConfigs() {
        return configurationRepository.findAll();
    }

    /**
     * Updates an existing configuration with new values.
     * @param id The ID of the configuration to update.
     * @param updateConfig The new values for the configuration.
     * @return The updated configuration.
     * @throws IllegalArgumentException If the ID is invalid.
     */
    public Configuration updateConfig(Integer id, Configuration updateConfig) {
        Configuration existingConfig = getConfigById(id);
        existingConfig.setTotalTickets(updateConfig.getTotalTickets());
        existingConfig.setTicketReleaseRate(updateConfig.getTicketReleaseRate());
        existingConfig.setCustomerRetrievalRate(updateConfig.getCustomerRetrievalRate());
        existingConfig.setMaxTicketCapacity(updateConfig.getMaxTicketCapacity());
        return configurationRepository.save(existingConfig);
    }

    /**
     * Deletes a configuration by its ID.
     * @param id The ID of the configuration to delete.
     * @throws IllegalArgumentException If the ID is invalid.
     */
    public void deleteConfig(Integer id) {
        if(!configurationRepository.existsById(id)){
            throw new IllegalArgumentException("Invalid ID: " + id);
        }
        configurationRepository.deleteById(id);
    }

}
