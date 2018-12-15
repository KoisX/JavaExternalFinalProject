package com.javacourse.shared;

/**
 * Simple enum which encapsulated the logic of determining
 * whether the method is <code>GET</code> or <code>POST</code>
 */
public enum HttpMethod {
    GET("GET"),
    POST("POST");

    HttpMethod(String value) {
        this.value = value;
    }

    String value;

    public static boolean isGet(String method){
        return method.equals(GET.value);
    }
}
