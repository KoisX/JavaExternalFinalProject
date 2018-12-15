package com.javacourse.home;

import com.javacourse.ApplicationResources;
import com.javacourse.security.AuthorizationCommandFactory;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.utils.LogConfigurator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet responsible for rounting "general" pages:
 * /Home/Index
 * /Home/Rules
 * /Home/About
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/Home/*", "/"})
public class HomeServlet extends HttpServlet {

    private Logger logger;

    @Override
    public void init() throws ServletException {
        //configuring log4j logger
        String contextPath = getServletContext().getRealPath("/");
        logger = LogConfigurator.getLogger(contextPath, this.getClass());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resultPage;
        try{
            CommandFactory factory = new HomeCommandFactory(request);
            Command command = factory.defineCommand();
            resultPage = command.execute(request);
        }catch (Exception e){
            resultPage = ApplicationResources.getErrorPage(request.getContextPath());
        }
        request.getRequestDispatcher(resultPage).forward(request, response);
    }
}
