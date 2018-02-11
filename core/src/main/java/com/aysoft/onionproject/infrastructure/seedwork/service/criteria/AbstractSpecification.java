package com.aysoft.onionproject.infrastructure.seedwork.service.criteria;

import com.aysoft.onionproject.infrastructure.seedwork.service.repository.domain.AbstractEntity;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractSpecification<E extends AbstractEntity> extends PredicateBuilder<E> implements Specification<E> {

    public abstract void buildSpecification();

    public final Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        this.root = root;
        this.builder = builder;
        return this.build();
    }

    protected <J> void join(Supplier<Boolean> condition, Consumer<JoinBuilder<J,E>> function) {
        if (condition.get()) {
            this.join(function);
        }
    }

    protected <J> void join(Consumer<JoinBuilder<J,E>> function) {
        JoinBuilder<J,E> join = new JoinBuilder<>(root, builder);
        function.accept(join);
        join.build();
    }

    private Predicate build() {
        this.buildSpecification();
        if (0 == index) {
            return null;
        }
        if (1 == index) {
            return predicates[0];
        }
        return builder.and(index < predicates.length ? Arrays.copyOf(predicates, index) : predicates);
    }
}
