package com.aysoft.onionproject.application.module.access;

import com.aysoft.onionproject.domain.module.access.contract.to.PermissionCriteriaTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionLocalizationTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionTO;
import com.aysoft.onionproject.infrastructure.seedwork.service.Result;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import java.util.Locale;
import java.util.Map;

public interface RoleFacade {

    Result<PagedList<PermissionTO>> searchPermissions(PermissionCriteriaTO criteria);

    Result<Map<Locale, PermissionLocalizationTO>> loadPermissionLocalizations(int id);
}
