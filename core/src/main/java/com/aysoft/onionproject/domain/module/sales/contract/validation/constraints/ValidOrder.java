package com.aysoft.onionproject.domain.module.sales.contract.validation.constraints;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.aysoft.onionproject.domain.module.sales.contract.validation.validators.ValidOrderIdValidator;
import com.aysoft.onionproject.domain.module.sales.contract.validation.validators.ValidOrderTOValidator;
import com.aysoft.onionproject.infrastructure.seedwork.validation.annotations.ReferenceConstraint;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({PARAMETER})
@Retention(RUNTIME)
@Documented
@ReferenceConstraint
@Constraint(validatedBy={ValidOrderIdValidator.class, ValidOrderTOValidator.class})
public @interface ValidOrder {

    String message() default "com.aysoft.onionproject.domain.module.sales.contract.validation.constraints.ValidOrderId.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
