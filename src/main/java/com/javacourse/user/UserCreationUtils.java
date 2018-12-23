package com.javacourse.user;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.PasswordManager;
import com.javacourse.shared.WebPage;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Helper class for signing up new users.
 * It's main responsibility is to validate user input and
 * insert new user to DB
 */
public class UserCreationUtils {

    private static Locale locale;
    private static ResourceBundle resourceBundle;
    private static Validator validator;
    private static final String ERROR_REQUEST_MESSAGE = "error";
    private static final String LANG_PARAM = "lang";
    private static final String ERROR_BUNDLE = "error_message";
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(UserCreationUtils.class);
    }

    /**
     * Checks if input fields are valid.
     * If yes, inserts a new user.
     * Otherwise sets error messages
     * @param user
     * @return url to forward or redirect to
     */
    public static WebPage handleUserInsert(User user, HttpServletRequest request){

        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        initValidator(lang);
        setResourceBundle(lang);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(!violations.isEmpty()){
            request.setAttribute(ERROR_REQUEST_MESSAGE, violations.iterator().next().getMessage());
            return WebPage.SIGN_UP_PAGE;
        }

        WebPage resultPage;

        if(!validateInDb(user, request)){
            resultPage = WebPage.SIGN_UP_PAGE;
            return  resultPage;
        }

        if(insertUser(user)){
            resultPage = WebPage.LOGIN_ACTION.setDoRedirect(true);
        }else {
            request.setAttribute(ERROR_REQUEST_MESSAGE, "Registration unsuccessful. Try again.");
            resultPage = WebPage.SIGN_UP_PAGE;
        }

        return resultPage;
    }

    static boolean validateInDb(User user, HttpServletRequest request){
        boolean doesEmailExist = false;
        UserService userService = new UserService();
        try {
            doesEmailExist = userService.doesUserWithEmailExist(user.getEmail());
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            request.setAttribute(ERROR_REQUEST_MESSAGE, "Unsuccessful signing up. Try again.");
            return false;
        }

        if(doesEmailExist){
            request.setAttribute(ERROR_REQUEST_MESSAGE, "User with this email already exists");
            return false;
        }
        return true;
    }

    static boolean insertUser(User user) {
        UserService userService = new UserService();
        user.setPassword(PasswordManager.hash(user.getPassword(), user.getEmail()));
        return userService.create(user);
    }

    static private void initValidator(String lang){
        if(lang==null)
            lang = ApplicationResources.getDefaultLang();
        Locale.setDefault(new Locale(lang));
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
        factory.close();
    }

    private static void setResourceBundle(String lang){
        if(lang==null)
            lang = ApplicationResources.getDefaultLang();
        locale= new Locale(lang);
        resourceBundle = ResourceBundle.getBundle(ERROR_BUNDLE, locale);
    }

}
