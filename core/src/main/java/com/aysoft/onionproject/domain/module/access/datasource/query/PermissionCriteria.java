package com.aysoft.onionproject.domain.module.access.datasource.query;

import com.aysoft.onionproject.domain.module.access.datasource.domain.Permission;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.Criteria;

public class PermissionCriteria extends Criteria<Permission> {
    private String name;

    @Override
    public String sortProperty() {
        return null != name ? "localizations.name" : null;
    }

    @Override
    public void buildSpecification() {
        this.join(()-> null != name, joiner ->
            joiner.on("localizations").match("name", name)
        );
    }

    //<editor-fold desc="Encapsulation">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>
}
