package edu.wgu.activitytracker.services;

import edu.wgu.activitytracker.datahandlers.DistanceUnitMapper;
import edu.wgu.activitytracker.dto.DistanceUnitDto;
import edu.wgu.activitytracker.entities.DistanceUnit;
import edu.wgu.activitytracker.repositories.DistanceUnitRepository;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DistanceUnitService {

    private final UserService userService;
    private final DistanceUnitMapper distanceUnitMapper;
    private final DistanceUnitRepository distanceUnitRepository;

    public List<DistanceUnit> getAllDistanceUnits() {
        return distanceUnitRepository.findAll();
    }

    public List<DistanceUnitDto> getAllDistanceUnitsByLoggedInUser() {
        var distanceUnits = distanceUnitRepository.findAllByUserId(userService.getCurrentlyLoggedInUser().getId()).orElse(Collections.emptyList());
        return distanceUnits.stream()
            .map(distanceUnitMapper::mapEntityToDto)
            .toList();
    }

    public void saveDistanceUnit(DistanceUnitDto distanceUnitDto) {
        distanceUnitRepository.save(distanceUnitMapper.mapDtoToEntity(distanceUnitDto));
    }

    public void deleteDistanceUnit(Integer distanceUnitId) {
        distanceUnitRepository.deleteById(distanceUnitId);
    }

    public DistanceUnit getDistanceUnitByName(String name) {
        return distanceUnitRepository.findByName(name);
    }

    public boolean checkExistenceByNameIgnoreCase(String name) {
        return distanceUnitRepository.existsByNameIgnoreCase(name);
    }

    public DistanceUnitDto getDistanceUnitById(Integer id) {
        var savedDistanceUnit = distanceUnitRepository.findById(id).orElse(null);
        if (savedDistanceUnit != null) {
            return distanceUnitMapper.mapEntityToDto(savedDistanceUnit);
        }
        return null;
    }
}
