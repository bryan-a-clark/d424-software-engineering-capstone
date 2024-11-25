package validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUserValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUser {
    String message() default "A user with this username already exists.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
