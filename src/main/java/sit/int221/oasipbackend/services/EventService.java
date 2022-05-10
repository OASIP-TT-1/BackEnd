package sit.int221.oasipbackend.services;

import org.hibernate.type.LocalDateTimeType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipbackend.dtos.EventCreateDTO;
import sit.int221.oasipbackend.dtos.EventDetailDTO;
import sit.int221.oasipbackend.dtos.EventRescheduleDTO;
import sit.int221.oasipbackend.dtos.SimpleEventDTO;
import sit.int221.oasipbackend.entities.Event;
import sit.int221.oasipbackend.repositories.EventRepository;
import sit.int221.oasipbackend.utils.ListMapper;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    public List<SimpleEventDTO> getAllEvent() {
        Sort sort = Sort.by("eventStartTime");
        List<Event> eventList = repository.findAll(sort.descending());
        return listMapper.mapList(eventList, SimpleEventDTO.class, modelMapper);
    }

//    public List<Event> getAllEvent() {
//        Sort sort = Sort.by("eventStartTime");
//        List<Event> eventList = repository.findAll(sort.descending());
//        return eventList;
//    }

    public EventDetailDTO getEventById(Integer id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        id + " Dose not exits!!!"));

        return modelMapper.map(event, EventDetailDTO.class);
//        return repository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        id + " Dose not exits!!!"));
    }

    public Event save(EventCreateDTO newEvent) {
        Event event = modelMapper.map(newEvent, Event.class);
        return repository.saveAndFlush(event);
    }

//    public Employee save(EmployeeDTO newEmployee) {
//        Employee e = modelMapper.map(newEmployee, Employee.class);
//        return repository.saveAndFlush(e);
//
//    }

//    public Event save(Event newEvent) {
////        Event event = modelMapper.map(newEvent, Event.class);
//        return repository.saveAndFlush(newEvent);
//    }

    public void delete(@PathVariable Integer id) {
        repository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        id + " does not exist !!!"));
        repository.deleteById(id);
    }

    public Event reschedule(EventRescheduleDTO updateData, Integer id) {
        Event existingEvent = repository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        id + " does not exist !!!"));
        existingEvent.setEventStartTime(updateData.getEventStartTime());
        existingEvent.setEventNote(updateData.getEventNote());
        return repository.saveAndFlush(existingEvent);
    }

    public Event update(Event updateEvent, Integer id) {
        Event event = repository.findById(id)
                .map(o->mapEvent(o, updateEvent))
                .orElseGet(()-> {
                    updateEvent.setId(id);
                    return updateEvent;
                });
        return repository.saveAndFlush(event);
    }

    private Event mapEvent(Event existingEvent , Event updateEvent){
        existingEvent.setBookingEmail(updateEvent.getBookingEmail());
        existingEvent.setEventCategory(updateEvent.getEventCategory());
        existingEvent.setBookingName(updateEvent.getBookingName());
        existingEvent.setEventDuration(updateEvent.getEventDuration());
        existingEvent.setEventStartTime(updateEvent.getEventStartTime());
        existingEvent.setEventNote(updateEvent.getEventNote());
        return existingEvent;
    }
}