package edu.wgu.activitytracker.repositories;

import edu.wgu.activitytracker.entities.UserActivity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserActivityRepository extends JpaRepository<UserActivity, Integer> {
    Optional<List<UserActivity>> getUserActivitiesByUserId(int userId);

    @Query("SELECT ua FROM UserActivity ua WHERE ua.user.id = :userId AND ua.startDateTime >= :startDate AND ua.endDateTime <= :endDate")
    Optional<List<UserActivity>> findUserActivitiesByUserIdAndDateRange(@Param("userId") int userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
