package com.javacourse.security.command;

import com.javacourse.shared.Command;

import javax.servlet.http.HttpServletRequest;

public class SignInCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/login.jsp";
    }
}
