package edu.wgu.activitytracker.validation;

import edu.wgu.activitytracker.services.DistanceUnitService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DistanceUnitValidator implements ConstraintValidator<UniqueDistanceUnitName, String> {

    private final DistanceUnitService distanceUnitService;

    @Override
    public boolean isValid(String distanceUnitName, ConstraintValidatorContext constraintValidatorContext) {
        if (distanceUnitName == null || distanceUnitName.isEmpty()) return true;
        return !distanceUnitService.checkExistenceByNameIgnoreCase(distanceUnitName);
    }
}
