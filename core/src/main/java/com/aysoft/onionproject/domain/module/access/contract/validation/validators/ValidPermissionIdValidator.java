package com.aysoft.onionproject.domain.module.access.contract.validation.validators;

import com.aysoft.onionproject.domain.module.access.contract.validation.constraints.ValidPermission;
import com.aysoft.onionproject.domain.module.access.datasource.PermissionRepository;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPermissionIdValidator implements ConstraintValidator<ValidPermission, Integer> {
    private @Inject PermissionRepository permissionRepository;

    @Override
    public void initialize(ValidPermission constraintAnnotation) {}

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return permissionRepository.findOne(id).isPresent();
    }
}
