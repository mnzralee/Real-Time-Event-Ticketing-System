package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Event;
import lk.ac.iit.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * create new event
     *
     * @param event instance
     */
    public Event createEvent(Event event) {
        event.generateTickets(); // Generate tickets
        return eventRepository.save(event);
    }
    // Retrieve all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Retrieve an event by ID
    public Optional<Event> getEventById(Long eventId) {
        return eventRepository.findById(eventId);
    }

    // Update an existing event
    public Event updateEvent(Long eventId, Event updatedEvent) {
        return eventRepository.findById(eventId)
                .map(event -> {
                    event.setEventName(updatedEvent.getEventName());
                    event.setEventDate(updatedEvent.getEventDate());
                    event.setEventLocation(updatedEvent.getEventLocation());

                    return eventRepository.save(event);
                })
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }
}
