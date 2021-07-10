package it.univaq.disim.mwt.letsjam.presentation.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {

    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        try {
            return true;
        } catch (BusinessException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }

}
