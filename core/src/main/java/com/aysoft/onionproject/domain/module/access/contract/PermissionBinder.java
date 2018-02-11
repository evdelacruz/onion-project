package com.aysoft.onionproject.domain.module.access.contract;

import com.aysoft.onionproject.domain.module.access.contract.to.PermissionCriteriaTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionLocalizationTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionTO;
import com.aysoft.onionproject.domain.module.access.datasource.domain.Permission;
import com.aysoft.onionproject.domain.module.access.datasource.domain.PermissionLocalization;
import com.aysoft.onionproject.domain.module.access.datasource.query.PermissionCriteria;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import java.util.List;

@Mapper
public interface PermissionBinder {
    PermissionBinder PERMISSION_BINDER = Mappers.getMapper(PermissionBinder.class);

    PermissionCriteria bind(PermissionCriteriaTO source);

    PermissionLocalizationTO bind(PermissionLocalization source);

    @Mapping(target="name", source="name")
    PermissionTO basic(Permission source);

    default PagedList<PermissionTO> bind(Page<Permission> source) {
        List<PermissionTO> orders = source.map(this::basic).getContent();
        return new PagedList<>(orders, source.getNumber(), source.getTotalPages(), source.getTotalElements());
    }
}
