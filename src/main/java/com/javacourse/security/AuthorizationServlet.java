package com.javacourse.security;

import com.javacourse.ApplicationResources;
import com.javacourse.shared.Command;
import com.javacourse.shared.CommandFactory;
import com.javacourse.user.UserDAO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = {"/Login/*"})
public class AuthorizationServlet extends HttpServlet {

    /*private final static Logger logger;
    //logger configuration
    static {
        logger = Logger.getLogger(UserDAO.class);
    }*/

    @Override
    public void init() throws ServletException {

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
        String resultPage;
        try{
            CommandFactory factory = new AuthorizationCommandFactory(request);
            Command command = factory.defineCommand();
            resultPage = command.execute(request);
        }catch (Exception e){

            resultPage = ApplicationResources.getErrorPage();
        }
        request.getRequestDispatcher(resultPage).forward(request, response);
    }

    @Override
    public void destroy() {

    }
}
