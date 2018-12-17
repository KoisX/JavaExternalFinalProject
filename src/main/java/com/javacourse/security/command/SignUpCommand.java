package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.WebKeys;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.PasswordManager;
import com.javacourse.shared.Command;
import com.javacourse.user.User;
import com.javacourse.user.UserCreationUtils;
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return UserCreationUtils.handleUserInsert(request);
    }

}
