package com.javacourse.shared;

import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller responsible for handling error situations
 */
@WebServlet(name = "Error", urlPatterns = "/Error")
public class ErrorServlet extends HttpServlet {

    private static final String ERROR_PAGE = "/error.jsp";
    private Logger logger;

    @Override
    public void init() throws ServletException {
        //configuring log4j logger
        String contextPath = getServletContext().getRealPath("/");
        logger = LogConfigurator.getLogger(contextPath, this.getClass());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
    }
}
