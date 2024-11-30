package edu.wgu.activitytracker.repositories;

import edu.wgu.activitytracker.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    Activity findByName(String name);
}
