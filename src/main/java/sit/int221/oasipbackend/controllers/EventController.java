package sit.int221.oasipbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasipbackend.dtos.EventDetailDTO;
import sit.int221.oasipbackend.dtos.SimpleEventDTO;
import sit.int221.oasipbackend.entities.Event;
import sit.int221.oasipbackend.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping("")
    public List<SimpleEventDTO> getAllEvent() {
        return service.getAllEvent();
    }

    @GetMapping("/{id}")
    public EventDetailDTO getEventById(@PathVariable Integer id) {
        return service.getEventById(id);
    }

    @PostMapping("")
    public void Event (@RequestBody EventDetailDTO newEvent) {
        service.save(newEvent);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Event updateEvent, @PathVariable Integer id) {
        service.update(updateEvent, id);
    }
}
