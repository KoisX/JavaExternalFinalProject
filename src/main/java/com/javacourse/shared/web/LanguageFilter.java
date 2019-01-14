package com.javacourse.shared.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Is triggered when user changer interface language
 */
@WebFilter(filterName = "LanguageFilter", urlPatterns = {"/*"})
public class LanguageFilter implements Filter {

    private static final String LANG_PARAM = "lang";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        if(request.getParameter(LANG_PARAM)!=null){
            request.getSession().setAttribute(LANG_PARAM, request.getParameter(LANG_PARAM));
        }

        chain.doFilter(req, resp);

    }

}
