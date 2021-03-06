package it.univaq.disim.mwt.letsjam.presentation.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {EmailUniqueValidator.class})
@Documented
public @interface EmailUnique {

    String message() default "{validation.exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
