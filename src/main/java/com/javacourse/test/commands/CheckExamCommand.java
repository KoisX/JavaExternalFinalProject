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
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class CheckExamCommand implements Command {

    private List<Task> tasks;
    private int maxScore;
    private final static Logger logger;
    private final static String ID_PARAM = "id";
    private final static String TASKS_ATTRIBUTE = "tasks";
    private final static String RESULT_ATTRIBUTE = "result";
    private final static String MAXIMAL_SCORE_ATTRIBUTE = "maximalResult";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(CheckExamCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        String testId = request.getParameter(ID_PARAM);

        if(testId==null)
            return WebPage.ERROR_ACTION;

        if(!getTasksAndMaxScoreFromDb(testId)){
            return WebPage.ERROR_ACTION;
        }

        int score = calculateTestScore(request.getParameterMap());
        setRequestAttributes(request, score);


        //TODO:save result to stats db table

        //TODO: send email with result to user

        return WebPage.TEST_USER_RESULTS;
    }

    boolean getTasksAndMaxScoreFromDb(String testId){
        try{
            TaskService taskService = new TaskService();
            tasks = taskService.findTasksByTestId(testId);
            maxScore = taskService.getMaximalScoreByTestId(testId);
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    int calculateTestScore(Map<String, String[]> paramMap){
        int score = 0;
        for(Task task : tasks){
            String[] userAnswersFromRequest = paramMap.get(getRequestParamInputValue(task));
            List<String> userAnswers = new ArrayList<>(Arrays.asList(userAnswersFromRequest));
            List<String> correctAnswers = task.getCorrectAnswers()
                    .stream()
                    .map(Answer::getValue)
                    .collect(Collectors.toList());
            if(userAnswers.containsAll(correctAnswers)){
                score += task.getPrice();
            }
        }
        return score;
    }

    /*Gets param from a request, with the name, which corresponds to
    * test page naming conventions: field_task-id*/
    private String getRequestParamInputValue(Task task){
        return "field_"+String.valueOf(task.getId());
    }

    private void setRequestAttributes(HttpServletRequest request, int score){
        request.setAttribute(RESULT_ATTRIBUTE, score);
        request.setAttribute(MAXIMAL_SCORE_ATTRIBUTE, maxScore);
        request.setAttribute(TASKS_ATTRIBUTE, tasks);
    }
}
