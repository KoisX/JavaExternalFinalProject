package com.javacourse.test.commands;

import com.javacourse.ApplicationResources;
import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.mail.LetterComposer;
import com.javacourse.mail.MailManager;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.stats.Stats;
import com.javacourse.stats.StatsService;
import com.javacourse.test.Test;
import com.javacourse.test.revisor.ExamResult;
import com.javacourse.test.revisor.ExamReviser;
import com.javacourse.test.task.Task;
import com.javacourse.test.task.TaskService;
import com.javacourse.user.User;
import com.javacourse.user.UserService;
import com.javacourse.utils.JsonManager;
import com.javacourse.utils.LogConfigurator;
import com.javacourse.utils.ResourceBundleConfig;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

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

        StatsService statsService = new StatsService();
        TaskService taskService = new TaskService();
        UserService userService = new UserService();

        //test revision

        List<Task> tasks = getTasksFromDb(testId, taskService);
        int maxScore = getMaxScoreFromDb(testId, taskService);
        ExamResult result = new ExamReviser().reviseTest(request.getParameterMap(), tasks, maxScore);

        //inserting test result to stats in db

        insertTestResultToStats((String) request.getSession()
                .getAttribute(ApplicationResources.getUserEmail()),
                Long.parseLong(testId),
                result,
                statsService,
                userService);

        //no reaction for unsuccessful email sending is foreseen
        //cause we want user to see their result even if something goes wrong
        try {
            //sending mail to user
            sendMail(request, result);
        } catch (Exception e) {
            logger.error("Problem while sending email "+ e.getMessage());
        }

        showExamResult(response, result, lang);
        return new WebPage(WebPageBase.STAND_STILL_PAGE).setDispatchType(DispatchType.STAND_STILL);
    }

    int getMaxScoreFromDb(String testId, TaskService taskService){
          return  taskService.getMaximalScoreByTestId(testId);
    }

    List<Task> getTasksFromDb(String testId, TaskService taskService){
        return taskService.findTasksByTestId(testId);
    }

    boolean insertTestResultToStats(String userEmail, long testId, ExamResult testResult, StatsService statsService, UserService userService){
        boolean res;
        try {
            res = statsService.create(constructStatsObject(testId, getUserId(userEmail, userService), testResult));
        } catch (UnsuccessfulQueryException  e) {
            logger.error(e.getMessage());
            return false;
        }
        return res;
    }

    Stats constructStatsObject(long testId, long userId, ExamResult testResult){
        Stats stats = new Stats();
        Test test = new Test();
        User user = new User();
        test.setId(testId);
        user.setId(userId);
        stats.setTest(test);
        stats.setUser(user);
        stats.setScore(Math.round(testResult.getPercentageOfSolvedTasks()));
        stats.setTimePassed(new Timestamp(System.currentTimeMillis()));
        return stats;
    }


    private long getUserId(String email, UserService userService){
        long id = -1;
        try{
            id = userService.getUserIdByEmail(email);
        } catch (UnsuccessfulQueryException  e) {
            logger.error(e.getMessage());
        }
        return id;
    }

    private void showExamResult(HttpServletResponse response, ExamResult testResult, String lang){
        JsonManager json = new JsonManager(response);
        json.put("mistakes", testResult.getWrongTasksIndexes())
            .put("score", testResult.getScore())
            .put("maxScore", testResult.getMaxScore())
            .put("message", getMessage(testResult, lang, "exam_result_messages"));
        json.respond();
    }

    /**
     * Returns the message to be shown depending on the test result
     * @return test result summary message
     */
    private String getMessage(ExamResult testResult, String lang, String base) {
        ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang, base);
        if(testResult.getMaxScore()==0)
            return resourceBundle.getString("msg.noresult");
        double percentageOfSolvedTasks = testResult.getPercentageOfSolvedTasks();
        if(percentageOfSolvedTasks<40)
            return resourceBundle.getString("msg.studyHarder");
        else if(percentageOfSolvedTasks<75)
            return resourceBundle.getString("msg.goodWork");
        else return resourceBundle.getString("msg.wellDone");
    }

    private void sendMail(HttpServletRequest request, ExamResult testResult) {
        ServletContext servletContext = request.getServletContext();
        String to = (String) request.getSession().getAttribute("login");
        Properties properties = new Properties();
        String filename = servletContext.getInitParameter("mail");
        try {
            properties.load(servletContext.getResourceAsStream(filename));
        } catch (IOException e) {
            logger.error(e.getMessage());
            return;
        }

        MailManager mailManager = MailManager.createMailManager(to,
                getMessage(testResult, (String)request.getSession().getAttribute("lang"), "exam_result_header_messages"),
                LetterComposer.compose(testResult.getScore(), testResult.getMaxScore(), (String)request.getSession().getAttribute("lang")),
                properties
                );

        mailManager.sendMail();
    }

}
