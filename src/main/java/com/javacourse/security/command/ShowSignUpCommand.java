package com.javacourse.security.command;

import com.javacourse.ApplicationResources;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSignUpCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request) {
        return WebPage.SIGN_UP_PAGE;
    }
}
