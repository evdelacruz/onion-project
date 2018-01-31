package com.aysoft.onionproject.domain.module.sales.contract.vo;

import com.aysoft.onionproject.infrastructure.seedwork.binding.vo.CriteriaTO;

public class OrderCriteriaTO extends CriteriaTO {
    private static final long serialVersionUID = -6146889409606660956L;
    private int state;

    public OrderCriteriaTO() {}

    //<editor-fold desc="Encapsulation">
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    //</editor-fold>
}
