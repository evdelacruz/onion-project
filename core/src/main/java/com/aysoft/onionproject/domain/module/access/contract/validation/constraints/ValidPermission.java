package com.aysoft.onionproject.domain.module.access.contract.validation.constraints;

import com.aysoft.onionproject.domain.module.access.contract.validation.validators.ValidPermissionIdValidator;
import com.aysoft.onionproject.infrastructure.seedwork.validation.annotations.ReferenceConstraint;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER})
@Retention(RUNTIME)
@Documented
@ReferenceConstraint
@Constraint(validatedBy={ValidPermissionIdValidator.class})
public @interface ValidPermission {

    String message() default "com.aysoft.onionproject.domain.module.access.contract.validation.constraints.ValidPermission.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
