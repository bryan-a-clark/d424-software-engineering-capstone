package edu.wgu.activitytracker.repositories;

import edu.wgu.activitytracker.entities.DistanceUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistanceUnitRepository extends JpaRepository<DistanceUnit, Integer> {
}
