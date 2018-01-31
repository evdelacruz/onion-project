package com.aysoft.onionproject.domain.module.sales.contract.validation.validators;

import com.aysoft.onionproject.domain.module.sales.contract.validation.constraints.ValidOrder;
import com.aysoft.onionproject.domain.module.sales.datasource.OrderRepository;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidOrderIdValidator implements ConstraintValidator<ValidOrder, Integer> {
    private @Inject OrderRepository orderRepository;

    @Override
    public void initialize(ValidOrder constraintAnnotation) {}

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return orderRepository.findOne(id).isPresent();
    }
}
