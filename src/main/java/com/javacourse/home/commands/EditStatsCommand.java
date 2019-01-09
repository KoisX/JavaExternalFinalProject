package com.javacourse.home.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.stats.Stats;
import com.javacourse.stats.StatsService;
import com.javacourse.utils.BeanValidatorConfig;
import com.javacourse.utils.JsonManager;
import com.javacourse.utils.LogConfigurator;
import com.javacourse.utils.ResourceBundleConfig;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

import static com.javacourse.shared.WebPage.*;

public class EditStatsCommand implements Command {

    private static final String LANG_PARAM = "lang";
    private static final String SCORE = "score";
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
        BeanValidatorConfig<Stats> validator = new BeanValidatorConfig<>(lang);
        if(!validator.isValid(stats)){
            JsonManager.sendSingleMessage("error", validator.getErrorMessage(), response);
        }else {
            editStats(response, stats, lang);
        }
        return WebPage.STAND_STILL_PAGE;
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

    private void editStats(HttpServletResponse response, Stats stats, String lang){
        StatsService statsService = new StatsService();
        JsonManager json = new JsonManager(response);
        try {
            if(statsService.updateScore(stats)){
                json.put("url", new WebPage(WebPageBase.STATS_ACTION));
            }
        } catch ( UnsuccessfulQueryException e) {
            ResourceBundle resourceBundle = ResourceBundleConfig.getErrorResourceBundle(lang);
            json.put("error", resourceBundle.getString("msg.creationUnsuccessful"));
        }
        json.respond();
    }

}
