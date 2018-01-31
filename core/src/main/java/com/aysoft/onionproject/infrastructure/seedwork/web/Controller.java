package com.aysoft.onionproject.infrastructure.seedwork.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.*;

import com.aysoft.onionproject.infrastructure.seedwork.binding.error.Error;
import com.aysoft.onionproject.infrastructure.seedwork.binding.vo.TransferObject;
import com.aysoft.onionproject.infrastructure.seedwork.service.Result;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.inject.Inject;
import java.util.List;
import java.util.function.Function;

public abstract class Controller<F> {
    private static ResponseEntity NOT_CONTENT = noContent().build();
    private static ResponseEntity NOT_FOUND = notFound().build();
    private static ResponseEntity INTERNAL_ERROR = status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    private @Inject F facade;

    @SuppressWarnings("unchecked")
    protected final <TO extends TransferObject> ResponseEntity wrapPagedList(Function<F, Result<PagedList<TO>>> function, Function<TO, ?> idExtractor) {
        Result<PagedList<TO>> result = function.apply(facade);
        return result.isSuccess()
                ? ok(result.getVal().map(vo -> new Resource(vo, linkTo(this.getClass()).slash(idExtractor.apply(vo)).withSelfRel())))
                : this.errorResponse(result.getErrors());
    }

    @SuppressWarnings("unchecked")
    protected final <TO extends TransferObject> ResponseEntity wrap(Function<F, Result<TO>> function, Function<TO, List<Link>> links) {
        Result<TO> result = function.apply(facade);
        return result.isSuccess()
                ? ok(new Resource(result.getVal(), links.apply(result.getVal())))
                : this.errorResponse(result.getErrors());
    }

    protected final ResponseEntity create(Function<F, Result<?>> function) {
        Result<?> result = function.apply(facade);
        return result.isSuccess()
                ? created(fromCurrentRequest().path("/{id}").buildAndExpand(result.getVal()).toUri()).build()
                : this.errorResponse(result.getErrors());
    }

    protected final ResponseEntity perform(Function<F, Result<?>> function) {
        Result<?> result = function.apply(facade);
        return result.isSuccess() ? NOT_CONTENT : this.errorResponse(result.getErrors());
    }

    private ResponseEntity errorResponse(List<Error> errors) {
//        Error error;
//        Iterator<Error> it = errors.iterator();
//        ResponseEntity response = null;
//        while (it.hasNext() && null == response) {
//            error = it.next();
//            if (1004 == error.getCode()) {
//                response = NOT_FOUND;
//            }
//            if (1000 == error.getCode()) {
//                response = INTERNAL_ERROR;
//            }
//        }
//        return null != response ? response : ResponseEntity.badRequest().body(errors);

        return errors.stream()
                .filter(error -> 1004 == error.getCode() || 1000 == error.getCode())
                .findAny()
                .map(error -> 1000 == error.getCode() ? INTERNAL_ERROR : NOT_FOUND)
                .orElse(badRequest().body(errors));
    }
}
