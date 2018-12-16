package com.javacourse.security.command;

import com.javacourse.shared.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();

        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        session.invalidate();
        return "/index.jsp";
    }
}
