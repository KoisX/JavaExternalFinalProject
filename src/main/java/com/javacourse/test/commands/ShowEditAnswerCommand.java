package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.answer.Answer;
import com.javacourse.test.answer.AnswerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ShowEditAnswerCommand implements Command {
    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        WebPage webPage = new WebPage(WebPage.WebPageBase.ANSWER_ADMIN_EDIT_PAGE);
        AnswerService answerService = new AnswerService();
        try {
            int taskId = Integer.parseInt(request.getParameter("id"));
            Answer answer = answerService.findById(taskId);
            request.setAttribute("answer", answer);
        } catch (SQLException | UnsuccessfulQueryException | NumberFormatException e) {
            webPage = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        }
        request.setAttribute("correct", request.getParameter("correct"));
        return webPage;
    }
}
