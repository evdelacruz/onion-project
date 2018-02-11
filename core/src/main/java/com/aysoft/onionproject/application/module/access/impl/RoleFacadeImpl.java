package com.aysoft.onionproject.application.module.access.impl;

import com.aysoft.onionproject.application.module.access.RoleFacade;
import com.aysoft.onionproject.domain.module.access.PermissionService;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionCriteriaTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionLocalizationTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionTO;
import com.aysoft.onionproject.infrastructure.seedwork.service.Result;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import com.aysoft.onionproject.infrastructure.seedwork.service.stereotype.Facade;
import com.aysoft.onionproject.infrastructure.seedwork.service.stereotype.Transactional;
import javax.inject.Inject;
import java.util.Locale;
import java.util.Map;

import static com.aysoft.onionproject.infrastructure.seedwork.service.Result.successful;

@Facade("roleFacade")
@Transactional
public class RoleFacadeImpl implements RoleFacade {
    private @Inject PermissionService permissionService;

    public RoleFacadeImpl() {}

    @Transactional(readOnly=true)
    public Result<PagedList<PermissionTO>> searchPermissions(PermissionCriteriaTO criteria) {
        PagedList<PermissionTO> orders = permissionService.loadAll(criteria);
        return successful(orders);
    }

    @Transactional(readOnly=true)
    public Result<Map<Locale, PermissionLocalizationTO>> loadPermissionLocalizations(int id) {
        Map<Locale, PermissionLocalizationTO> localizations = permissionService.loadLocalizations(id);
        return successful(localizations);
    }
}
