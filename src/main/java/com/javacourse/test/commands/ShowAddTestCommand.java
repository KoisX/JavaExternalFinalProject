package com.javacourse.test.commands;

import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.javacourse.shared.WebPage.WebPageBase;

public class ShowAddTestCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        String test_id = request.getParameter("id");
        if(test_id==null)
            return new WebPage(WebPageBase.ERROR_ACTION);
        request.setAttribute("id", request.getParameter("id"));
        return new WebPage(WebPageBase.TEST_ADMIN_ADD_PAGE);
    }
}
