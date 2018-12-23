package com.javacourse.user;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.PasswordManager;
import com.javacourse.shared.WebPage;
import com.javacourse.user.role.Role;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Helper class for signing up new users.
 * It's main responsibility is to validate user input and
 * insert new user to DB
 */
public class UserCreationUtils {

    private static final int PASSWORD_MIN_LENGTH = 3;
    private static final String ERROR_REQUEST_MESSAGE = "error";
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(UserCreationUtils.class);
    }

    /**
     * Checks if input fields are valid.
     * If yes, inserts a new user.
     * Otherwise sets error messages
     * @param request
     * @return url to forward or redirect to
     */
    public static WebPage handleUserInsert(HttpServletRequest request){
        WebPage resultPage;

        if(!checkInputFields(request) || !validateInDb(request)){
            resultPage = WebPage.SIGN_UP_PAGE;
            return  resultPage;
        }

        if(insertUser(constructUser(request))){
            resultPage = WebPage.LOGIN_ACTION.setDoRedirect(true);
        }else {
            request.setAttribute(ERROR_REQUEST_MESSAGE, "Registration unsuccessful. Try again.");
            resultPage = WebPage.SIGN_UP_PAGE;
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
            request.setAttribute(ERROR_REQUEST_MESSAGE, "Field(s) can't be empty");
            return false;
        }

        if(!arePasswordsEqual(userPassword, userPasswordConfirm)){
            request.setAttribute(ERROR_REQUEST_MESSAGE, "Password confirmation is unsuccessful");
            return false;
        }

        if(!isPasswordLongEnough(userPassword)){
            request.setAttribute(ERROR_REQUEST_MESSAGE, "Password must me at least 3 symbols long");
            return false;
        }

        return true;
    }

    static boolean validateInDb(HttpServletRequest request){
        String userEmail = request.getParameter("login");
        boolean doesEmailExist = false;
        UserService userService = new UserService();
        try {
            doesEmailExist = userService.doesUserWithEmailExist(userEmail);
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

    static User constructUser(HttpServletRequest request){
        User user = new User();
        user.setEmail(request.getParameter("login"));
        user.setPassword(PasswordManager.hash(request.getParameter("password"), request.getParameter("login")));
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setRole(Role.USER);
        return user;
    }

    static boolean insertUser(User user) {
        UserService userService = new UserService();
        logger.debug("in insert");
        return userService.create(user);
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
