package com.aysoft.onionproject.domain.module.sales.contract.validation.validators;

import com.aysoft.onionproject.domain.module.sales.contract.validation.constraints.UniqueOrderReference;
import com.aysoft.onionproject.domain.module.sales.contract.to.OrderTO;
import com.aysoft.onionproject.domain.module.sales.datasource.OrderRepository;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueOrderReferenceTOValidator implements ConstraintValidator<UniqueOrderReference, OrderTO> {
    private @Inject OrderRepository orderRepository;

    @Override
    public void initialize(UniqueOrderReference constraintAnnotation) {}

    @Override
    public boolean isValid(OrderTO orderTO, ConstraintValidatorContext context) {
        if (null == orderTO || null == orderTO.getReferenceId()) {
            return true;
        }
        return !orderRepository.findByReferenceId(orderTO.getReferenceId())
                .filter(order -> order.getId() != orderTO.getId())
                .isPresent();
    }
}
