package com.aysoft.onionproject.infrastructure.seedwork.service.criteria;

import com.aysoft.onionproject.infrastructure.seedwork.service.repository.domain.AbstractEntity;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public abstract class AbstractSpecification<E extends AbstractEntity> implements Specification<E> {
    private Predicate[] predicates = new Predicate[20];
    private int index = 0;
    private Root<E> root;
    private CriteriaBuilder builder;

    public abstract void buildPredicate();

    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        this.root = root;
        this.builder = builder;
        return this.getPredicate();
    }

    protected void eq(String field, Object value) {
        predicates[index++] = builder.equal(root.get(field), value);
    }

    protected void eq(Supplier<Boolean> condition, String field, Object value) {
        if (condition.get()) {
            this.eq(field, value);
        }
    }

    protected void match(String field, String value) {
        predicates[index++] = builder.like(builder.lower(root.get(field)), String.format("%%%s%%", value.toLowerCase()));
    }

    protected void match(Supplier<Boolean> condition, String field, String value) {
        if (condition.get()) {
            this.match(field, value);
        }
    }

    protected void join(BiConsumer<Root<E>, CriteriaBuilder> function) {
        function.accept(root, builder);
    }

    //<editor-fold desc="Support methods">
    private Predicate getPredicate() {
        this.buildPredicate();
        if (index == 0) {
            return null;
        }
        if (index == 1) {
            return predicates[0];
        }
        return builder.and(index < predicates.length ? Arrays.copyOf(predicates, index) : predicates);
    }
    //</editor-fold>
}
