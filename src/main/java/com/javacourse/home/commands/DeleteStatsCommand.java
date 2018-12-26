package com.javacourse.home.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.stats.StatsService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteStatsCommand implements Command {

    private static final String ID = "id";

    @Override
    public WebPage execute(HttpServletRequest request) {
        WebPage webPage = WebPage.STATS_ACTION.setDoRedirect(true);
        StatsService statsService = new StatsService();
        String id = request.getParameter(ID);
        try {
            statsService.delete(Integer.parseInt(id));
        } catch (UnsuccessfulQueryException | SQLException e) {
            webPage = WebPage.ERROR_ACTION.setDoRedirect(true);
            return webPage;
        }
        return webPage;
    }
}
