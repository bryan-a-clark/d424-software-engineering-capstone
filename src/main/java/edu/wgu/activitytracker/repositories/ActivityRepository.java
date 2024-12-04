package edu.wgu.activitytracker.repositories;

import edu.wgu.activitytracker.entities.Activity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    Activity findByName(String name);
    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT a FROM Activity a WHERE a.user.id = :userId or a.user.id is null")
    Optional<List<Activity>> findAllByUserId(Integer userId);
}
