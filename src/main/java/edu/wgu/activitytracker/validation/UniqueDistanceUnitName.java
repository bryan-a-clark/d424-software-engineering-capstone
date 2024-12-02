package edu.wgu.activitytracker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DistanceUnitValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueDistanceUnitName {
    String message() default "A distance unit with this name already exists.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
