package com.javacourse.security;

import com.javacourse.security.command.SignInCommand;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationCommandFactory extends CommandFactory {

    /**
     * @param request HttpServletRequest object of the request which is being served
     */
    public AuthorizationCommandFactory(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Command defineCommand() {
        return new SignInCommand();
    }
}
