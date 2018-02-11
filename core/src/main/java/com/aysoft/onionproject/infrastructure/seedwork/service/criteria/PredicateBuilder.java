package com.aysoft.onionproject.infrastructure.seedwork.service.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Supplier;

public class PredicateBuilder<E> {
    protected Predicate[] predicates = new Predicate[20];
    protected int index = 0;
    protected Root<E> root;
    protected CriteriaBuilder builder;

    public void eq(String field, Object value) {
        predicates[index++] = builder.equal(root.get(field), value);
    }

    public void eq(Supplier<Boolean> condition, String field, Object value) {
        if (condition.get()) {
            this.eq(field, value);
        }
    }

    public void match(String field, String value) {
        predicates[index++] = builder.like(builder.lower(root.get(field)), String.format("%%%s%%", value.toLowerCase()));
    }

    public void match(Supplier<Boolean> condition, String field, String value) {
        if (condition.get()) {
            this.match(field, value);
        }
    }
}
