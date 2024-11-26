package edu.wgu.activitytracker.dto;

import edu.wgu.activitytracker.entities.DistanceUnit;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserActivityDto {

    private Integer id;

    @NotNull(message = "User ID is required")
    private Integer userId;

    @NotNull(message = "Activity ID is required")
    private Integer activityId;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Duration duration;

    private double distance;

    private DistanceUnit distanceUnit;

    private String note;
}
