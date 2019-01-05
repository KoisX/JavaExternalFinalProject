package com.javacourse.test.commands;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAddAnswerCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String test_id = request.getParameter("testId");
        if(id==null || test_id==null)
            return new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        request.setAttribute("id", id);
        request.setAttribute("testId", test_id);
        return new WebPage(WebPage.WebPageBase.ANSWER_ADMIN_ADD_PAGE);
    }
}
