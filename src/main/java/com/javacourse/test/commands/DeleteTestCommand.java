package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.TestService;
import com.javacourse.test.topic.commands.DeleteTopicCommand;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteTestCommand implements Command {

    private final static Logger logger;
    //logger configuration
    static {
        logger = LogConfigurator.getLogger(DeleteTestCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        return getPageDependingOnWhetherDeleteIsSuccessful(request);
    }

    private WebPage getPageDependingOnWhetherDeleteIsSuccessful(HttpServletRequest request) {
        TestService testService = new TestService();
        String id = request.getParameter("id");
        try {
            if(testService.delete(id))
                return WebPage.TOPICS_REDIRECT_ACTION;
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
        }
        //in case delete is unsuccessful
        return WebPage.ERROR_FORWARD_ACTION;
    }


}
