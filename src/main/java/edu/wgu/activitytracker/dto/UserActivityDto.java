package edu.wgu.activitytracker.dto;

import edu.wgu.activitytracker.utils.DurationFormatter;
import edu.wgu.activitytracker.validation.EndDateTimeAfterStartDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EndDateTimeAfterStartDateTime
public class UserActivityDto {

    private Integer id;

    private Integer userId;

    @NotNull(message = "Activity is required")
    private String activityName;

    @Past
    private LocalDateTime startDateTime;

    @Past
    private LocalDateTime endDateTime;

    private Duration duration;

    private double distance;

    private String distanceUnitName;

    private String note;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Builder
    public UserActivityDto(String note, String distanceUnitName, double distance, Duration duration,
                           LocalDateTime endDateTime, LocalDateTime startDateTime, String activityName,
                           Integer userId, Integer id) {
        this.note = note;
        this.distanceUnitName = distanceUnitName;
        this.distance = distance;
        this.duration = duration;
        this.endDateTime = endDateTime;
        this.startDateTime = startDateTime;
        this.activityName = activityName;
        this.userId = userId;
        this.id = id;
    }

    public String getFormattedStartDateTime() {
        return startDateTime != null ? startDateTime.format(formatter) : null;
    }

    public String getFormattedEndDateTime() {
        return endDateTime != null ? endDateTime.format(formatter) : null;
    }

    public String getFormattedDuration() {
        if (duration == null) {
            return null;
        }
        return DurationFormatter.getFormattedDuration(duration);
    }
}
