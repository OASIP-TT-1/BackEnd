package sit.int221.oasipbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sit.int221.oasipbackend.entities.EventCategory;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRescheduleDTO {
//    private Integer id;
    private LocalDateTime eventStartTime;
    private String eventNote;
    private EventCategory EventCategory;
}
