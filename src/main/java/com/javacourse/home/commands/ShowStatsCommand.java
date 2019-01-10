package com.javacourse.home.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.shared.WebPage;
import com.javacourse.stats.Stats;
import com.javacourse.stats.StatsService;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.javacourse.shared.WebPage.WebPageBase;

public class ShowStatsCommand implements Command {

    private final static int RECORDS_PER_PAGE = 10;
    private final static String PAGE_PARAM = "page";
    private final static String STATS_ATTRIBUTE = "stats";
    private final static String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private final static String PAGES_ATTRIBUTE = "pages";
    private final static String RECORDS_PER_PAGE_ATTRIBUTE = "recordsPerPage";

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        WebPage resultingPage = new WebPage(WebPageBase.STATS_ADMIN_PAGE);
        StatsService statsService = new StatsService();
        int currentPage = Integer.parseInt(Optional
                .ofNullable(request.getParameter(PAGE_PARAM))
                .orElse("1"));
        int numberOfPages = getNumberOfPages(statsService);

        if(!isPageAccessible(currentPage, numberOfPages)){
            resultingPage = new WebPage(WebPageBase.ERROR_ACTION);
            return resultingPage;
        }

        List<Stats> stats = getStats(statsService, currentPage);
        initRequestAttributes(request, currentPage, numberOfPages, stats);
        return resultingPage;
    }

    List<Stats> getStats(StatsService statsService, int currentPage) throws UnsuccessfulQueryException {
        return statsService.findAllWithPagination(getOffset(currentPage), RECORDS_PER_PAGE);
    }

    int getNumberOfPages(StatsService service){
       return service.getNumberOfPages(RECORDS_PER_PAGE);
    }

    boolean isPageAccessible(int currentPage, int numberOfPages){
        return currentPage >= 0 && currentPage <= numberOfPages;
    }

    private void initRequestAttributes(HttpServletRequest request, int currentPage, int numberOfPages, List<Stats> stats){
        request.setAttribute(STATS_ATTRIBUTE, stats);
        request.setAttribute(CURRENT_PAGE_ATTRIBUTE, currentPage);
        request.setAttribute(PAGES_ATTRIBUTE, numberOfPages);
        request.setAttribute(RECORDS_PER_PAGE_ATTRIBUTE, RECORDS_PER_PAGE);
    }

    private int getOffset(int currentPage){
        return (currentPage-1)*RECORDS_PER_PAGE;
    }
}
