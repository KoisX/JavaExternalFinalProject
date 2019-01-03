package com.javacourse.shared.web;

import com.javacourse.shared.WebPage;
import com.javacourse.utils.WebPageDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.javacourse.shared.WebPage.WebPageBase;

/**
 * Controller responsible for handling error situations
 */
@WebServlet(name = "Error", urlPatterns = "/Error")
public class ErrorServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new WebPageDispatcher(req, resp, new WebPage(WebPageBase.ERROR_PAGE)).dispatch();
    }
}
