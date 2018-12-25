package com.javacourse.utils;

import com.javacourse.ApplicationResources;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleConfig {
    public static ResourceBundle getResourceBundle(String lang){
        if(lang==null)
            lang = ApplicationResources.getDefaultLang();
        Locale locale= new Locale(lang);
        return ResourceBundle.getBundle(ApplicationResources.getErrorBundle(), locale);
    }
}
