package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.user.UserDAO;
import com.javacourse.user.role.AdminUserRoleFactory;
import com.javacourse.user.role.Role;
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
        Role role = Role.USER;
        try {
            doesExist = userDAO.doesUserExist(userEmail, userPassword);
            if(doesExist){
                role = userDAO.getRoleByEmailAndPassword(userEmail, userPassword);
            }
        }catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return "/Error";
        }
        if(doesExist){
            session.setAttribute("login", userEmail);
            session.setAttribute("password", userPassword);
            session.setAttribute("role", role.getName());
            return "/index.jsp";
        }

        request.setAttribute("error", "Incorrect login or password");
        return "/login.jsp";
    }
}
