package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.TestService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static com.javacourse.shared.WebPage.WebPageBase;

public class DeleteTestCommand implements Command {

    private final static Logger logger;
    //logger configuration
    static {
        logger = LogConfigurator.getLogger(DeleteTestCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        TestService testService = new TestService();
        return getPageDependingOnWhetherDeleteIsSuccessful(request, testService);
    }

    WebPage getPageDependingOnWhetherDeleteIsSuccessful(HttpServletRequest request, TestService testService) {
        String id = request.getParameter("id");
        try {
            if(testService.delete(id))
                return new WebPage(WebPageBase.TESTS_ACTION)
                        .setDispatchType(WebPage.DispatchType.REDIRECT)
                        .setQueryString("?id="+request.getParameter("topicId"));
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
        }
        //in case delete is unsuccessful
        return new WebPage(WebPageBase.ERROR_ACTION);
    }


}
