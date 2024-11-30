package edu.wgu.activitytracker.services;

import edu.wgu.activitytracker.datahandlers.UserActivityMapper;
import edu.wgu.activitytracker.datahandlers.UserMapper;
import edu.wgu.activitytracker.dto.UserActivityDto;
import edu.wgu.activitytracker.entities.User;
import edu.wgu.activitytracker.repositories.UserActivityRepository;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final UserActivityMapper userActivityMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    public List<UserActivityDto> getLoggedInUsersUserActivities() {
        User loggedInUser = userMapper.mapDtoToEntity(userService.getCurrentlyLoggedInUser());
        var userActivities = userActivityRepository.getUserActivitiesByUserId(loggedInUser.getId()).orElse(Collections.emptyList());
        return userActivities.stream()
            .map(userActivityMapper::mapEntityToDto)
            .toList();
    }

    public void addUserActivity(UserActivityDto userActivityDto) {
        userActivityRepository.save(userActivityMapper.mapDtoToEntity(userActivityDto));
    }
}
