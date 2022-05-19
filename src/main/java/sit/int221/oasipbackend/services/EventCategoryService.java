package sit.int221.oasipbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipbackend.dtos.EventDetailDTO;
import sit.int221.oasipbackend.dtos.SimpleEventCategoryDTO;
import sit.int221.oasipbackend.entities.Event;
import sit.int221.oasipbackend.entities.EventCategory;
import sit.int221.oasipbackend.repositories.EventCategoryRepository;
import sit.int221.oasipbackend.repositories.EventRepository;

import java.util.List;

@Service
public class EventCategoryService {
    @Autowired
    private EventCategoryRepository repository;

    public List<EventCategory> getAllCategory() {
        List<EventCategory> eventList = repository.findAll();
        return eventList;
    }

    public EventCategory getCategoryById(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " Dose not exits!!!"));
    }

    public EventCategory updateCategory(SimpleEventCategoryDTO updateCategory, Integer categoryId) {
        EventCategory existingCategory = repository.findById(categoryId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, categoryId + " Dose not exits!!!"));

        existingCategory.setEventCategoryName(updateCategory.getEventCategoryName());
        existingCategory.setEventCategoryDescription(updateCategory.getEventCategoryDescription());
        existingCategory.setEventDuration(updateCategory.getEventDuration());
        return repository.saveAndFlush(existingCategory);
    }

}
