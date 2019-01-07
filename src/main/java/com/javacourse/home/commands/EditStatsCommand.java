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
import com.javacourse.utils.ResourceBundleConfig;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import static com.javacourse.shared.WebPage.*;

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
            showErrorResult(response, violations.iterator().next().getMessage());
            return WebPage.STAND_STILL_PAGE;
        }

        return getPageBasedOnWhetherEditIsSuccessful(request, response,stats, lang);
    }

    @SuppressWarnings("Duplicates")
    private void showErrorResult(HttpServletResponse response, String error) {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("error", error);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(jsonResponse.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException("Could not get response writer");
        }
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
            logger.warn(e.getMessage());
        }
        return stats;
    }

    private WebPage getPageBasedOnWhetherEditIsSuccessful(HttpServletRequest request, HttpServletResponse response, Stats stats, String lang){
        StatsService statsService = new StatsService();

        JSONObject jsonResponse = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            if(statsService.updateScore(stats)){
                jsonResponse.put("url", new WebPage(WebPageBase.STATS_ACTION));
            }
        } catch (SQLException | UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getResourceBundle(lang);
            jsonResponse.put("error", resourceBundle.getString("msg.creationUnsuccessful"));
        }
        try {
            response.getWriter().write(jsonResponse.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException("Could not get response writer");
        }
        return WebPage.STAND_STILL_PAGE;
    }

}
