package com.javacourse.test.commands;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.stats.Stats;
import com.javacourse.stats.StatsService;
import com.javacourse.test.Test;
import com.javacourse.test.answer.Answer;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import com.javacourse.user.User;
import com.javacourse.user.UserService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import static com.javacourse.shared.WebPage.*;

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

        if(testId==null || !getTasksAndMaxScoreFromDb(testId)){
            return new WebPage(WebPageBase.ERROR_ACTION);
        }
        score = reviseTest(request.getParameterMap());

        insertTestResultToStats((String) request.getSession()
                .getAttribute(ApplicationResources
                .getUserEmail()), Long.parseLong(testId));

        //TODO: send email with result to user

        showExamResult(response);
        return new WebPage(WebPageBase.STAND_STILL_PAGE).setDispatchType(DispatchType.STAND_STILL);
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

    boolean insertTestResultToStats(String userEmail, long testId){
        StatsService statsService = new StatsService();
        boolean res;
        try {
            res = statsService.create(constructStatsObject(testId, getUserId(userEmail)));
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
        return res;
    }

    Stats constructStatsObject(long testId, long userId){
        Stats stats = new Stats();
        Test test = new Test();
        User user = new User();
        test.setId(testId);
        user.setId(userId);
        stats.setTest(test);
        stats.setUser(user);
        stats.setScore(Math.round(getPercentageOfSolvedTasks()));
        stats.setTimePassed(new Timestamp(System.currentTimeMillis()));
        return stats;
    }


    long getUserId(String email){
        long id = -1;
        try{
            UserService userService = new UserService();
            id = userService.getUserIdByEmail(email);
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
        }
        return id;
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
            if(userAnswers.containsAll(correctAnswers) && userAnswers.size()==correctAnswers.size()){
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
                    .put("maxScore", maxScore)
                    .put("message", getMessage());
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

    /**
     * Returns the message to be shown depending on the test result
     * @return test result summary message
     */
    private String getMessage() {
        //TODO: get msg from resource bundle
        if(maxScore==0)
            return "No result";
        double percentageOfSolvedTasks = getPercentageOfSolvedTasks();
        if(percentageOfSolvedTasks<40)
            return "You need to study harder!";
        else if(percentageOfSolvedTasks<75)
            return "Good work!";
        else return "Well done!";
    }

    private double getPercentageOfSolvedTasks(){
        return ((double)score)/maxScore*100;
    }
}
