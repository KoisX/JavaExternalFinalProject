package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.Test;
import com.javacourse.test.TestService;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class GrantPublicStatusCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        TestService testService = new TestService();
        Test test = new Test();
        String idParam = request.getParameter("id");
        test.setId(Long.parseLong(idParam));
        JSONObject jsonResponse = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            if(testService.makeTestPublic(test)){
                jsonResponse.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+request.getParameter("id")));
            }else {
                jsonResponse.put("error", true);
            }
        } catch (UnsuccessfulQueryException | SQLException e) {
            jsonResponse.put("error", true);
        }

        try {
            response.getWriter().write(jsonResponse.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException("Could not get response writer");
        }

        return WebPage.STAND_STILL_PAGE;
    }
}
