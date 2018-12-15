package com.javacourse.security.command;

import com.javacourse.shared.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowSignUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/signup.jsp";
    }
}
