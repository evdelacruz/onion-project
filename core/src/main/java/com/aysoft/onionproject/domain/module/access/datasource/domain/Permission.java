package com.aysoft.onionproject.domain.module.access.datasource.domain;

import com.aysoft.onionproject.infrastructure.seedwork.service.repository.domain.AbstractEntity;
import javax.persistence.*;
import java.util.Locale;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static com.aysoft.onionproject.infrastructure.seedwork.i18n.LocaleContex.getLocale;

@Entity
@Table(name="permission", schema="public")
public class Permission extends AbstractEntity {
    private static final long serialVersionUID = -4908321530202873492L;
    private int id;
    private Map<Locale, PermissionLocalization> localizations;
    private String code;

    public Permission() {}

    //<editor-fold desc="Support methods">
    private PermissionLocalization localization(Locale provided) {
        return this.getLocalizations().computeIfAbsent(provided, locale -> new PermissionLocalization(this, locale));
    }
    //</editor-fold>

    //<editor-fold desc="Localized Encapsulation">
    @Transient
    public String getName() {
        return this.localization(getLocale()).getName();
    }

    public void setName(Locale locale, String name) {
        this.localization(locale).setName(name);
    }

    @Transient
    public String getDescription() {
        return this.localization(getLocale()).getDescription();
    }

    public void setDescription(Locale locale, String name) {
        this.localization(locale).setDescription(name);
    }
    //</editor-fold>

    //<editor-fold desc="Encapsulation">
    @Id
    @Column(name="pk_permission")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToMany(mappedBy="permission", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @MapKeyColumn(name="pk_locale")
    public Map<Locale, PermissionLocalization> getLocalizations() {
        return null == localizations ? localizations = emptyMap() : localizations;
    }

    public void setLocalizations(Map<Locale, PermissionLocalization> localizations) {
        this.localizations = localizations;
    }

    @Column(name="code", unique=true, length=50)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    //</editor-fold>
}
