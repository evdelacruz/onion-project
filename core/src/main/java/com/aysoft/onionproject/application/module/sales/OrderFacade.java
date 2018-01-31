package com.aysoft.onionproject.application.module.sales;

import com.aysoft.onionproject.domain.module.sales.contract.vo.OrderCriteriaTO;
import com.aysoft.onionproject.domain.module.sales.contract.vo.OrderTO;
import com.aysoft.onionproject.infrastructure.seedwork.service.Result;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;

public interface OrderFacade {

    Result<Integer> addOrder(OrderTO order);

    Result<Integer> updateOrder(OrderTO order);

    Result<OrderTO> loadOrder(int id);

    Result<PagedList<OrderTO>> searchOrders(OrderCriteriaTO criteria);
}
