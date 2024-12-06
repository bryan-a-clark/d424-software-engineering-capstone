package edu.wgu.activitytracker.dto;

import edu.wgu.activitytracker.utils.DurationFormatter;
import edu.wgu.activitytracker.validation.EndDateAfterStartDate;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EndDateAfterStartDate
public class ReportDto {

    @NotNull(message="Start date is required")
    private LocalDate startDate;

    @NotNull(message="End date is required")
    private LocalDate endDate;

    private List<UserActivityDto> userActivities;

    private int numberOfActivities;

    private Duration totalDuration;

    private Duration averageDuration;

    public String getFormattedTotalDuration() {
        if (totalDuration == null) {
            return null;
        }
        return DurationFormatter.getFormattedDuration(totalDuration);
    }

    public String getFormattedAverageDuration() {
        if (averageDuration == null) {
            return null;
        }
        return DurationFormatter.getFormattedDuration(averageDuration);
    }
}
