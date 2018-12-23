package com.javacourse.shared.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Sets the encoding of the pages to default encoding (UTF-8)
 */
@WebFilter(filterName = "EncodingFilter",
           urlPatterns = {"/*"},
           initParams = @WebInitParam(name="requestEncoding", value = "UTF-8"))
public class EncodingFilter implements Filter {

    private String encoding;
    private static final  String DEFAULT_ENCODING = "UTF-8";

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if(encoding==null)
            encoding = DEFAULT_ENCODING;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if(req.getCharacterEncoding()==null)
            req.setCharacterEncoding(encoding);

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding(encoding);

        chain.doFilter(req, resp);
    }

}
