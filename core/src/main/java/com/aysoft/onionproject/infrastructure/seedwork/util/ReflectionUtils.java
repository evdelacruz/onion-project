package com.aysoft.onionproject.infrastructure.seedwork.util;

public final class ReflectionUtils {

    static public Object instantiate(String className) {
        Object obj;
        try {
            Class<?> clazz = Class.forName(className);
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException ex) {
            obj = null;
        }
        return obj;
    }

    private ReflectionUtils() {
        throw new AssertionError("No 'ReflectionUtils' instances for you!");
    }
}
