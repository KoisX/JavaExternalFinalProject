package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSignInCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        return WebPage.LOGIN_PAGE;
    }
}
