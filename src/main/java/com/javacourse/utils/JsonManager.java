package com.javacourse.utils;

import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A utility class, which encapsulates the basic actions,
 * which may be done with JSONObject entity to send response for AJAX request
 */
public class JsonManager {
    private JSONObject jsonResponse;
    private HttpServletResponse response;

    public JsonManager(HttpServletResponse response) {
        jsonResponse = new JSONObject();
        this.response = response;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    }

    public JsonManager put(String key, Object value){
        jsonResponse.put(key, value);
        return this;
    }

    public void respond(){
        try {
            response.getWriter().write(jsonResponse.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException("Could not get response writer");
        }
    }

    public static void sendSingleMessage(String errorKey, String errorValue, HttpServletResponse response){
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(errorKey, errorValue);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(jsonResponse.toString());
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException("Could not get response writer");
        }
    }

}
