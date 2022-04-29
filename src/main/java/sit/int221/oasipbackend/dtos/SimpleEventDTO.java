package sit.int221.oasipbackend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
public class SimpleEventDTO {
    private Integer id;
    private String bookingName;
    private Instant eventStartTime;
    private Integer eventDuration;
    @JsonIgnore
    private SimpleEventCategoryDTO eventCategory;

    public String getEventCategoryName() {
        return eventCategory.getEventCategoryName();
    }

}
