package lk.ac.iit.backend.repository;

import lk.ac.iit.backend.model.TicketingLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketingLogRepository extends JpaRepository<TicketingLog, Integer> {
}
