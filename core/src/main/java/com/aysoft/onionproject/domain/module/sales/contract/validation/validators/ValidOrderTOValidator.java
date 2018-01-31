package com.aysoft.onionproject.domain.module.sales.contract.validation.validators;

import com.aysoft.onionproject.domain.module.sales.contract.validation.constraints.ValidOrder;
import com.aysoft.onionproject.domain.module.sales.contract.vo.OrderTO;
import com.aysoft.onionproject.domain.module.sales.datasource.OrderRepository;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidOrderTOValidator implements ConstraintValidator<ValidOrder, OrderTO> {
    private @Inject OrderRepository orderRepository;

    @Override
    public void initialize(ValidOrder constraintAnnotation) {}

    @Override
    public boolean isValid(OrderTO order, ConstraintValidatorContext context) {
        return orderRepository.findOne(order.getId()).isPresent();
    }
}
