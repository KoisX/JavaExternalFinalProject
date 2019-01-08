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
        Test test = constructTest(request);
        JsonManager json = grantPublicAccessIfPossible(test,response);
        json.respond();
        return WebPage.STAND_STILL_PAGE;
    }

    private Test constructTest(HttpServletRequest request){
        Test test = new Test();
        String idParam = request.getParameter("id");
        test.setId(Long.parseLong(idParam));
        return test;
    }

    private JsonManager grantPublicAccessIfPossible(Test test, HttpServletResponse response){
        TestService testService = new TestService();
        JsonManager json = new JsonManager(response);
        try {
            if(testService.makeTestPublic(test)){
                json.put("url", new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setQueryString("?id="+test.getId()));
            }else {
                json.put("error", true);
            }
        } catch (UnsuccessfulQueryException e) {
            json.put("error", true);
        }
        return json;
    }
}
