package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.WebKeys;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.PasswordManager;
import com.javacourse.shared.Command;
import com.javacourse.user.UserService;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SignInCommand implements Command {
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(SignInCommand.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserService();
        return tryLogInByUserCredentials(request, userService);
    }

    String tryLogInByUserCredentials(HttpServletRequest request, UserService userService){
        String userEmail = request.getParameter("login");
        String userPassword = request.getParameter("password");
        try {
            String hash = PasswordManager.hash(userPassword, userEmail);
            if(userService.doesUserExist(userEmail, hash)){
                setUserAttributes(userService, hash, request);
                return  ApplicationResources.getIndexAction();
            }
        }catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return ApplicationResources.getErrorAction();
        }
        //if logging in is unsuccessful
        request.setAttribute(WebKeys.getErrorRequestMessage(), "Incorrect login or password");
        return ApplicationResources.getLoginPage();
    }

    void setUserAttributes(UserService userService, String hash, HttpServletRequest request) throws UnsuccessfulQueryException, SQLException {
        String userEmail = request.getParameter("login");
        String userPassword = request.getParameter("password");
        HttpSession session = request.getSession();

        Role role = userService.getUserRoleByEmail(userEmail);
        session.setAttribute("login", userEmail);
        session.setAttribute("password", userPassword);
        session.setAttribute("role", role);
        request.setAttribute(WebKeys.getShouldRedirect(), "true");
    }

}
