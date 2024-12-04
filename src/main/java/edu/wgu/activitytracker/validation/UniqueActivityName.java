package edu.wgu.activitytracker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ActivityValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueActivityName {
    String message() default "An activity with this name already exists.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
