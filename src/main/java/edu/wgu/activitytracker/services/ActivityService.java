package edu.wgu.activitytracker.services;

import edu.wgu.activitytracker.datahandlers.ActivityMapper;
import edu.wgu.activitytracker.dto.ActivityDto;
import edu.wgu.activitytracker.entities.Activity;
import edu.wgu.activitytracker.repositories.ActivityRepository;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActivityService {

    private final UserService userService;
    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<ActivityDto> getAllActivitiesByLoggedInUser() {
        var activities = activityRepository.findAllByUserId(userService.getCurrentlyLoggedInUser().getId()).orElse(Collections.emptyList());
        return activities.stream()
            .map(activityMapper::mapEntityToDto)
            .toList();
    }

    public void saveActivity(ActivityDto activityDto) {
        activityRepository.save(activityMapper.mapDtoToEntity(activityDto));
    }

    public void deleteActivity(Integer activityId) {
        activityRepository.deleteById(activityId);
    }

    public Activity getActivityByName(String name) {
        return activityRepository.findByName(name);
    }

    public ActivityDto getActivityById(Integer id) {
        var savedActivity = activityRepository.findById(id).orElse(null);
        if (savedActivity != null) {
            return activityMapper.mapEntityToDto(savedActivity);
        }
        return null;
    }

    public boolean checkExistenceByNameIgnoreCase(String name) {
        return activityRepository.existsByNameIgnoreCase(name);
    }
}
