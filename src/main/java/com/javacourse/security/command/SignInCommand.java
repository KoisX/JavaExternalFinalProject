package com.javacourse.security.command;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.PasswordManager;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.user.UserService;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SignInCommand implements Command {

    String userEmail;
    String userPassword;
    private final static Logger logger;
    private final static String ERROR_MSG = "error";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(SignInCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request) {
        userEmail = request.getParameter("login");
        userPassword = request.getParameter("password");
        WebPage webPage = getPageBasedOnWhetherUserExists(request);
        return webPage;
    }

    private WebPage getPageBasedOnWhetherUserExists(HttpServletRequest request){
        WebPage webPage = WebPage.LOGIN_PAGE;
        try {
            UserService userService = new UserService();
            String hash = PasswordManager.hash(userPassword, userEmail);
            if(userService.doesUserExist(userEmail, hash)){
                Role role = userService.getUserRoleByEmail(userEmail);
                setUserAttributes(role, hash, request);
                webPage = WebPage.INDEX_ACTION.setDoRedirect(true);
            }
        }catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            webPage = WebPage.ERROR_ACTION;
        }
        //if logging in is unsuccessful
        request.setAttribute(ERROR_MSG, "Incorrect login or password");
        return webPage;
    }

    private void setUserAttributes(Role role, String hash, HttpServletRequest request) throws UnsuccessfulQueryException, SQLException {
        HttpSession session = request.getSession();
        session.setAttribute("login", userEmail);
        session.setAttribute("password", hash);
        session.setAttribute("role", role);
    }

}
