package com.aysoft.onionproject.infrastructure.seedwork.service.stereotype;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface Interceptor {

    @AliasFor(annotation=Service.class, attribute="value")
    String value() default "";
}
