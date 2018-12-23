package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.security.PasswordManager;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.user.User;
import com.javacourse.user.UserCreationUtils;
import com.javacourse.user.role.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class SignUpCommand implements Command {

    private static final String LANG_PARAM = "lang";
    private static final String ERROR_BUNDLE = "error_message";

    @Override
    public WebPage execute(HttpServletRequest request) {
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        if(lang==null) lang = "en";
        Locale locale2= new Locale(lang);
        Validator validator;
        Locale.setDefault(locale2);
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
        factory.close();



        User user = constructUser(request);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(!violations.isEmpty()){
            Locale locale= new Locale(request.getParameter(LANG_PARAM)!=null?request.getParameter(LANG_PARAM) : ApplicationResources.getDefaultLang());
            ResourceBundle resourceBundle = ResourceBundle.getBundle(ERROR_BUNDLE, locale);

            request.setAttribute("error", violations.iterator().next().getMessage());
            return WebPage.SIGN_UP_PAGE;
        }


        return WebPage.INDEX_ACTION;
    }

    private User constructUser(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter("login"));
        user.setPassword(PasswordManager.hash(request.getParameter("password"), request.getParameter("login")));
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setRole(Role.USER);
        return user;
    }

}
