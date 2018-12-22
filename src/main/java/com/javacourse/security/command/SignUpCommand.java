package com.javacourse.security.command;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.user.UserCreationUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements Command {

    @Override
    public WebPage execute(HttpServletRequest request) {
        return UserCreationUtils.handleUserInsert(request);
    }

}
