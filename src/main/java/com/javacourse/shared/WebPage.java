package com.javacourse.shared;

import javax.servlet.ServletException;
import java.io.IOException;

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
    ERROR_PAGE("/error.jsp"),
    STATS_ADMIN_PAGE("/WEB-INF/jsp/admin/stats.jsp"),
    EXAM_USER_PAGE("/WEB-INF/jsp/user/exam.jsp"),
    TESTS_USER_PAGE("/WEB-INF/jsp/user/tests.jsp"),
    TESTS_ADMIN_PAGE("/WEB-INF/jsp/admin/tests.jsp"),
    TOPICS_ADMIN_PAGE("/WEB-INF/jsp/admin/topics.jsp"),
    TOPICS_USER_PAGE("/WEB-INF/jsp/user/topics.jsp"),
    TOPICS_ADMIN_CREATE("/WEB-INF/jsp/admin/topic-create.jsp"),
    TOPICS_ADMIN_EDIT("/WEB-INF/jsp/admin/topic-edit.jsp"),
    STATS_ADMIN_DETAILS("/WEB-INF/jsp/admin/stats-detail.jsp"),
    TEST_USER_RESULTS("/WEB-INF/jsp/user/exam-results.jsp"),
    ERROR_ACTION("/Error"),
    LOGIN_ACTION("/Login/SignIn"),
    TOPICS_ACTION("/Topic/List"),
    INDEX_ACTION("/Home/Index"),
    STATS_ACTION("/Home/Stats");

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
