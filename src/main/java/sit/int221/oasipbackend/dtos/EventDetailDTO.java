package sit.int221.oasipbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class EventDetailDTO {
    private Integer id;
    private String bookingName;
    private String bookingEmail;
    private Instant eventStartTime;
    private Integer eventDuration;
    private String eventNote;
    @JsonIgnore
    private SimpleEventCategoryDTO eventCategory;

    public String getEventCategoryName() {
        return eventCategory.getEventCategoryName();
    }
}
