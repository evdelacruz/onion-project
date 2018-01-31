package com.aysoft.onionproject.domain.module.sales.datasource.query;

import com.aysoft.onionproject.domain.module.sales.datasource.domain.Order;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.Criteria;

public class OrderCriteria extends Criteria<Order> {
    private int state;

    @Override
    public String sortProperty() {
        return "id";
    }

    @Override
    public void buildPredicate() {
        this.eq("state", state);
    }

    //<editor-fold desc="Encapsulation">
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    //</editor-fold>
}
