package edu.wgu.activitytracker.repositories;

import edu.wgu.activitytracker.entities.UserActivity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
    Optional<List<UserActivity>> getUserActivitiesByUserId(int userId);
}
