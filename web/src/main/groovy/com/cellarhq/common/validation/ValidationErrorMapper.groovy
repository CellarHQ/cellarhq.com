package com.cellarhq.common.validation

import javax.validation.ConstraintViolation


class ValidationErrorMapper {
    public List<String> buildMessages(Set<ConstraintViolation> ... violations) {
        violations.collectMany { Set<ConstraintViolation> constraintViolations ->
            constraintViolations.collect {
                ConstraintViolation singleViolation -> "${singleViolation.propertyPath} ${singleViolation.message}"
            }
        }
    }
}
