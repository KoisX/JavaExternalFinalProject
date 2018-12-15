package com.javacourse.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter, which is responsible for checking whether a user is allowed to access
 * current page
 */
@WebFilter(urlPatterns = {"/Test/*"})
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        String loginURI = request.getContextPath() + "/Login/SignIn";

        if(isLoggedIn(request) || isRequestAllowedForUnauthorizedUsers(request, loginURI)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            response.sendRedirect(loginURI);
        }

    }

    boolean isLoggedIn(HttpServletRequest request) {
        Cookie login=null, password=null;
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("login")){
                login = cookie;
            }
            if(cookie.getName().equals("password")){
                password = cookie;
            }
        }
        return (login!=null && password!=null);
    }

    //TODO: change allowed pages as this filter is now only used with tests
    boolean isRequestAllowedForUnauthorizedUsers(HttpServletRequest request, String loginURI) {
        /*List of pages allowed for unauthorized users*/
        String allTopicsPage = request.getContextPath() + "/Test/All";

        /*Page, from which the request was sent*/
        String currentURI = request.getRequestURI();

        return currentURI.equals(allTopicsPage);
    }



    @Override
    public void destroy() {

    }
}