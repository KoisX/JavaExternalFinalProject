package com.javacourse.shared.web;

import com.javacourse.utils.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MailServlet", urlPatterns = {"/Mail/*"})
public class MailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            String to = "kois@ukr.net";
            String subject = "subject";
            String message =  "message";
            String user = "kois9911@gmail.com";
            String pass = "YfdexjljyjcjH1";
            SendMail.send(to,subject, message, user, pass);
            out.println("Mail send successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
