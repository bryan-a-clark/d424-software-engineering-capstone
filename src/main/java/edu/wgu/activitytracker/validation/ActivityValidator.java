package edu.wgu.activitytracker.validation;

import edu.wgu.activitytracker.services.ActivityService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActivityValidator implements ConstraintValidator<UniqueActivityName, String> {

    private final ActivityService activityService;

    @Override
    public boolean isValid(String activityName, ConstraintValidatorContext constraintValidatorContext) {
        if (activityName == null || activityName.isEmpty()) return true;
        return !activityService.checkExistenceByNameIgnoreCase(activityName);
    }
}
