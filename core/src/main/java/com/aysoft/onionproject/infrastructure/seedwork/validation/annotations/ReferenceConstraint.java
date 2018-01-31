package com.aysoft.onionproject.infrastructure.seedwork.validation.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Target({ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ReferenceConstraint {
}
