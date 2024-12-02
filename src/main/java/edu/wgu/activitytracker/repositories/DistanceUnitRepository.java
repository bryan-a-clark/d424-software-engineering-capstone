package edu.wgu.activitytracker.repositories;

import edu.wgu.activitytracker.entities.DistanceUnit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DistanceUnitRepository extends JpaRepository<DistanceUnit, Integer> {
    DistanceUnit findByName(String name);
    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT d FROM DistanceUnit d WHERE d.user.id = :userId or d.user.id is null")
    Optional<List<DistanceUnit>> findAllByUserId(Integer userId);
}
