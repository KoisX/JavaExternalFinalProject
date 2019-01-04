package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class GrantPrivateStatusCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        TestService testService = new TestService();
        Test test = new Test();
        String idParam = request.getParameter("id");
        test.setId(Long.parseLong(idParam));
        try {
            testService.makeTestPrivate(test);
        } catch (UnsuccessfulQueryException | SQLException e) {
            return new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        }
        return new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                .setQueryString("?id="+request.getParameter("id"))
                .setDispatchType(WebPage.DispatchType.REDIRECT);
    }
}
