package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.WebKeys;
import com.javacourse.shared.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        final HttpSession session = request.getSession();
        request.setAttribute(WebKeys.getShouldRedirect(), "true");
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        session.invalidate();
        return ApplicationResources.getIndexAction();
    }
}
