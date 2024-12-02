package edu.wgu.activitytracker.datahandlers;

import edu.wgu.activitytracker.dto.DistanceUnitDto;
import edu.wgu.activitytracker.entities.DistanceUnit;
import edu.wgu.activitytracker.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DistanceUnitMapper implements Mapper<DistanceUnitDto, DistanceUnit> {

    private UserService userService;

    @Override
    public DistanceUnit mapDtoToEntity(DistanceUnitDto distanceUnitDto) {
        return new DistanceUnit(distanceUnitDto.getId(), distanceUnitDto.getName(), userService.getCurrentlyLoggedInUser());
    }

    //TODO refactor to check instance of
    @Override
    public DistanceUnitDto mapEntityToDto(DistanceUnit distanceUnit) {
        if (distanceUnit == null) {
            return null;
        }
        if (distanceUnit.getUser() == null) {
            return new DistanceUnitDto(distanceUnit.getId(), distanceUnit.getName(), true);
        } else {
            return new DistanceUnitDto(distanceUnit.getId(), distanceUnit.getName(), false);
        }
    }
}
