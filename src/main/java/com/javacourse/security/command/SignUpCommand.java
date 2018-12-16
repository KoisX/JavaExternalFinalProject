package com.javacourse.security.command;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.PasswordManager;
import com.javacourse.shared.Command;
import com.javacourse.user.User;
import com.javacourse.user.UserDAO;
import com.javacourse.user.role.AdminUserRoleFactory;
import com.javacourse.user.role.Role;
import com.javacourse.user.role.RoleDAO;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements Command {

    private static final int PASSWORD_MIN_LENGTH = 3;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(SignUpCommand.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String userEmail = request.getParameter("login");
        String userPassword = request.getParameter("password");
        String userPasswordConfirm = request.getParameter("password-confirm");
        String userName = request.getParameter("name");
        String userSurname = request.getParameter("surname");

        if(isFieldEmpty(userEmail) ||
            isFieldEmpty(userName) ||
            isFieldEmpty(userPassword) ||
            isFieldEmpty(userPasswordConfirm) ||
            isFieldEmpty(userSurname)){

            //TODO: get it form props
            request.setAttribute("error", "Field(s) can't be empty");
            return "/signup.jsp";

        }


        if(!arePasswordsEqual(userPassword, userPasswordConfirm)){
            //TODO: get it form props
            request.setAttribute("error", "Password confirmation is unsuccessful");
            return "/signup.jsp";
        }

        if(!isPasswordLongEnough(userPassword)){
            //TODO: get it form props
            request.setAttribute("error", "Password must me at least 3 symbols long");
            return "/signup.jsp";
        }

        UserDAO userDAO = new UserDAO();
        boolean doesEmailExist = false;
        try {
            doesEmailExist = userDAO.doesEmailAlreadyExist(userEmail);
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return "/Error";
        }

        if(doesEmailExist){
            //TODO: get it form props
            request.setAttribute("error", "User with this email already exists");
            return "/signup.jsp";

        }

        if(insertUser(userName, userSurname, userEmail, userPassword, userDAO)){
            //!!!
            return "/index.jsp";
        }else {
            request.setAttribute("error", "Registration unsuccessful. Try again.");
            return "/login.jsp";
        }
    }

    private boolean insertUser(String userName, String userSurname, String userEmail, String userPassword, UserDAO userDAO) {
        User user = new User();
        user.setName(userName);
        user.setSurname(userSurname);
        user.setEmail(userEmail);
        user.setRole(Role.USER);
        user.setPassword(PasswordManager.hash(userPassword, userEmail));

        RoleDAO roleDAO = new RoleDAO();
        int roleId;

        try {
            roleId = roleDAO.getRoleIdByName(user.getRole().getName());
            user.getRole().setId(roleId);
            userDAO.create(user);
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    boolean arePasswordsEqual(String p1, String p2){
        return p1.equals(p2);
    }

    boolean isPasswordLongEnough(String password){
        return password.length() >= PASSWORD_MIN_LENGTH;
    }

    boolean isFieldEmpty(String s){
        if(s==null || s.equals(""))
            return true;
        else return false;
    }
}
