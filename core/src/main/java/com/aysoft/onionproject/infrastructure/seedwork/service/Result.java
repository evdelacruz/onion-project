package com.aysoft.onionproject.infrastructure.seedwork.service;

import static java.util.Collections.singletonList;

import com.aysoft.onionproject.infrastructure.seedwork.binding.error.Error;
import java.util.List;
import java.util.function.Consumer;

public class Result<R> {
    private List<Error> errors;
    private R val;
    //private Error error;
    private boolean success;

    public Result() {}

    public static <O> Result<O> successful() {
        return new Result<O>().success(null);
    }

    public static <O> Result<O> successful(O result) {
        return new Result<O>().success(result);
    }

    public static <O>  Result<O> failed(Error error) {
        return new Result<O>().failure(singletonList(error));
    }

    public static <O>  Result<O> failed(List<Error> errors) {
        return new Result<O>().failure(errors);
    }

    public ElseFailed ifSuccess(Consumer<R> function) {
        if (success) {
            function.accept(val);
            return ElseFailed.EMPTY;
        }
        return new ElseFailed(errors);
        //return new ElseFailed(error);
    }

    //<editor-fold desc="Support Methods">
    private Result<R> success(R val) {
        //this.errors = Collections.emptyList();
        this.val = val;
        this.success = true;
        return this;
    }

    private Result<R> failure(List<Error> errors/*Error error*/) {
        this.errors = errors;
        //this.error = error;
        this.success = false;
        return this;
    }
    //</editor-fold>

    //<editor-fold desc="Encapsulation">
    public List<Error> getErrors() {
        return errors;
    }

    /*public Error getError() {
        return error;
    }*/

    public R getVal() {
        return val;
    }

    public boolean isSuccess() {
        return success;
    }
    //</editor-fold>

    //<editor-fold desc="Inner Classes">
    public static class ElseFailed {
        private static final ElseFailed EMPTY = new ElseFailed();
        private List<Error> errors;
        //private Error error;

        public ElseFailed() {}

        public ElseFailed(List<Error> errors/*Error error*/) {
            this.errors = errors;
            //this.error = error;
        }

        public void orElse(Consumer<List<Error>> function/*Consumer<Error> function*/) {
            if (null != errors) {
                function.accept(errors);
            }
            /*f (null != error) {
                function.accept(error);
            }*/
        }
    }
    //</editor-fold>
}
