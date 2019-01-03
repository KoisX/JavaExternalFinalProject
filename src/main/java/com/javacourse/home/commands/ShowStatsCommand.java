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
import static com.javacourse.shared.WebPage.WebPageBase;

public class ShowStatsCommand implements Command {

    private List<Stats> stats;
    private int currentPage = 1;
    private int numberOfPages = 0;
    private final static Logger logger;
    private final static int RECORDS_PER_PAGE = 10;
    private final static String PAGE_PARAM = "page";
    private final static String STATS_ATTRIBUTE = "stats";
    private final static String CURRENT_PAGE_ATTRIBUTE = "currentPage";
    private final static String PAGES_ATTRIBUTE = "pages";
    private final static String RECORDS_PER_PAGE_ATTRIBUTE = "recordsPerPage";

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowStatsCommand.class);
    }

    @Override
    public WebPage execute(HttpServletRequest request, HttpServletResponse response) {
        WebPage resultingPage = new WebPage(WebPageBase.STATS_ADMIN_PAGE);

        if(!getPageInfo(request)){
            resultingPage = new WebPage(WebPageBase.ERROR_ACTION);
            return resultingPage;
        }

        initRequestAttributes(request);
        return resultingPage;
    }

    private boolean getPageInfo(HttpServletRequest request){
        StatsService statsService = new StatsService();
        if(request.getParameter(PAGE_PARAM) != null)
            currentPage = Integer.parseInt(request.getParameter(PAGE_PARAM));
        try {
            stats = statsService.findAllWithPagination(getOffset(), RECORDS_PER_PAGE);
            numberOfPages = statsService.getNumberOfPages(RECORDS_PER_PAGE);
        } catch (UnsuccessfulQueryException | SQLException e) {
            logger.error(e.getMessage());
            return false;
        }

        //checking if currentPage param is in accepted value segment
        return currentPage >= 0 && currentPage <= numberOfPages;
    }

    private void initRequestAttributes(HttpServletRequest request){
        request.setAttribute(STATS_ATTRIBUTE, stats);
        request.setAttribute(CURRENT_PAGE_ATTRIBUTE, currentPage);
        request.setAttribute(PAGES_ATTRIBUTE, numberOfPages);
        request.setAttribute(RECORDS_PER_PAGE_ATTRIBUTE, RECORDS_PER_PAGE);
    }

    private int getOffset(){
        return (currentPage-1)*RECORDS_PER_PAGE;
    }
}
