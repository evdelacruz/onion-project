package com.aysoft.onionproject.domain.module.sales.contract;

import com.aysoft.onionproject.domain.module.sales.contract.to.OrderCriteriaTO;
import com.aysoft.onionproject.domain.module.sales.contract.to.OrderTO;
import com.aysoft.onionproject.domain.module.sales.datasource.domain.Order;
import com.aysoft.onionproject.domain.module.sales.datasource.query.OrderCriteria;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import java.util.List;

@Mapper
public interface OrderBinder {
    OrderBinder ORDER_BINDER = Mappers.getMapper(OrderBinder.class);

    OrderCriteria bind(OrderCriteriaTO source);

    @Mapping(target="id", ignore = true)
    @Mapping(target="state", expression="java(OrderState.CREATED)")
    @Mapping(target="date", expression="java(java.time.LocalDate.now())")
    Order bind(OrderTO source);

    @Mapping(target="id", ignore = true)
    @Mapping(target="date", ignore = true)
    @Mapping(target="state", ignore = true)
    void bind(OrderTO source, @MappingTarget Order target);

    @Mapping(target="state", source="state.id")
    OrderTO bind(Order source);

    @Mapping(target="state", ignore=true)
    OrderTO basic(Order source);

    default PagedList<OrderTO> bind(Page<Order> source) {
        List<OrderTO> orders = source.map(this::basic).getContent();
        return new PagedList<>(orders, source.getNumber(), source.getTotalPages(), source.getTotalElements());
    }
}
