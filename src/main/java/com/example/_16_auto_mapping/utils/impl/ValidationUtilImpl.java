package com.example._16_auto_mapping.utils.impl;

import com.example._16_auto_mapping.utils.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
@Component
public class ValidationUtilImpl implements ValidatorUtil {

    private final Validator validator;
    @Autowired
    public ValidationUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
       return validator.validate(entity).isEmpty();
    }

    @Override
    public <E> Set<ConstraintViolation<E>> violations(E entity) {
      return   this.validator.validate(entity);
    }
}
