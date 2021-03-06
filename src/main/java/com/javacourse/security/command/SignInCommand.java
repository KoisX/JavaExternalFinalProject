package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.PasswordManager;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.user.UserService;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import static com.javacourse.shared.WebPage.WebPageBase;

public class SignInCommand implements Command {

    private final static Logger logger;
    private final static String ERROR_MSG = "error";
    private static final String LANG_PARAM = "lang";
    private static final String ERROR_BUNDLE = "error_message";
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String ROLE_PARAM = "role";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(SignInCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        String  userEmail = request.getParameter(LOGIN_PARAM);
        String userPassword = request.getParameter(PASSWORD_PARAM);
        UserService userService = new UserService();
        return getPageBasedOnWhetherUserExists(request, userPassword, userEmail, userService);
    }

    WebPage getPageBasedOnWhetherUserExists(HttpServletRequest request, String userPassword, String userEmail, UserService userService){
        WebPage webPage = new WebPage(WebPageBase.LOGIN_PAGE);
        try {
            String hash = PasswordManager.hash(userPassword, userEmail);
            if(userService.doesUserExist(userEmail, hash)){
                Role role = userService.getUserRoleByEmail(userEmail);
                setUserAttributes(role, hash, request, userEmail);
                webPage = new WebPage(WebPageBase.INDEX_ACTION).setDispatchType(WebPage.DispatchType.REDIRECT);
            }
        }catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            webPage = new WebPage(WebPageBase.ERROR_ACTION);
        }
        //if logging in is unsuccessful
        setErrorMessage(request);
        return webPage;
    }

    private void setUserAttributes(Role role, String hash, HttpServletRequest request, String userEmail) {
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_PARAM, userEmail);
        session.setAttribute(PASSWORD_PARAM, hash);
        session.setAttribute(ROLE_PARAM, role);
    }

    private void setErrorMessage(HttpServletRequest request){
        Locale locale= new Locale(request.getParameter(LANG_PARAM)!=null?request.getParameter(LANG_PARAM) : ApplicationResources.getDefaultLang());
        ResourceBundle resourceBundle = ResourceBundle.getBundle(ERROR_BUNDLE, locale);
        request.setAttribute(ERROR_MSG, resourceBundle.getString("msg.incorrectCredentials"));
    }

}
