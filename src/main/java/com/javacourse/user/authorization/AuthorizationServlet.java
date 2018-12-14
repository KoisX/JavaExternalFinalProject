package com.javacourse.user.authorization;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = {"/Login/*"})
public class AuthorizationServlet extends HttpServlet {

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

    void processRequest(HttpServletRequest request, HttpServletResponse response){
        /*System.out.println("-----");
        System.out.println(request.getPathInfo()+";");
        System.out.println(request.getContextPath()+";");
        System.out.println(request.getRequestURI()+";");
        System.out.println(request.getRequestURL()+";");
        System.out.println(request.getPathTranslated()+";");
        System.out.println(request.getServletPath()+";");*/
    }

    @Override
    public void destroy() {

    }
}
