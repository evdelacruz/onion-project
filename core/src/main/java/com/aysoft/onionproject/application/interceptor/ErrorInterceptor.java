package com.aysoft.onionproject.application.interceptor;

import static com.aysoft.onionproject.infrastructure.seedwork.service.Result.*;
import static com.aysoft.onionproject.infrastructure.seedwork.binding.error.Error.*;
import static com.aysoft.onionproject.infrastructure.seedwork.i18n.MessageContex.*;

import com.aysoft.onionproject.infrastructure.seedwork.binding.error.ExceptionHandler;
import com.aysoft.onionproject.infrastructure.seedwork.service.Result;
import com.aysoft.onionproject.infrastructure.seedwork.service.stereotype.Interceptor;
import com.aysoft.onionproject.infrastructure.seedwork.validation.CustomValidatorAdapter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataIntegrityViolationException;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.function.Function;

@Aspect
@Interceptor
public class ErrorInterceptor extends ExceptionHandler<Result> {
    private CustomValidatorAdapter adapter = new CustomValidatorAdapter();

    @Override
    public void initialize(Map<Class<? extends Exception>, Function<Exception, Result>> responses) {
        responses.put(DataIntegrityViolationException.class, ex -> failed(err(1003, msg("msg.core.application.inconsistency_operation"))));
        responses.put(ConstraintViolationException.class, ex -> failed(adapter.extractErrors(((ConstraintViolationException)ex).getConstraintViolations())));
        //TODO Find delete constraint and test it
    }

    @Override
    public Result getDefaultResponse() {
        return failed(err(1000, msg("msg.core.error.internal_error")));
    }

    @Around("execution(com.aysoft.onionproject.infrastructure.seedwork.service.Result com.aysoft.onionproject.application.module.*.impl.*FacadeImpl.*(..))")
    public Object intercept(ProceedingJoinPoint procedure) throws Throwable {
        try {
            return procedure.proceed();
        } catch (Exception ex) {
            return this.handleException(ex);
        }
    }
}
