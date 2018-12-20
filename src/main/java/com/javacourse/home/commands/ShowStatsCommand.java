package com.javacourse.home.commands;

import com.javacourse.exceptions.UnsuccessfulQueryException;
import com.javacourse.shared.Command;
import com.javacourse.stats.Stats;
import com.javacourse.stats.StatsDAO;
import com.javacourse.test.commands.ShowTopicsCommand;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowStatsCommand implements Command {

    private final static Logger logger;

    //logger configuration
    static {
        logger = LogConfigurator.getLogger(ShowTopicsCommand.class);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        StatsDAO statsDAO = new StatsDAO();
        List<Stats> stats;

        int page = 1;
        int recordsPerPage = 10;
        int pages = 0;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        try {
            stats = statsDAO.findAllWithPagination((page-1)*recordsPerPage, recordsPerPage);
            pages = statsDAO.getNumberOfPages(recordsPerPage);
        } catch (UnsuccessfulQueryException e) {
            logger.error(e.getMessage());
            return "/Error";
        }

        if(page<0 || page>pages){
            return "/Error";
        }

        request.setAttribute("stats", stats);
        request.setAttribute("currentPage", page);
        request.setAttribute("pages", pages);
        request.setAttribute("recordsPerPage", recordsPerPage);
        return request.getContextPath()+"/jsp/admin/stats.jsp";
    }
}
