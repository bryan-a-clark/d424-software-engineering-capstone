package edu.wgu.activitytracker.services;

import edu.wgu.activitytracker.dto.ReportDto;
import edu.wgu.activitytracker.dto.UserActivityDto;
import java.time.Duration;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportService {

    private final UserActivityService userActivityService;

    public ReportDto generateReportData(ReportDto reportDto) {
        List<UserActivityDto> userActivities = userActivityService.findLoggedInUsersActivitiesByUserIdAndDateRange(reportDto.getStartDate().atStartOfDay(), reportDto.getEndDate().atTime(23, 59, 59, 999999999));
        if (!userActivities.isEmpty()) {
            var numberOfUserActivities = userActivities.size();
            var totalDuration = calculateTotalDuration(userActivities);
            var averageDuration = calculateAverageDuration(totalDuration, numberOfUserActivities);

            reportDto.setUserActivities(userActivities);
            reportDto.setNumberOfActivities(numberOfUserActivities);
            reportDto.setTotalDuration(totalDuration);
            reportDto.setAverageDuration(averageDuration);
        }

        return reportDto;
    }

    private Duration calculateTotalDuration(List<UserActivityDto> userActivities) {
        Duration totalDuration = Duration.ZERO;
        for (UserActivityDto userActivityDto : userActivities) {
            totalDuration = totalDuration.plus(userActivityDto.getDuration());
        }
        return totalDuration;
    }

    private Duration calculateAverageDuration(Duration totalDuration, Integer numberOfActivities) {
        return totalDuration.dividedBy(numberOfActivities);
    }
}
