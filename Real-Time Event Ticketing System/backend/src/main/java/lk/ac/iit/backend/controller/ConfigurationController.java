package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.Configuration;
import lk.ac.iit.backend.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/config")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @Autowired
    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping
    public Configuration addConfig(Configuration config) {
        return configurationService.addConfig(config);
    }

    @GetMapping("/{id}")
    public Configuration getConfigById(@PathVariable Integer id) {
        return configurationService.getConfigById(id);
    }

    @GetMapping
    public List<Configuration> getAllConfigs() {
        return configurationService.getAllConfigs();
    }

    @PutMapping("/{id}")
    public Configuration updateConfig(@PathVariable Integer id, Configuration config) {
        return configurationService.updateConfig(id, config);
    }

    @DeleteMapping("/{id}")
    public void deleteConfig(@PathVariable Integer id) {
        configurationService.deleteConfig(id);
    }
}
