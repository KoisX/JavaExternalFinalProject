package com.javacourse.shared;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * CommandFactory is the basic class, the implementations of which will be able
 * to define an appropriate Command according to request URI
 */
public abstract class CommandFactory {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     *
     * @param request HttpServletRequest object of the current request
     */
    public CommandFactory(HttpServletRequest request, HttpServletResponse response) {
        //creating safe copy of the request
        this.request = new HttpServletRequestWrapper(request);
        this.response = new HttpServletResponseWrapper(response);
    }

    /**
     * Defines an appropriate Command according to request URI
     * @return Command entity
     */
    public abstract Command defineCommand();
}
