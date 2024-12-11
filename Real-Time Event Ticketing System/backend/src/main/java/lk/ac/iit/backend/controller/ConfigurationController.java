package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.Configuration;
import lk.ac.iit.backend.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/config")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @Autowired
    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Adds a new configuration to the system.
     *
     * @param config The configuration object to be added.
     * @return The saved configuration object.
     */
    @PostMapping
    public Configuration addConfig(@RequestBody Configuration config) {
        return configurationService.addConfig(config);
    }

    /**
     * Retrieves a specific configuration by its ID.
     *
     * @param id The ID of the configuration to retrieve.
     * @return The configuration object with the specified ID.
     */
    @GetMapping("/{id}")
    public Configuration getConfigById(@PathVariable Integer id) {
        return configurationService.getConfigById(id);
    }

    /**
     * Retrieves all configurations from the system.
     *
     * @return A list of all configuration objects.
     */
    @GetMapping
    public List<Configuration> getAllConfigs() {
        return configurationService.getAllConfigs();
    }

    /**
     * Updates an existing configuration by its ID.
     *
     * @param id     The ID of the configuration to update.
     * @param config The updated configuration object.
     * @return The updated configuration object.
     */
    @PutMapping("/{id}")
    public Configuration updateConfig(@PathVariable Integer id, @RequestBody Configuration config) {
        return configurationService.updateConfig(id, config);
    }

    /**
     * Deletes a specific configuration by its ID.
     *
     * @param id The ID of the configuration to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteConfig(@PathVariable Integer id) {
        configurationService.deleteConfig(id);
    }
}
