package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.WebKeys;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.PasswordManager;
import com.javacourse.shared.Command;
import com.javacourse.user.UserDAO;
import com.javacourse.user.role.AdminUserRoleFactory;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInCommand implements Command {
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(SignInCommand.class);
    }

    @Override
    public String execute(HttpServletRequest request , HttpServletResponse response) {
        UserDAO userDAO = new UserDAO();
        return tryLogInByUserCredentials(request, userDAO);
    }

    String tryLogInByUserCredentials(HttpServletRequest request,UserDAO userDAO){
        String userEmail = request.getParameter("login");
        String userPassword = request.getParameter("password");
        try {
            String hash = PasswordManager.hash(userPassword, userEmail);
            if(userDAO.doesUserExist(userEmail, hash)){
                setUserAttributes(userDAO, hash, request);
                return ApplicationResources.getIndexAction();
            }
        }catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return ApplicationResources.getErrorPage();
        }
        //if logging in is unsuccessful
        request.setAttribute(WebKeys.getErrorRequestMessage(), "Incorrect login or password");
        return ApplicationResources.getLoginPage();
    }

    void setUserAttributes(UserDAO userDAO, String hash, HttpServletRequest request) throws UnsuccessfulQueryException {
        String userEmail = request.getParameter("login");
        String userPassword = request.getParameter("password");
        HttpSession session = request.getSession();

        Role role = userDAO.getRoleByEmailAndPassword(userEmail, hash);
        session.setAttribute("login", userEmail);
        session.setAttribute("password", userPassword);
        session.setAttribute("role", role.getName());
        request.setAttribute(WebKeys.getShouldRedirect(), "true");
    }

}
