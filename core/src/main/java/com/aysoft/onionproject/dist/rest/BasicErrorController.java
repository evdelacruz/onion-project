package com.aysoft.onionproject.dist.rest;

import static com.aysoft.onionproject.infrastructure.seedwork.binding.error.Error.*;
import static com.aysoft.onionproject.infrastructure.seedwork.i18n.MessageContex.*;
import static org.springframework.http.HttpStatus.*;

import com.aysoft.onionproject.infrastructure.seedwork.binding.error.Error;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.ResponseEntity.*;

@RestController
public class BasicErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value="/error/{code}")
    public ResponseEntity<Error> error(@PathVariable(required=false) int code) {
        switch (code) {
            case 401:
                return status(UNAUTHORIZED).body(err(2000, msg("msg.core.error.unauthorized")));
            case 403:
                return status(FORBIDDEN).body(err(2001, msg("msg.core.error.forbidden")));
            case 404:
                return notFound().build();
            default://500
                return status(INTERNAL_SERVER_ERROR).body(err(1000, msg("msg.core.error.internal_error")));
        }
    }
}
