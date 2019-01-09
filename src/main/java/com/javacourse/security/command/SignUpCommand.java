package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.security.PasswordManager;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.user.User;
import com.javacourse.user.UserCreationUtils;
import com.javacourse.user.role.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class SignUpCommand implements Command {

    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String NAME_PARAM = "name";
    private static final String SURNAME_PARAM = "surname";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        return new UserCreationUtils(request).handleUserInsert(constructUser(request));
    }

    User constructUser(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter(LOGIN_PARAM));
        user.setPassword((request.getParameter(PASSWORD_PARAM)));
        user.setName(request.getParameter(NAME_PARAM));
        user.setSurname(request.getParameter(SURNAME_PARAM));
        user.setRole(Role.USER);
        return user;
    }




}
