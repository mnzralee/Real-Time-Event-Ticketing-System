package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.entity.Event;
import lk.ac.iit.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/ticket")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Post Request to add new event
     * @param event instance
     * @return event detail
     */
    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        eventRepository.save(event);
        return event;
    }

    /**
     * Get request to get all events
     * @return all events
     */
    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

//    /**
//     * Put request to update an Event
//     * @param id to identify which event to update
//     * @param event instance
//     * @return updated event
//     */
//    @PutMapping("{/id}")
//    public Event updateEvent(@PathVariable long id, @RequestBody Event event) {
//        event.setEventId(id);
//        return eventRepository.save(event);
//    }
//
//    /**
//     * Deleting an event
//     * @param id to identify the event
//     */
//    @DeleteMapping("{/id}")
//    public void deleteEvent(@PathVariable long id) {
//        eventRepository.deleteById(id);
//    }





}
