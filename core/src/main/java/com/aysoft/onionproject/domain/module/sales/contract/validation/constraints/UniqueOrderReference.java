package com.aysoft.onionproject.domain.module.sales.contract.validation.constraints;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.aysoft.onionproject.domain.module.sales.contract.validation.validators.UniqueOrderReferenceTOValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy={UniqueOrderReferenceTOValidator.class})
public @interface UniqueOrderReference {

    String message() default "{com.aysoft.onionproject.domain.module.sales.contract.validation.constraints.UniqueOrderReference.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
