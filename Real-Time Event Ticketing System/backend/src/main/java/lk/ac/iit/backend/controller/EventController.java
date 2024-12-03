package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.Event;
import lk.ac.iit.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Integer id) {
        return eventService.getEventById(id);
    }

    @GetMapping
    public List<Event> getAllEvent() {
        return eventService.getAllEvents();
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Integer id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
    }
}
