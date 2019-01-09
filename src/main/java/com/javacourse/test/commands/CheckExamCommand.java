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
import com.javacourse.utils.JsonManager;
import com.javacourse.utils.LogConfigurator;
import com.javacourse.utils.ResourceBundleConfig;
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

    private final static Logger logger;
    private final static String ID_PARAM = "id";
    private static final String LANG_PARAM = "lang";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(CheckExamCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        String testId = request.getParameter(ID_PARAM);
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);

        TestResult testResult = new TestResult();

        if(testId==null || !getTasksAndMaxScoreFromDb(testId, testResult)){
            return new WebPage(WebPageBase.ERROR_ACTION);
        }

        testResult.setScore(reviseTest(request.getParameterMap(), testResult));

        //no reaction for unsuccessful insert is foreseen
        //cause we want user to see their result even if something goes wrong
        insertTestResultToStats((String) request.getSession()
                .getAttribute(ApplicationResources.getUserEmail()), Long.parseLong(testId), testResult);

        //TODO: send email with result to user

        showExamResult(response, testResult, lang);
        return new WebPage(WebPageBase.STAND_STILL_PAGE).setDispatchType(DispatchType.STAND_STILL);
    }

    private boolean getTasksAndMaxScoreFromDb(String testId, TestResult testResult){
        try{
            TaskService taskService = new TaskService();
            testResult.setTasks(taskService.findTasksByTestId(testId));
            testResult.setMaxScore(taskService.getMaximalScoreByTestId(testId));
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    private boolean insertTestResultToStats(String userEmail, long testId, TestResult testResult){
        StatsService statsService = new StatsService();
        boolean res;
        try {
            res = statsService.create(constructStatsObject(testId, getUserId(userEmail), testResult));
        } catch (UnsuccessfulQueryException  e) {
            logger.error(e.getMessage());
            return false;
        }
        return res;
    }

    private Stats constructStatsObject(long testId, long userId, TestResult testResult){
        Stats stats = new Stats();
        Test test = new Test();
        User user = new User();
        test.setId(testId);
        user.setId(userId);
        stats.setTest(test);
        stats.setUser(user);
        stats.setScore(Math.round(getPercentageOfSolvedTasks(testResult)));
        stats.setTimePassed(new Timestamp(System.currentTimeMillis()));
        return stats;
    }


    private long getUserId(String email){
        long id = -1;
        try{
            UserService userService = new UserService();
            id = userService.getUserIdByEmail(email);
        } catch (UnsuccessfulQueryException  e) {
            logger.error(e.getMessage());
        }
        return id;
    }

    private int reviseTest(Map<String, String[]> paramMap, TestResult testResult){
        List<Long> wrongTasks = new ArrayList<>();
        int score = 0;
        for(Task task : testResult.getTasks()){
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
        testResult.setWrongTasksIndexes(wrongTasks);
        return score;
    }

    /*Gets param from a request, with the name, which corresponds to
    * test page naming conventions: field_task-id*/
    private String getRequestParamInputValue(Task task){
        return "field_"+String.valueOf(task.getId());
    }

    private void showExamResult(HttpServletResponse response, TestResult testResult, String lang){
        JsonManager json = new JsonManager(response);
        json.put("mistakes", testResult.getWrongTasksIndexes())
            .put("score", testResult.getScore())
            .put("maxScore", testResult.getMaxScore())
            .put("message", getMessage(testResult, lang));
        json.respond();
    }

    /**
     * Returns the message to be shown depending on the test result
     * @return test result summary message
     */
    private String getMessage(TestResult testResult, String lang) {
        ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang, "exam_result_messages");
        if(testResult.getMaxScore()==0)
            return resourceBundle.getString("msg.noresult");
        double percentageOfSolvedTasks = getPercentageOfSolvedTasks(testResult);
        if(percentageOfSolvedTasks<40)
            return resourceBundle.getString("msg.studyHarder");
        else if(percentageOfSolvedTasks<75)
            return resourceBundle.getString("msg.goodWork");
        else return resourceBundle.getString("msg.wellDone");
    }

    private double getPercentageOfSolvedTasks(TestResult testResult){
        return ((double)testResult.getScore())/testResult.getMaxScore()*100;
    }

    /**
     * Value object for comfortable transport of multiple params
     * between multiple methods
     */
    private class TestResult{
        private List<Task> tasks;
        private int maxScore;
        private int score;

        /*Tasks, in which user made mistakes*/
        private List<Long> wrongTasksIndexes;

        public void setTasks(List<Task> tasks) {
            this.tasks = tasks;
        }

        void setMaxScore(int maxScore) {
            this.maxScore = maxScore;
        }

        public void setScore(int score) {
            this.score = score;
        }

        void setWrongTasksIndexes(List<Long> wrongTasksIndexes) {
            this.wrongTasksIndexes = wrongTasksIndexes;
        }

        public List<Task> getTasks() {
            return tasks;
        }

        int getMaxScore() {
            return maxScore;
        }

        public int getScore() {
            return score;
        }

        List<Long> getWrongTasksIndexes() {
            return wrongTasksIndexes;
        }
    }

}
