package com.javacourse.utils;

import com.javacourse.WebKeys;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatchHelper {
    public static void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        if(request.getAttribute(WebKeys.getShouldRedirect())==null)
            request.getRequestDispatcher(url).forward(request, response);
        else response.sendRedirect(url);
    }
}
