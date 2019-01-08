package com.javacourse.utils;

import com.javacourse.ApplicationResources;

import javax.validation.*;
import java.util.Locale;
import java.util.Set;

/**
 * Handles basic validator properties set up and returns validator
 * for model entities
 */
public class BeanValidatorConfig<T> {

    private String lang;
    private Set<ConstraintViolation<T>> violations;

    public BeanValidatorConfig(String lang) {
        this.lang = lang;
    }

    private static Validator getValidator(String errorMessageLang){
        if(errorMessageLang==null)
            errorMessageLang = ApplicationResources.getDefaultLang();
        Locale.setDefault(new Locale(errorMessageLang));
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        Validator validator = factory.getValidator();
        factory.close();
        return validator;
    }

    public boolean isValid(T model){
        violations = BeanValidatorConfig
                .getValidator(lang)
                .validate(model);

        return violations.isEmpty();
    }

    public String getErrorMessage(){
        return violations.iterator().next().getMessage();
    }
}
