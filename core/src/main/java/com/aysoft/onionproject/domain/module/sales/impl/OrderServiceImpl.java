package com.aysoft.onionproject.domain.module.sales.impl;

import com.aysoft.onionproject.domain.module.sales.OrderService;
import com.aysoft.onionproject.domain.module.sales.contract.to.OrderCriteriaTO;
import com.aysoft.onionproject.domain.module.sales.contract.to.OrderTO;
import com.aysoft.onionproject.domain.module.sales.datasource.OrderRepository;
import com.aysoft.onionproject.domain.module.sales.datasource.domain.Order;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import com.aysoft.onionproject.infrastructure.seedwork.service.stereotype.Service;
import org.springframework.data.domain.Page;
import javax.inject.Inject;

import static com.aysoft.onionproject.domain.module.sales.contract.OrderBinder.ORDER_BINDER;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private @Inject OrderRepository orderRepository;

    public OrderServiceImpl() {}

    public int add(OrderTO orderTO) {
        Order order = ORDER_BINDER.bind(orderTO);
        return orderRepository.save(order).getId();
    }

    public void update(OrderTO orderTO) {
        orderRepository.findOne(orderTO.getId()).ifPresent(order ->
            ORDER_BINDER.bind(orderTO, order)
        );
    }

    public OrderTO load(int id) {
        return orderRepository.findOne(id).map(ORDER_BINDER::bind).get();
    }

    public PagedList<OrderTO> loadAll(OrderCriteriaTO criteria) {
        Page<Order> page = orderRepository.findAll(ORDER_BINDER.bind(criteria));
        return ORDER_BINDER.bind(page);
    }
}
