package com.aysoft.onionproject.infrastructure.seedwork.service.stereotype;

import org.springframework.core.annotation.AliasFor;
import org.springframework.validation.annotation.Validated;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Validated
@org.springframework.stereotype.Service
public @interface Service {

    @AliasFor(annotation=org.springframework.stereotype.Service.class, attribute="value")
    String value() default "";
}
