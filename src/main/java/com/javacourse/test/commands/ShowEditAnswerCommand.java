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
            String taskId = request.getParameter("id");
            Answer answer = answerService.findById(taskId);
            setRequestAttributes(request, answer);
        } catch ( UnsuccessfulQueryException | NumberFormatException e) {
            webPage = new WebPage(WebPage.WebPageBase.ERROR_ACTION);
        }
        request.setAttribute("correct", request.getParameter("correct"));
        return webPage;
    }

    private void setRequestAttributes(HttpServletRequest request, Answer answer){
        request.setAttribute("answer", answer);
        request.setAttribute("testId", request.getParameter("testId"));
        request.setAttribute("taskId", request.getParameter("taskId"));
    }
}
