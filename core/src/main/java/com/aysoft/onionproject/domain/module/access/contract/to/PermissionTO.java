package com.aysoft.onionproject.domain.module.access.contract.to;

import com.aysoft.onionproject.infrastructure.seedwork.binding.annotation.ReadOnly;
import com.aysoft.onionproject.infrastructure.seedwork.binding.to.TransferObject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.Locale;
import java.util.Map;

public class PermissionTO extends TransferObject {
    private static final long serialVersionUID = 586896640703771410L;
    private int id;
    private Map<Locale, PermissionLocalizationTO> localizations;
    private String code;
    private String name;

    public PermissionTO() {}

    //<editor-fold desc="Localized Encapsulation">
    @Null
    @ReadOnly
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>

    //<editor-fold desc="Encapsulation">
    @ReadOnly
    public int getId() {
        return id;
    }

    public PermissionTO setId(int id) {
        this.id = id;
        return this;
    }

    @NotNull
    @Pattern(regexp="[A-Z]+")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    //</editor-fold>
}
