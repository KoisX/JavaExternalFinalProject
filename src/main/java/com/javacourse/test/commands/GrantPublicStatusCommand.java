package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import com.javacourse.utils.JsonManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class GrantPublicStatusCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        TestService testService = new TestService();
        Test test = new Test();
        String idParam = request.getParameter("id");
        test.setId(Long.parseLong(idParam));
        JsonManager json = new JsonManager(response);
        try {
            if(testService.makeTestPublic(test)){
                json.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+request.getParameter("id")));
            }else {
                json.put("error", true);
            }
        } catch (UnsuccessfulQueryException | SQLException e) {
            json.put("error", true);
        }
        json.respond();
        return WebPage.STAND_STILL_PAGE;
    }
}
