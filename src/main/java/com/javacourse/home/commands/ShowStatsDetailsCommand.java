package com.javacourse.home.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.stats.Stats;
import com.javacourse.stats.StatsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import static com.javacourse.shared.WebPage.WebPageBase;

public class ShowStatsDetailsCommand implements Command {

    private static final String ID = "id";
    private static final String STAT_PROP = "stat";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        return getWebPageBasedOnWhetherQueryIsSuccessful(request);
    }

    private WebPage getWebPageBasedOnWhetherQueryIsSuccessful(HttpServletRequest request){
        WebPage webPage = new WebPage(WebPageBase.STATS_ADMIN_DETAILS);
        StatsService statsService = new StatsService();
        try {
            String id = request.getParameter(ID);
            Stats stats = statsService.findById(id);
            request.setAttribute(STAT_PROP, stats);
        } catch (UnsuccessfulQueryException | SQLException | NumberFormatException e) {
            webPage = new WebPage(WebPageBase.ERROR_ACTION);
        }
        return webPage;
    }
}
