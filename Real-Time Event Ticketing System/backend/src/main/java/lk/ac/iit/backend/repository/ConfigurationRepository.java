package lk.ac.iit.backend.repository;

import lk.ac.iit.backend.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {
}
