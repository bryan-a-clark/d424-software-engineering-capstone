package edu.wgu.activitytracker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserActivityValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EndDateTimeAfterStartDateTime {

    String message() default "End date time must be after start date time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
