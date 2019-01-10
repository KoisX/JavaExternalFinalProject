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
    private static final String PAGE = "page";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        StatsService statsService = new StatsService();
        return getWebPageBasedOnWhetherQueryIsSuccessful(request,statsService);
    }

    WebPage getWebPageBasedOnWhetherQueryIsSuccessful(HttpServletRequest request, StatsService statsService){
        WebPage webPage = new WebPage(WebPageBase.STATS_ADMIN_DETAILS);
        try {
            String id = request.getParameter(ID);
            Stats stats = statsService.findById(id);
            request.setAttribute(STAT_PROP, stats);
            request.setAttribute(PAGE, request.getParameter(PAGE));
        } catch (UnsuccessfulQueryException | NumberFormatException e) {
            webPage = new WebPage(WebPageBase.ERROR_ACTION);
        }
        return webPage;
    }
}
