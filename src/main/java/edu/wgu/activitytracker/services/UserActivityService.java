package edu.wgu.activitytracker.services;

import edu.wgu.activitytracker.datahandlers.UserActivityMapper;
import edu.wgu.activitytracker.dto.UserActivityDto;
import edu.wgu.activitytracker.entities.User;
import edu.wgu.activitytracker.repositories.UserActivityRepository;
import java.time.LocalDateTime;
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

    public List<UserActivityDto> getLoggedInUsersUserActivities() {
        User loggedInUser = userService.getCurrentlyLoggedInUser();
        var userActivities = userActivityRepository.getUserActivitiesByUserId(loggedInUser.getId()).orElse(Collections.emptyList());
        return userActivities.stream()
            .map(userActivityMapper::mapEntityToDto)
            .toList();
    }

    public UserActivityDto getActivityById(Integer id) {
        var savedUserActivity = userActivityRepository.findById(id).orElse(null);
        if (savedUserActivity != null) {
            return userActivityMapper.mapEntityToDto(savedUserActivity);
        }
        return null;
    }

    public void saveUserActivity(UserActivityDto userActivityDto) {
        userActivityRepository.save(userActivityMapper.mapDtoToEntity(userActivityDto));
    }

    public void deleteUserActivity(Integer userActivityId) {
        userActivityRepository.deleteById(userActivityId);
    }

    public List<UserActivityDto> findLoggedInUsersActivitiesByUserIdAndDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        User loggedInUser = userService.getCurrentlyLoggedInUser();
        return userActivityRepository.findUserActivitiesByUserIdAndDateRange(loggedInUser.getId(), startDate, endDate).orElse(Collections.emptyList()).stream()
            .map(userActivityMapper::mapEntityToDto)
            .toList();
    }
}
