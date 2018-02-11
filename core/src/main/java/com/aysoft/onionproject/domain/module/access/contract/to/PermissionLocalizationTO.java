package com.aysoft.onionproject.domain.module.access.contract.to;

import com.aysoft.onionproject.infrastructure.seedwork.binding.to.TransferObject;

public class PermissionLocalizationTO extends TransferObject {
    private static final long serialVersionUID = 8059593873496600020L;
    private String name;
    private String description;

    public PermissionLocalizationTO() {}

    //<editor-fold desc="Encapsulation">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold>
}
