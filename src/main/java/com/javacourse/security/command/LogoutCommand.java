package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.WebKeys;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        session.removeAttribute("password");
        session.removeAttribute("login");
        session.removeAttribute("role");
        session.invalidate();
        return WebPage.INDEX_ACTION.setDoRedirect(true);
    }
}
