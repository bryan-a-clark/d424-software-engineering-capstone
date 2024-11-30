package edu.wgu.activitytracker.dto;

import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserActivityDto {

    private Integer id;

    private Integer userId;

    @NotNull(message = "Activity is required")
    private String activityName;

    private LocalDateTime startDateTime;

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
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
