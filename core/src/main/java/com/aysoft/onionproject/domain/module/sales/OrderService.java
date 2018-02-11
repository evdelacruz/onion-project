package com.aysoft.onionproject.domain.module.sales;

import com.aysoft.onionproject.domain.module.sales.contract.validation.constraints.ValidOrder;
import com.aysoft.onionproject.domain.module.sales.contract.validation.constraints.UniqueOrderReference;
import com.aysoft.onionproject.domain.module.sales.contract.to.OrderCriteriaTO;
import com.aysoft.onionproject.domain.module.sales.contract.to.OrderTO;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import javax.validation.Valid;

public interface OrderService {

    int add(@UniqueOrderReference @Valid OrderTO orderTO);

    void update(@ValidOrder @UniqueOrderReference @Valid OrderTO orderTO);

    OrderTO load(@ValidOrder int id);

    PagedList<OrderTO> loadAll(OrderCriteriaTO criteria);
}
