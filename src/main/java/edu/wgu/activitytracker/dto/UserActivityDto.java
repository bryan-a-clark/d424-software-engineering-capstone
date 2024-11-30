package edu.wgu.activitytracker.dto;

import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
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
}
