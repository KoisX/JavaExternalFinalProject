package com.javacourse.utils;

import com.javacourse.ApplicationResources;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Utility class which encapsulates the logic of getting ResourceBundle entity
 * for the corresponding language
 */
public class ResourceBundleConfig {
    public static ResourceBundle getErrorResourceBundle(String lang){
        if(lang==null)
            lang = ApplicationResources.getDefaultLang();
        Locale locale= new Locale(lang);
        return ResourceBundle.getBundle(ApplicationResources.getErrorBundle(), locale);
    }

    public static ResourceBundle getResourceBundle(String lang, String baseName){
        if(lang==null)
            lang = ApplicationResources.getDefaultLang();
        Locale locale= new Locale(lang);
        return ResourceBundle.getBundle(baseName, locale);
    }
}
