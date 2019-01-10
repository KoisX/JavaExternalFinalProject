package com.javacourse.home.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.stats.StatsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import static com.javacourse.shared.WebPage.WebPageBase;

public class DeleteStatsCommand implements Command {

    private static final String ID = "id";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        StatsService statsService = new StatsService();
        return getPageDependingOnStatusOfDeletion(request, statsService);
    }

    WebPage getPageDependingOnStatusOfDeletion(HttpServletRequest request, StatsService statsService){
        WebPage webPage = new WebPage(WebPageBase.STATS_ACTION)
                .setDispatchType(WebPage.DispatchType.REDIRECT)
                .setQueryString("?page="+request.getParameter("page"));
        String id = request.getParameter(ID);
        try {
            statsService.delete(id);
        } catch (UnsuccessfulQueryException e) {
            webPage = new WebPage(WebPageBase.ERROR_ACTION)
                    .setDispatchType(WebPage.DispatchType.REDIRECT);
            return webPage;
        }
        return webPage;
    }
}
