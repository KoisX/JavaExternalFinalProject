package com.javacourse.test.topic.commands;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;

public class ShowCreatePageCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request) {
        return WebPage.TOPICS_ADMIN_CREATE;
    }
}
