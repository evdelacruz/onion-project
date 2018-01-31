package com.aysoft.onionproject.infrastructure.seedwork.service.catalog.stereotype;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Catalog {

    String propOrd() default "id";

    boolean orderable() default true;
}
