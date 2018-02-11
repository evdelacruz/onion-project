package com.aysoft.onionproject.domain.module.access.contract.to;

import com.aysoft.onionproject.infrastructure.seedwork.binding.to.CriteriaTO;

public class PermissionCriteriaTO extends CriteriaTO {
    private static final long serialVersionUID = 3092514522753378701L;
    private String name;

    public PermissionCriteriaTO() {}

    //<editor-fold desc="Encapsulation">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>
}
