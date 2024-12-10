package lk.ac.iit.backend.repository;

import lk.ac.iit.backend.model.TicketingLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketingLogRepository extends JpaRepository<TicketingLog, Integer> {
    List<TicketingLog> findAllByOrderByIdDesc(); // Replace `Id` with the field you want to sort by descending order
}
