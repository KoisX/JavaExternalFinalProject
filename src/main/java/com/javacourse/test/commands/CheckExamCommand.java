package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.answer.Answer;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import com.javacourse.test.topic.commands.ShowTopicsCommand;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CheckExamCommand implements Command {

    private final static Logger logger;
    private final static String ID_PARAM = "id";
    private final static String TASKS_ATTRIBUTE = "tasks";
    private final static String TEST_ID_ATTRIBUTE = "testId";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(CheckExamCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request) {
        WebPage webPage = WebPage.TEST_USER_RESULTS;
        //TODO: foresee null and not int
        TaskService taskService = new TaskService();
        String testId = request.getParameter(ID_PARAM);
        List<Task> tasks;
        int maxScore = 0;
        try{
             tasks = taskService.findTasksByTestId(testId);
             maxScore = taskService.getMaximalScoreByTestId(testId);
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return WebPage.ERROR_ACTION;
        }

        int score = 0;
        for(Task task : tasks){
            String[] userAnswersFromRequest = request.getParameterValues("field_"+String.valueOf(task.getId()));
            List<String> userAnswers = new ArrayList<>(Arrays.asList(userAnswersFromRequest));
            List<String> correctAnswers = task.getCorrectAnswers()
                                    .stream()
                                    .map(Answer::getValue)
                                    .collect(Collectors.toList());
            if(userAnswers.containsAll(correctAnswers)){
                score += task.getPrice();
            }
        }

        request.setAttribute("result", score);
        request.setAttribute("maximalResult", maxScore);


        //setting all tasks again
        request.setAttribute(TASKS_ATTRIBUTE, tasks);


        return WebPage.TEST_USER_RESULTS;
    }
}
