package com.javacourse.home.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.security.command.SignInCommand;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.stats.Stats;
import com.javacourse.stats.StatsService;
import com.javacourse.test.topic.Topic;
import com.javacourse.test.topic.TopicService;
import com.javacourse.utils.BeanValidatorConfig;
import com.javacourse.utils.LogConfigurator;
import com.javacourse.utils.ResourceBundleConfig;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class EditStatsCommand implements Command {

    private static final String LANG_PARAM = "lang";
    private static final String ERROR_REQUEST_MESSAGE = "error";
    private static final String SCORE = "score";
    private static final int SCORE_INVALID = -1;
    private static final String ID = "id";
    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(EditStatsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request) {
        Stats stats = constructPartialStatsModel(request);
        String lang = (String)request.getSession().getAttribute(LANG_PARAM);

        //validating model and getting violations if sth is wrong
        Set<ConstraintViolation<Stats>> violations = BeanValidatorConfig
                .getValidator(lang)
                .validate(stats);

        //set error message if model is not valid
        if(!violations.isEmpty()){
            request.setAttribute(ERROR_REQUEST_MESSAGE, violations.iterator().next().getMessage());
            return WebPage.STATS_ADMIN_DETAILS;
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
            stats.setScore(SCORE_INVALID);
            logger.warn(e.getMessage());
        }
        return stats;
    }

    private WebPage getPageBasedOnWhetherEditIsSuccessful(HttpServletRequest request, Stats stats){
        WebPage webPage = WebPage.STATS_ACTION;
        StatsService statsService = new StatsService();
        try {
            if(statsService.updateScore(stats)){
                webPage.setDoRedirect(true);
            }
        } catch (SQLException | UnsuccessfulQueryException e) {
            //add filling in stats param!
            webPage = WebPage.STATS_ADMIN_DETAILS;
        }
        return webPage;
    }

}
