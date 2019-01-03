package com.javacourse.security.command;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.javacourse.shared.WebPage.WebPageBase;

public class ShowSignInCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        return new WebPage(WebPageBase.LOGIN_PAGE);
    }
}
