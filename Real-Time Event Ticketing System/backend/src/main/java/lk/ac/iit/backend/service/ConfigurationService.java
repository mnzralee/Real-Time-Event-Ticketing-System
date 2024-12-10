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

    public Configuration addConfig(Configuration config) {
        if (configurationRepository.existsById(1)){
            config = updateConfig(1, config);
        }
        return configurationRepository.save(config);
    }

    public Configuration getConfig() {
        return configurationRepository.findById(1)
                .orElseThrow(() -> new IllegalArgumentException("No configuration found"));
    }

    public Configuration getConfigById(Integer id) {
        return configurationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));
    }

    public List<Configuration> getAllConfigs() {
        return configurationRepository.findAll();
    }

    public Configuration updateConfig(Integer id, Configuration updateConfig) {
        Configuration existingConfig = getConfigById(id);
        existingConfig.setTotalTickets(updateConfig.getTotalTickets());
        existingConfig.setTicketReleaseRate(updateConfig.getTicketReleaseRate());
        existingConfig.setCustomerRetrievalRate(updateConfig.getCustomerRetrievalRate());
        existingConfig.setMaxTicketCapacity(updateConfig.getMaxTicketCapacity());
        return configurationRepository.save(existingConfig);
    }

    public void deleteConfig(Integer id) {
        if(!configurationRepository.existsById(id)){
            throw new IllegalArgumentException("Invalid ID: " + id);
        }
        configurationRepository.deleteById(id);
    }

}
