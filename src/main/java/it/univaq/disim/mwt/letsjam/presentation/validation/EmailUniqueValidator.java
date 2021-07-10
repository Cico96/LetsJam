package it.univaq.disim.mwt.letsjam.presentation.validation;

import it.univaq.disim.mwt.letsjam.exceptions.BusinessException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        try {
            return true;
        } catch (BusinessException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
