package edu.wgu.activitytracker.validation;

import edu.wgu.activitytracker.dto.UserActivityDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserActivityValidator implements ConstraintValidator<EndDateAfterStartDate, UserActivityDto> {

    @Override
    public boolean isValid(UserActivityDto userActivityDto, ConstraintValidatorContext constraintValidatorContext) {
        if (userActivityDto.getStartDateTime() == null || userActivityDto.getEndDateTime() == null) {
            return true;
        }
        return userActivityDto.getEndDateTime().isAfter(userActivityDto.getStartDateTime());
    }
}
