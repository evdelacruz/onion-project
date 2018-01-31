package com.aysoft.onionproject.infrastructure.seedwork.binding.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class ExceptionHandler<R> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<Class<? extends Exception>, Function<Exception, R>> responses;

    public ExceptionHandler() {
        responses = new HashMap<>();
        this.initialize(responses);
    }

    public R handleException(Exception ex) {
        if (responses.containsKey(ex.getClass())) {
            logger.debug("Expected exception handled", ex);
            return responses.get(ex.getClass()).apply(ex);
        }
        logger.error("Unexpected exception handled", ex);
        return this.getDefaultResponse();
    }

    public abstract void initialize(Map<Class<? extends Exception>, Function<Exception, R>> responses);

    public abstract R getDefaultResponse();
}
