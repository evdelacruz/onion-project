package com.aysoft.onionproject.domain.module.access.datasource;

import com.aysoft.onionproject.domain.module.access.datasource.domain.Permission;
import com.aysoft.onionproject.infrastructure.seedwork.service.repository.EntityRepository;

public interface PermissionRepository extends EntityRepository<Permission, Integer> {
}
