package lk.ac.iit.backend.repository;

import lk.ac.iit.backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
