package com.aysoft.onionproject.domain.module.access.impl;

import com.aysoft.onionproject.domain.module.access.PermissionService;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionCriteriaTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionLocalizationTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionTO;
import com.aysoft.onionproject.domain.module.access.datasource.PermissionRepository;
import com.aysoft.onionproject.domain.module.access.datasource.domain.Permission;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import com.aysoft.onionproject.infrastructure.seedwork.service.stereotype.Service;
import org.springframework.data.domain.Page;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.aysoft.onionproject.domain.module.access.contract.PermissionBinder.PERMISSION_BINDER;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    private @Inject PermissionRepository permissionRepository;

    public PermissionServiceImpl() {}

    public PagedList<PermissionTO> loadAll(PermissionCriteriaTO criteria) {
        Page<Permission> permissions = permissionRepository.findAll(PERMISSION_BINDER.bind(criteria));
        return PERMISSION_BINDER.bind(permissions);
    }

    public Map<Locale, PermissionLocalizationTO> loadLocalizations(int id) {
        Map<Locale, PermissionLocalizationTO> localizations = new HashMap<>();
        permissionRepository.findOne(id).ifPresent(permission ->
            permission.getLocalizations().forEach((locale, localization) ->
                localizations.put(locale, PERMISSION_BINDER.bind(localization))
            )
        );
        return localizations;
    }
}
