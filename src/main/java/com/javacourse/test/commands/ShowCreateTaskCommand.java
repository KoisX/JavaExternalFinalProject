package com.javacourse.test.commands;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowCreateTaskCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        String test_id = request.getParameter("id");
        if(test_id==null)
            return new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        request.setAttribute("id", request.getParameter("testId"));
        return new WebPage(WebPage.WebPageBase.TASK_ADMIN_CREATE_PAGE);
    }
}
