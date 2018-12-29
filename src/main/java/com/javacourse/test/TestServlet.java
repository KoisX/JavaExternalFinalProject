package com.javacourse.test;

import com.javacourse.ApplicationResources;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.shared.WebPage;
import com.javacourse.utils.LogConfigurator;
import com.javacourse.utils.WebPageDispatcher;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestServlet", urlPatterns = {"/Test/*"})
public class TestServlet extends HttpServlet {

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
        WebPage resultPage;
        try{
            CommandFactory factory = new TestCommandFactory(request, response);
            Command command = factory.defineCommand();
            resultPage = command.execute(request, response);
        }catch (Exception e){
            resultPage = WebPage.ERROR_ACTION;
            logger.error(e.getMessage());
        }
        new WebPageDispatcher(request, response, resultPage).dispatch();
    }
}
