package com.javacourse.security.command;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    private static final String PASSWORD_ATTRIBUTE = "password";
    private static final String LOGIN_ATTRIBUTE = "login";
    private static final String ROLE_ATTRIBUTE = "role";
    private static final String LANG_ATTRIBUTE = "lang";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        finishRequestSession(request);
        return WebPage.INDEX_ACTION.setDoRedirect(true);
    }

    private void finishRequestSession(HttpServletRequest request){
        final HttpSession session = request.getSession();
        session.removeAttribute(PASSWORD_ATTRIBUTE);
        session.removeAttribute(LOGIN_ATTRIBUTE);
        session.removeAttribute(ROLE_ATTRIBUTE);
        session.removeAttribute(LANG_ATTRIBUTE);
        session.invalidate();
    }
}
