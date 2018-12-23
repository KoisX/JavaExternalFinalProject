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

    private Locale locale;
    private HttpServletRequest request;
    private ResourceBundle resourceBundle;
    private Validator validator;
    private static final String ERROR_REQUEST_MESSAGE = "error";
    private static final String LANG_PARAM = "lang";
    private static final String ERROR_BUNDLE = "error_message";
    private static final String PASS_CONFIRM_PARAM = "password-confirm";
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(UserCreationUtils.class);
    }

    public UserCreationUtils(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * Checks if input fields are valid.
     * If yes, inserts a new user.
     * Otherwise sets error messages
     * @param user
     * @return url to forward or redirect to
     */
    public WebPage handleUserInsert(User user){

        String lang = (String)request.getSession().getAttribute(LANG_PARAM);
        initValidator(lang);
        setResourceBundle(lang);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(!violations.isEmpty()){
            request.setAttribute(ERROR_REQUEST_MESSAGE, violations.iterator().next().getMessage());
            return WebPage.SIGN_UP_PAGE;
        }

        if(!arePasswordsEqual(user.getPassword(), request.getParameter(PASS_CONFIRM_PARAM))){
            request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.passwordsNotEqual"));
            return WebPage.SIGN_UP_PAGE;
        }

        if(!validateInDb(user, request)){
            return WebPage.SIGN_UP_PAGE;
        }

        WebPage resultPage;
        if(insertUser(user)){
            resultPage = WebPage.LOGIN_ACTION.setDoRedirect(true);
        }else {
            request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.registrationUnsuccessful"));
            resultPage = WebPage.SIGN_UP_PAGE;
        }

        return resultPage;
    }

    boolean validateInDb(User user, HttpServletRequest request){
        boolean doesEmailExist = false;
        UserService userService = new UserService();
        try {
            doesEmailExist = userService.doesUserWithEmailExist(user.getEmail());
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.unsuccessfullSigningUp"));
            return false;
        }

        if(doesEmailExist){
            request.setAttribute(ERROR_REQUEST_MESSAGE, resourceBundle.getString("msg.userAlreadyExists"));
            return false;
        }
        return true;
    }

    boolean insertUser(User user) {
        UserService userService = new UserService();
        user.setPassword(PasswordManager.hash(user.getPassword(), user.getEmail()));
        return userService.create(user);
    }

    boolean arePasswordsEqual(String p1, String p2){
        return p1.equals(p2);
    }

    private void initValidator(String lang){
        if(lang==null)
            lang = ApplicationResources.getDefaultLang();
        Locale.setDefault(new Locale(lang));
        Configuration<?> config = Validation.byDefaultProvider().configure();
        ValidatorFactory factory = config.buildValidatorFactory();
        validator = factory.getValidator();
        factory.close();
    }

    private void setResourceBundle(String lang){
        if(lang==null)
            lang = ApplicationResources.getDefaultLang();
        locale= new Locale(lang);
        resourceBundle = ResourceBundle.getBundle(ERROR_BUNDLE, locale);
    }

}
