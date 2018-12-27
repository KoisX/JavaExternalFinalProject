package com.javacourse.test.commands;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;

public class CheckExamCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request) {
        return WebPage.TEST_USER_RESULTS;
    }
}
