package com.javacourse.home.commands;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;

public class ShowAboutCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request) {
        return WebPage.ABOUT_PAGE;
    }
}
