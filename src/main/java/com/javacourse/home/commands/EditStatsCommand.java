package com.javacourse.home.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.stats.Stats;
import com.javacourse.stats.StatsService;
import com.javacourse.test.Test;
import com.javacourse.user.User;
import com.javacourse.utils.BeanValidatorConfig;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.sql.SQLException;
import java.util.Set;

public class EditStatsCommand implements Command {

    private static final String LANG_PARAM = "lang";
    private static final String ERROR_REQUEST_MESSAGE = "error";
    private static final String SCORE = "score";
    private static final String EMAIL = "email";
    private static final String NAME = "name";
    private static final String TEST = "test";
    private static final String STAT = "stat";
    private static final int SCORE_INVALID = -1;
    private static final String ID = "id";
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(EditStatsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        Stats stats = constructPartialStatsModel(request);
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);

        //validating model and getting violations if sth is wrong
        Set<ConstraintViolation<Stats>> violations = BeanValidatorConfig
                .getValidator(lang)
                .validate(stats);

        //set error message if model is not valid
        if(!violations.isEmpty()){
            request.setAttribute(ERROR_REQUEST_MESSAGE, violations.iterator().next().getMessage());
            return WebPage.STATS_ADMIN_FORWARD_DETAILS;
        }

        return getPageBasedOnWhetherEditIsSuccessful(request, stats);
    }

    /*Constructs model, filling in only those fields, which are required*/
    private Stats constructPartialStatsModel(HttpServletRequest request) {
        Stats stats = new Stats();
        String scoreParam = request.getParameter(SCORE);
        String id = request.getParameter(ID);
        try {
            stats.setScore(Integer.parseInt(scoreParam));
            stats.setId(Long.parseLong(id));
        } catch (NumberFormatException e) {
            //stats.setScore(SCORE_INVALID);
            setStatsRequestProperties(request, stats);
            logger.warn(e.getMessage());
        }
        return stats;
    }

    private void setStatsRequestProperties(HttpServletRequest request, Stats stats){
        stats.setScore(SCORE_INVALID);
        User user = new User();
        user.setName(request.getParameter(NAME));
        user.setEmail(request.getParameter(EMAIL));

        Test test = new Test();
        test.setHeader(request.getParameter(TEST));
        stats.setUser(user);
        stats.setTest(test);
        stats.setId(Long.parseLong(request.getParameter(ID)));
        request.setAttribute(STAT, stats);
    }

    private WebPage getPageBasedOnWhetherEditIsSuccessful(HttpServletRequest request, Stats stats){
        WebPage webPage = WebPage.STATS_FORWARD_ACTION;
        StatsService statsService = new StatsService();
        try {
            if(statsService.updateScore(stats)){
                webPage = WebPage.STATS_REDIRECT_ACTION;
            }
        } catch (SQLException | UnsuccessfulQueryException e) {
            //add filling in stats param!
            webPage = WebPage.STATS_ADMIN_FORWARD_DETAILS;
        }
        return webPage;
    }

}
