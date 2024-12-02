package edu.wgu.activitytracker.validation;

import edu.wgu.activitytracker.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidator implements ConstraintValidator<UniqueUser, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (username == null || username.isEmpty()) return true;
        return userService.getUserByUsername(username) == null;
    }
}
