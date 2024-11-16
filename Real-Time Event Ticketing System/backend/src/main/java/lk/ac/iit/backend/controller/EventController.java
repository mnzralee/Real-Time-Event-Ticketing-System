package lk.ac.iit.backend.controller;

import lk.ac.iit.backend.model.Event;
import lk.ac.iit.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        eventService.createEvent(event);
        return event;
    }

}
