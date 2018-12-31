package com.javacourse.test.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.test.answer.Answer;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class CheckExamCommand implements Command {

    private List<Task> tasks;
    private int maxScore;
    private int score;
    /*Tasks, in which user made mistakes*/
    private List<Long> wrongTasksIndexes;
    private final static Logger logger;
    private final static String ID_PARAM = "id";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(CheckExamCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        String testId = request.getParameter(ID_PARAM);

        if(testId==null)
            return WebPage.ERROR_FORWARD_ACTION;

        if(!getTasksAndMaxScoreFromDb(testId)){
            return WebPage.ERROR_FORWARD_ACTION;
        }
        score = reviseTest(request.getParameterMap());

        showExamResult(response);


        return WebPage.STAND_STILL_PAGE;



        //TODO:save result to stats db table

        //TODO: send email with result to user
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

    int reviseTest(Map<String, String[]> paramMap){
        List<Long> wrongTasks = new ArrayList<>();
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
            }else {
                wrongTasks.add(task.getId());
            }
        }
        wrongTasksIndexes = wrongTasks;
        return score;
    }

    /*Gets param from a request, with the name, which corresponds to
    * test page naming conventions: field_task-id*/
    private String getRequestParamInputValue(Task task){
        return "field_"+String.valueOf(task.getId());
    }

    void showExamResult(HttpServletResponse response){
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("mistakes", wrongTasksIndexes)
                    .put("score", score)
                    .put("maxScore", maxScore);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(jsonResponse.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Could not get response writer");
        }
    }
}
