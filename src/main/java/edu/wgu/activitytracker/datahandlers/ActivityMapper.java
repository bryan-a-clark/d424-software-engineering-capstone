package edu.wgu.activitytracker.datahandlers;

import edu.wgu.activitytracker.dto.ActivityDto;
import edu.wgu.activitytracker.entities.Activity;
import edu.wgu.activitytracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActivityMapper implements Mapper<ActivityDto, Activity> {

    private UserService userService;

    @Override
    public Activity mapDtoToEntity(ActivityDto activityDto) {
        return new Activity(activityDto.getId(), activityDto.getName(), userService.getCurrentlyLoggedInUser());
    }

    @Override
    public ActivityDto mapEntityToDto(Activity activity) {
        if (activity == null) {
            return null;
        }
        if (activity.getUser() == null) {
            return new ActivityDto(activity.getId(), activity.getName(), true);
        } else {
            return new ActivityDto(activity.getId(), activity.getName(), false);
        }
    }
}
