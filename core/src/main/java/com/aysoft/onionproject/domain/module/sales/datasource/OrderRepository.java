package com.aysoft.onionproject.domain.module.sales.datasource;

import com.aysoft.onionproject.domain.module.sales.datasource.domain.Order;
import com.aysoft.onionproject.infrastructure.seedwork.service.repository.EntityRepository;
import java.util.Optional;

public interface OrderRepository extends EntityRepository<Order, Integer> {

    Optional<Order> findByReferenceId(String referenceId);
}
