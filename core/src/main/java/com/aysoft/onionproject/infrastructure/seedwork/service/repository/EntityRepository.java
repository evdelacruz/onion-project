package com.aysoft.onionproject.infrastructure.seedwork.service.repository;

import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.Criteria;
import com.aysoft.onionproject.infrastructure.seedwork.service.repository.domain.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public interface EntityRepository<E extends AbstractEntity, I extends Serializable> extends Repository<E, I> {

    E save(E entity);

    void delete(I id);

    void delete(E entity);

    Optional<E> findOne(I id);

    Stream<E> findAll();

    default Page<E> findAll(Criteria<E> criteria) {
        return this.findAll(criteria, criteria.pageRequest());
    }

    Page<E> findAll(Specification<E> spec, Pageable pageable);
}
