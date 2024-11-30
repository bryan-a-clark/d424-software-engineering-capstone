package edu.wgu.activitytracker.datahandlers;

import edu.wgu.activitytracker.dto.UserActivityDto;
import edu.wgu.activitytracker.entities.UserActivity;
import edu.wgu.activitytracker.services.ActivityService;
import edu.wgu.activitytracker.services.DistanceUnitService;
import edu.wgu.activitytracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserActivityMapper implements Mapper<UserActivityDto, UserActivity> {

    private UserService userService;
    private ActivityService activityService;
    private DistanceUnitService distanceUnitService;

    @Override
    public UserActivity mapDtoToEntity(UserActivityDto userActivityDto) {
        return UserActivity.builder()
            .id(userActivityDto.getId())
            .user(userService.getCurrentlyLoggedInUser())
            .activity(activityService.getActivityByName(userActivityDto.getActivityName()))
            .startDateTime(userActivityDto.getStartDateTime())
            .endDateTime(userActivityDto.getEndDateTime())
            .distance(userActivityDto.getDistance())
            .distanceUnit(distanceUnitService.getDistanceUnitByName(userActivityDto.getDistanceUnitName()))
            .note(userActivityDto.getNote())
            .build();
    }

    @Override
    public UserActivityDto mapEntityToDto(UserActivity userActivity) {
        return UserActivityDto.builder()
            .id(userActivity.getId())
            .userId(userActivity.getUser().getId())
            .activityName(userActivity.getActivity().getName())
            .startDateTime(userActivity.getStartDateTime())
            .endDateTime(userActivity.getEndDateTime())
            .duration(userActivity.getDuration())
            .distance(userActivity.getDistance())
            .distanceUnitName(userActivity.getDistanceUnit().getName())
            .note(userActivity.getNote())
            .build();
    }
}
