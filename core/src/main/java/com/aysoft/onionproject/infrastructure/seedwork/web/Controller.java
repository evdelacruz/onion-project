package com.aysoft.onionproject.infrastructure.seedwork.web;

import com.aysoft.onionproject.infrastructure.seedwork.binding.error.Error;
import com.aysoft.onionproject.infrastructure.seedwork.binding.to.IdentifiableTO;
import com.aysoft.onionproject.infrastructure.seedwork.service.Result;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.inject.Inject;
import java.util.List;
import java.util.function.Function;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static org.springframework.http.ResponseEntity.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.*;

public abstract class Controller<F> {
    private static ResponseEntity NOT_CONTENT = noContent().build();
    private static ResponseEntity NOT_FOUND = notFound().build();
    private static ResponseEntity INTERNAL_ERROR = status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    private @Inject F facade;

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

    @SuppressWarnings("unchecked")
    protected final <TO> ResponseEntity get(Function<F, Result<TO>> function) {
        Result<TO> result = function.apply(facade);
        return result.isSuccess()
                ? ok(result.getVal())
                : this.errorResponse(result.getErrors());
    }

    @SuppressWarnings("unchecked")
    protected final <TO> ResponseEntity get(Function<F, Result<TO>> function, Function<TO, List<Link>> funcLinks) {
        Result<TO> result = function.apply(facade);
        return result.isSuccess()
                ? ok(new Resource(result.getVal(), funcLinks.apply(result.getVal())))
                : this.errorResponse(result.getErrors());
    }

    @SuppressWarnings("unchecked")
    protected final <TO extends IdentifiableTO> ResponseEntity getPagedList(Function<F, Result<PagedList<TO>>> function) {
        Result<PagedList<TO>> result = function.apply(facade);
        return result.isSuccess()
                ? ok(result.getVal().map(to -> new Resource(to, linkTo(this.getClass()).slash(to.getId()).withSelfRel())))
                : this.errorResponse(result.getErrors());
    }

    @SuppressWarnings("unchecked")
    protected final <TO> ResponseEntity getPagedList(Function<F, Result<PagedList<TO>>> function, Function<TO, List<Link>> funcLinks) {
        Result<PagedList<TO>> result = function.apply(facade);
        return result.isSuccess()
                ? ok(result.getVal().map(to -> new Resource(to, funcLinks.apply(to))))
                : this.errorResponse(result.getErrors());
    }

    //<editor-fold desc="Support methods">
    private ResponseEntity errorResponse(List<Error> errors) {
        return errors.stream()
                .filter(error -> 1004 == error.getCode() || 1000 == error.getCode())
                .findAny()
                .map(error -> 1000 == error.getCode() ? INTERNAL_ERROR : NOT_FOUND)
                .orElse(badRequest().body(errors));
    }
    //</editor-fold>
}
