package com.javacourse.utils;

import com.javacourse.shared.WebPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Utility class, which encapsulates the logic of dispatching WebPage entities
 */
public class WebPageDispatcher {
    HttpServletRequest request;
    HttpServletResponse response;
    WebPage webPage;

    public WebPageDispatcher(HttpServletRequest request, HttpServletResponse response, WebPage webPage) {
        this.request = request;
        this.response = response;
        this.webPage = webPage;
    }

    public void dispatch() throws ServletException, IOException {
        if(webPage==null || webPage.getPath()==null){
            request.getRequestDispatcher(getUrl(WebPage.ERROR_ACTION)).forward(request, response);
            return;
        }
        if(webPage.isDoRedirect()){
            response.sendRedirect(getUrl());
        }else {
            request.getRequestDispatcher(getUrl()).forward(request, response);
        }
    }

    private String getUrl(WebPage page){
        return request.getContextPath() + page.getPath();
    }

    private String getUrl(){
        return request.getContextPath() + webPage.getPath();
    }
}
