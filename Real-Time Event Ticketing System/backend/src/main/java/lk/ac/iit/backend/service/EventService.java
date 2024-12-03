package lk.ac.iit.backend.service;

import lk.ac.iit.backend.model.Event;
import lk.ac.iit.backend.repository.EventRepository;
import lk.ac.iit.backend.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event getEventById(Integer id) {
        return eventRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Invalid Event ID: " + id));
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event updateEvent(Integer id, Event updatedEvent) {
        Event existingEvent = getEventById(id);
        existingEvent.setEventName(updatedEvent.getEventName());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setDate(updatedEvent.getDate());
        return eventRepository.save(existingEvent);
    }

    public void deleteEvent(Integer id) {
        if(!eventRepository.existsById(Long.valueOf(id))) {
            throw new IllegalArgumentException("Invalid Event ID: " + id);
        }
        eventRepository.deleteById(Long.valueOf(id));
    }
}
