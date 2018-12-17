package com.javacourse.shared;

import com.javacourse.WebKeys;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Is triggered when user changer interface language
 */
@WebFilter(filterName = "LanguageFilter", urlPatterns = {"/*"})
public class LanguageFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        if(request.getParameter(WebKeys.getLangUrlParam())!=null){
            request.getSession().setAttribute(WebKeys.getLangSession(), request.getParameter(WebKeys.getLangUrlParam()));
        }

        chain.doFilter(req, resp);
    }

}
