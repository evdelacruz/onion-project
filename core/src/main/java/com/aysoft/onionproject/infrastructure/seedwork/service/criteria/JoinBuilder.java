package com.aysoft.onionproject.infrastructure.seedwork.service.criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.Arrays;

public class JoinBuilder<J, R> {
    JoinPredicateBuilder<J, R> builder;

    public JoinBuilder(Root<R> root, CriteriaBuilder builder) {
        this.builder = new JoinPredicateBuilder<>(root, builder);
    }

    public JoinPredicateBuilder<J, R> on(String field) {
        return this.on(field, JoinType.INNER);
    }

    public JoinPredicateBuilder<J, R> on(String field, JoinType type) {
        return this.on(field, type, true);
    }

    public JoinPredicateBuilder<J, R> on(String field, JoinType type, boolean conjunction) {
        return this.builder.on(field, type, conjunction);
    }

    void build() {
        this.builder.build();
    }

    public static class JoinPredicateBuilder<J, R> extends PredicateBuilder<R> {
        private Join<J, R> join;
        private boolean conjunction;

        private JoinPredicateBuilder(Root<R> root, CriteriaBuilder builder) {
            this.root = root;
            this.builder = builder;
        }

        private JoinPredicateBuilder<J, R> on(String field, JoinType type, boolean conjunction) {
            join = root.join(field, type);
            this.conjunction = conjunction;
            return this;
        }

        private void build() {
            if (0 < index) {
                if (conjunction || 1 == index) {
                    join.on(predicates);
                    return;
                }
                join.on(builder.or(index < predicates.length ? Arrays.copyOf(predicates, index) : predicates));
            }
        }
    }
}
