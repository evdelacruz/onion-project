package com.aysoft.onionproject.application.module.sales.impl;

import com.aysoft.onionproject.application.module.sales.OrderFacade;
import com.aysoft.onionproject.domain.module.sales.OrderService;
import com.aysoft.onionproject.domain.module.sales.contract.to.OrderCriteriaTO;
import com.aysoft.onionproject.domain.module.sales.contract.to.OrderTO;
import com.aysoft.onionproject.infrastructure.seedwork.service.Result;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import com.aysoft.onionproject.infrastructure.seedwork.service.stereotype.Facade;
import com.aysoft.onionproject.infrastructure.seedwork.service.stereotype.Transactional;
import javax.inject.Inject;

import static com.aysoft.onionproject.infrastructure.seedwork.service.Result.*;

@Facade("orderFacade")
@Transactional
public class OrderFacadeImpl implements OrderFacade {
    private @Inject OrderService orderService;

    public OrderFacadeImpl() {}

    public Result<Integer> addOrder(OrderTO order) {
        int id = orderService.add(order);
        return successful(id);
    }

    public Result<Integer> updateOrder(OrderTO order) {
        orderService.update(order);
        return successful();
    }

    @Transactional(readOnly=true)
    public Result<OrderTO> loadOrder(int id) {
        OrderTO order = orderService.load(id);
        return successful(order);
    }

    @Transactional(readOnly=true)
    public Result<PagedList<OrderTO>> searchOrders(OrderCriteriaTO criteria) {
        PagedList<OrderTO> orders = orderService.loadAll(criteria);
        return successful(orders);
    }
}
