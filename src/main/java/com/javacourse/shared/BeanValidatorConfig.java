package com.javacourse.shared;

import com.javacourse.ApplicationResources;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;

/**
 * Handles basic validator properties set up and returns validator
 * for model entities
 */
public class BeanValidatorConfig {
    public static Validator getValidator(String errorMessageLang){
        if(errorMessageLang==null)
            errorMessageLang = ApplicationResources.getDefaultLang();
        Locale.setDefault(new Locale(errorMessageLang));
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        Validator validator = factory.getValidator();
        factory.close();
        return validator;
    }
}
