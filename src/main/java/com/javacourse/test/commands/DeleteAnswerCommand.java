package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.answer.AnswerService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteAnswerCommand implements Command {

    private final static Logger logger;
    //logger configuration
    static {
        logger = LogConfigurator.getLogger(DeleteAnswerCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        AnswerService answerService = new AnswerService();
        return getPageDependingOnWhetherDeleteIsSuccessful(request, answerService);
    }

    WebPage getPageDependingOnWhetherDeleteIsSuccessful(HttpServletRequest request, AnswerService answerService){
        String id = request.getParameter("id");
        String testId = request.getParameter("testId");
        try {
            if(answerService.delete(id))
                return new WebPage(WebPage.WebPageBase.TEST_ADMIN_DETAILS_ACTION)
                        .setDispatchType(WebPage.DispatchType.REDIRECT)
                        .setQueryString("?id="+testId);
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
        }
        //in case delete is unsuccessful
        return new WebPage(WebPage.WebPageBase.ERROR_ACTION);
    }
}
