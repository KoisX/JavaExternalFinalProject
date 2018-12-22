package com.javacourse.security;

import com.javacourse.ApplicationResources;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.shared.WebPage;
import com.javacourse.utils.DispatchHelper;
import com.javacourse.utils.LogConfigurator;
import com.javacourse.utils.WebPageDispatcher;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = {"/Login/*"})
public class AuthorizationServlet extends HttpServlet {

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

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebPage resultPage;
        try{
            CommandFactory factory = new AuthorizationCommandFactory(request, response);
            Command command = factory.defineCommand();
            resultPage = command.execute(request);
        }catch (Exception e){
            resultPage = WebPage.ERROR_ACTION;
        }
        new WebPageDispatcher(request, response, resultPage).dispatch();
    }

    @Override
    public void destroy() {

    }
}
