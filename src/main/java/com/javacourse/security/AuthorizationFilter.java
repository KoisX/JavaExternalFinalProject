package com.javacourse.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        final HttpSession session = request.getSession(false);

        String loginURI = request.getContextPath() + "/Login/SignIn";

        if(isLoggedIn(session)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            response.sendRedirect(loginURI);
        }

    }

    boolean isLoggedIn(HttpSession session) {
        return (session!=null
                && session.getAttribute("login")!=null
                && session.getAttribute("password")!=null);
    }
}
