package edu.wgu.activitytracker.services;

import edu.wgu.activitytracker.entities.Activity;
import edu.wgu.activitytracker.repositories.ActivityRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity getActivityByName(String name) {
        return activityRepository.findByName(name);
    }
}
