package com.javacourse.shared;

/**
 * Represents a wrapper for a path,
 * which the request will be forwarded or redirected to
 */
public enum WebPage {
    INDEX_PAGE("/index.jsp"),
    SIGN_UP_PAGE("/signup.jsp"),
    LOGIN_PAGE("/login.jsp"),
    ABOUT_PAGE("/about.jsp"),
    RULES_PAGE("/rules.jsp"),
    STATS_ADMIN_PAGE("/jsp/admin/stats.jsp"),
    EXAM_USER_PAGE("/jsp/user/exam.jsp"),
    TESTS_USER_PAGE("/jsp/user/tests.jsp"),
    TESTS_ADMIN_PAGE("/jsp/admin/tests.jsp"),
    TOPICS_ADMIN_PAGE("/jsp/admin/topics.jsp"),
    TOPICS_USER_PAGE("/jsp/user/topics.jsp"),
    ERROR_ACTION("/Error"),
    LOGIN_ACTION("/Login/SignIn"),
    INDEX_ACTION("/Home/Index");

    String path;
    boolean doRedirect;

    WebPage(String path) {
        this.path = path;
    }

    WebPage(String path, boolean doRedirect) {
        this.path = path;
        this.doRedirect = doRedirect;
    }

    public boolean isDoRedirect() {
        return doRedirect;
    }

    //allows to set redirect param in declarative style
    public WebPage setDoRedirect(boolean doRedirect) {
        this.doRedirect = doRedirect;
        return this;
    }

    public String getPath() {
        return path;
    }
}
