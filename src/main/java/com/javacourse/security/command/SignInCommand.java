package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.user.UserDAO;
import com.javacourse.user.role.AdminUserRoleFactory;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignInCommand implements Command {
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(SignInCommand.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String userEmail = request.getParameter("login");
        String userPassword = request.getParameter("password");
        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAO(new AdminUserRoleFactory());
        boolean doesExist = false;
        try {
            doesExist = userDAO.doesUserExist(userEmail, userPassword);
        }catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return "/Error";
        }
        if(doesExist){
            session.setAttribute("login", userEmail);
            session.setAttribute("password", userPassword);
            return "/index.jsp";
        }

        return "/login.jsp";
    }
}
