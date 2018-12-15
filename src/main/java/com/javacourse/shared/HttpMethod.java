package com.javacourse.shared;

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
