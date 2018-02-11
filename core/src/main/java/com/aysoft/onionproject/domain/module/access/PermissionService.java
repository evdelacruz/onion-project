package com.aysoft.onionproject.domain.module.access;

import com.aysoft.onionproject.domain.module.access.contract.to.PermissionCriteriaTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionLocalizationTO;
import com.aysoft.onionproject.domain.module.access.contract.to.PermissionTO;
import com.aysoft.onionproject.domain.module.access.contract.validation.constraints.ValidPermission;
import com.aysoft.onionproject.infrastructure.seedwork.service.criteria.PagedList;
import java.util.Locale;
import java.util.Map;

public interface PermissionService {

    PagedList<PermissionTO> loadAll(PermissionCriteriaTO criteria);

    Map<Locale, PermissionLocalizationTO> loadLocalizations(@ValidPermission int id);
}
