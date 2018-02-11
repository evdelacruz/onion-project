package com.aysoft.onionproject.domain.module.access.datasource.domain;

import com.aysoft.onionproject.infrastructure.seedwork.service.repository.domain.AbstractEntity;
import javax.persistence.*;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name="permission_localization", schema="public")
public class PermissionLocalization extends AbstractEntity {
    private static final long serialVersionUID = -7593484935560733457L;
    private Permission permission;
    private Locale locale;
    private String name;
    private String description;

    public PermissionLocalization() {}

    public PermissionLocalization(Permission permission, Locale locale) {
        this.permission = permission;
        this.locale = locale;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (null == obj || this.getClass() != obj.getClass())
            return false;
        PermissionLocalization localization = (PermissionLocalization) obj;
        return (null == permission
                ? null == localization.permission
                : Objects.equals(permission.getId(), null != localization.permission ? localization.permission.getId() : null)) &&
                (null == locale
                ? null == localization.locale
                : Objects.equals(locale.getLanguage(), null != localization.locale ? localization.locale.getLanguage() : null)) &&
                Objects.equals(name, localization.name) &&
                Objects.equals(description, localization.description);
    }

    @Override
    public int hashCode() {
        int id = null == permission ? 0 : permission.getId();
        String lang = null == locale ? null : locale.getLanguage();
        return Objects.hash(id, lang, name, description);
    }

    //<editor-fold desc="Encapsulation">
    @Id
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="pk_permission", referencedColumnName="pk_permission")
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Id
    @Column(name="pk_locale", nullable=false, length=2)
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Column(name="name", nullable=false, length=100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description", nullable=false, columnDefinition="text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold>
}
