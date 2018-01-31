package com.aysoft.onionproject.dist.rest;

import static com.aysoft.onionproject.infrastructure.seedwork.binding.error.Error.*;
import static com.aysoft.onionproject.infrastructure.seedwork.i18n.MessageContex.*;
import static java.util.stream.Collectors.*;
import static org.springframework.http.ResponseEntity.*;

import com.aysoft.onionproject.infrastructure.seedwork.binding.error.Error;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.Map;
import java.util.function.Function;

@ControllerAdvice
public class GlobalController extends com.aysoft.onionproject.infrastructure.seedwork.binding.error.ExceptionHandler<ResponseEntity<Error>> {
    private static final int BAD_REQUEST_CODE = 4000;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        return super.handleException(ex);
    }

    @Override
    public void initialize(Map<Class<? extends Exception>, Function<Exception, ResponseEntity<Error>>> responses) {
        responses.put(HttpRequestMethodNotSupportedException.class, ex -> this.getResponse((HttpRequestMethodNotSupportedException) ex));
        responses.put(MissingServletRequestParameterException.class, ex -> this.getResponse((MissingServletRequestParameterException) ex));
        responses.put(MethodArgumentTypeMismatchException.class, ex -> this.getResponse((MethodArgumentTypeMismatchException) ex));
        responses.put(BindException.class, ex -> this.getResponse((BindException) ex));
        responses.put(HttpMessageNotReadableException.class, ex -> badRequest().body(err(BAD_REQUEST_CODE, msg("msg.core.error.malformed_message"))));
        responses.put(MethodArgumentNotValidException.class, ex -> this.getResponse((MethodArgumentNotValidException) ex));
    }

    @Override
    public ResponseEntity<Error> getDefaultResponse() {
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(err(5000, msg("msg.core.error.internal_error")));
    }

    //<editor-fold desc="Support methods">
    private ResponseEntity<Error> getResponse(HttpRequestMethodNotSupportedException ex) {
        return notFound().allow(ex.getSupportedHttpMethods().toArray(new HttpMethod[0])).build();
    }

    private ResponseEntity<Error> getResponse(MissingServletRequestParameterException ex) {
        return badRequest().body(err(BAD_REQUEST_CODE, msg("msg.core.error.missing_param", ex.getParameterName())));
    }

    private ResponseEntity<Error> getResponse(MethodArgumentTypeMismatchException ex) {
        return badRequest().body(err(BAD_REQUEST_CODE, msg("msg.core.error.invalid_type_params", ex.getName())));
    }

    private ResponseEntity<Error> getResponse(BindException ex) {
        String params = ex.getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(joining(", "));
        return badRequest().body(err(BAD_REQUEST_CODE, msg("msg.core.error.invalid_type_params", params)));
    }

    private ResponseEntity<Error> getResponse(MethodArgumentNotValidException ex) {
        String params = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(joining(", "));
        return badRequest().body(err(BAD_REQUEST_CODE, msg("msg.core.error.validation_failed", params)));
    }
    //</editor-fold>
}
