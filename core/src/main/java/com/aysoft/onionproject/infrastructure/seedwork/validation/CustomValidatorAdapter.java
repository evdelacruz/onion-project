package com.aysoft.onionproject.infrastructure.seedwork.validation;

import static java.util.stream.Collectors.toList;
import static com.aysoft.onionproject.infrastructure.seedwork.binding.error.Error.err;

import com.aysoft.onionproject.domain.module.sales.contract.validation.constraints.UniqueOrderReference;
import com.aysoft.onionproject.infrastructure.seedwork.binding.error.Error;
import com.aysoft.onionproject.infrastructure.seedwork.validation.annotations.ReferenceConstraint;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.internal.engine.path.NodeImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.ConstraintViolation;
import javax.validation.ElementKind;
import javax.validation.Path;
import javax.validation.constraints.*;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomValidatorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CustomValidatorAdapter.class);
    private static final Map<Class<? extends Annotation>, Integer> codes;

    static  {
        codes = new HashMap<>();
        codes.put(NotNull.class, 1005);
        codes.put(AssertFalse.class, 1001);
        codes.put(AssertTrue.class, 1001);
        codes.put(DecimalMax.class, 1001);
        codes.put(DecimalMin.class, 1001);
        codes.put(Digits.class, 1001);
        codes.put(Max.class, 1001);
        codes.put(Min.class, 1001);
        codes.put(Null.class, 1001);
        codes.put(Future.class, 1001);
        codes.put(Past.class, 1001);
        codes.put(Pattern.class, 1002);
        codes.put(Size.class, 1001);
        codes.put(Email.class, 1001);
        codes.put(NotBlank.class, 1001);
//        org.hibernate.validator.constraints.Length.message                  = length must be between {min} and {max}
//        org.hibernate.validator.constraints.NotEmpty.message                = may not be empty
//        org.hibernate.validator.constraints.ParametersScriptAssert.message  = script expression "{script}" didn't evaluate to true
//        org.hibernate.validator.constraints.Range.message                   = must be between {min} and {max}
//        org.hibernate.validator.constraints.SafeHtml.message                = may have unsafe html content
//        org.hibernate.validator.constraints.ScriptAssert.message            = script expression "{script}" didn't evaluate to true
//        org.hibernate.validator.constraints.URL.message                     = must be a valid URL
        codes.put(UniqueOrderReference.class, 1001);
    }

    public List<Error> extractErrors(Collection<ConstraintViolation<?>> violations) {
        return violations.stream().map(this::getError).collect(toList());
    }

    private Error getError(ConstraintViolation<?> violation) {
        int code = this.determineCode(violation);
        String detail = null;
        if (1000 != code && 1004 != code) {
            String field = this.determineField(violation);
            detail = null != field ? String.format("%s -> %s", field, violation.getMessage())
                                   : violation.getMessage();
        }
        return err(code, detail);
    }

    private String determineField(ConstraintViolation<?> violation) {
        Path path = violation.getPropertyPath();
        if (path instanceof PathImpl) {
            NodeImpl node = ((PathImpl) path).getLeafNode();
            if (node.getKind() == ElementKind.PROPERTY) {
                StringBuilder builder = new StringBuilder();
                builder.append(node);
                while(null != node.getParent() && node.getParent().getKind() == ElementKind.PROPERTY) {
                    node = node.getParent();
                    builder.insert(0, ".").insert(0, node);
                }
                return builder.toString();
            }
        }
        return null;
    }

    private int determineCode(ConstraintViolation<?> violation) {
        if (codes.containsKey(violation.getConstraintDescriptor().getAnnotation().annotationType())) {
            return codes.get(violation.getConstraintDescriptor().getAnnotation().annotationType());
        } else if (null != violation.getConstraintDescriptor().getAnnotation().annotationType().getAnnotation(ReferenceConstraint.class)) {
            return 1004;
        }
        logger.error("Unmapped constraint violation: {}", violation);
        return 1000;
    }
}
