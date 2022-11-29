package com.example._16_auto_mapping.utils;


import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtil {

     <E> boolean isValid (E entity);

     <E>Set<ConstraintViolation<E>> violations(E entity);
}
