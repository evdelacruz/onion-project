package com.aysoft.onionproject.infrastructure.seedwork.service.stereotype;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@org.springframework.transaction.annotation.Transactional
public @interface Transactional {

    @AliasFor(annotation=org.springframework.transaction.annotation.Transactional.class, attribute="readOnly")
    boolean readOnly() default false;
}
