package com.javacourse.user;

import com.javacourse.ApplicationResources;
import com.javacourse.WebKeys;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.PasswordManager;
import com.javacourse.security.command.SignUpCommand;
import com.javacourse.user.role.Role;
import com.javacourse.user.role.RoleDAO;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Helper class for sihning up new users.
 * It's main responsibility is to validate user input and
 * insert new user to DB
 */
public class UserCreationUtils {

    private static final int PASSWORD_MIN_LENGTH = 3;
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(SignUpCommand.class);
    }

    /**
     * Checks if input fields are valid.
     * If yes, inserts a new user.
     * Otherwise sets error messages
     * @param request
     * @return url to forward or redirect to
     */
    public static String handleUserInsert(HttpServletRequest request){
        UserDAO userDAO = new UserDAO();
        String resultPage;

        if(!checkInputFields(request) || !validateInDb(request, userDAO)){
            resultPage = ApplicationResources.getSignUpPage();
        }

        if(insertUser(constructUser(request), userDAO)){
            resultPage = ApplicationResources.getLoginAction();
            /*If new account was created successfully, we want to
             * redirect user to login page, not forward*/
            request.setAttribute(WebKeys.getShouldRedirect(), "true");
        }else {
            request.setAttribute("error", "Registration unsuccessful. Try again.");
            resultPage = ApplicationResources.getSignUpPage();
        }

        return resultPage;
    }

    static boolean checkInputFields(HttpServletRequest request){
        String userEmail = request.getParameter("login");
        String userPassword = request.getParameter("password");
        String userPasswordConfirm = request.getParameter("password-confirm");
        String userName = request.getParameter("name");
        String userSurname = request.getParameter("surname");

        if(containsEmptyFields(userName, userSurname, userEmail, userPassword, userPasswordConfirm)){
            request.setAttribute("error", "Field(s) can't be empty");
            return false;
        }

        if(!arePasswordsEqual(userPassword, userPasswordConfirm)){
            request.setAttribute("error", "Password confirmation is unsuccessful");
            return false;
        }

        if(!isPasswordLongEnough(userPassword)){
            request.setAttribute("error", "Password must me at least 3 symbols long");
            return false;
        }

        return true;
    }

    static boolean validateInDb(HttpServletRequest request, UserDAO userDAO){
        String userEmail = request.getParameter("login");
        boolean doesEmailExist = false;
        try {
            doesEmailExist = userDAO.doesEmailAlreadyExist(userEmail);
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            request.setAttribute("error", "Unsuccessful signing up. Try again.");
            return false;
        }

        if(doesEmailExist){
            request.setAttribute("error", "User with this email already exists");
            return false;
        }
        return true;
    }

    static User constructUser(HttpServletRequest request){
        User user = new User();
        user.setEmail(request.getParameter("login"));
        user.setPassword(PasswordManager.hash(request.getParameter("password"), request.getParameter("login")));
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setRole(Role.USER);
        return user;
    }

    static boolean insertUser(User user, UserDAO userDAO) {
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

    static boolean containsEmptyFields(String name, String surname, String email, String password, String passwordValidation){
        if(isFieldEmpty(name) ||
                isFieldEmpty(surname) ||
                isFieldEmpty(email) ||
                isFieldEmpty(password) ||
                isFieldEmpty(passwordValidation)){
            return true;
        }
        return false;
    }

    static boolean arePasswordsEqual(String p1, String p2){
        return p1.equals(p2);
    }

    static boolean isPasswordLongEnough(String password){
        return password.length() >= PASSWORD_MIN_LENGTH;
    }

    static boolean isFieldEmpty(String s){
        if(s==null || s.equals(""))
            return true;
        else return false;
    }
}
