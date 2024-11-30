package edu.wgu.activitytracker.services;

import edu.wgu.activitytracker.entities.DistanceUnit;
import edu.wgu.activitytracker.repositories.DistanceUnitRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DistanceUnitService {

    private final DistanceUnitRepository distanceUnitRepository;

    public List<DistanceUnit> getAllDistanceUnits() {
        return distanceUnitRepository.findAll();
    }

    public DistanceUnit getDistanceUnitByName(String name) {
        return distanceUnitRepository.findByName(name);
    }
}
