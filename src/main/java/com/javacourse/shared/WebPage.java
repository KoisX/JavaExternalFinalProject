package com.javacourse.shared;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Represents a wrapper for a path,
 * which the request will be forwarded or redirected to
 */
public enum WebPage {
    INDEX_FORWARD_PAGE("/index.jsp", DispatchType.FORWARD),
    SIGN_UP_FORWARD_PAGE("/signup.jsp", DispatchType.FORWARD),
    LOGIN_FORWARD_PAGE("/login.jsp", DispatchType.FORWARD),
    ABOUT_FORWARD_PAGE("/about.jsp", DispatchType.FORWARD),
    RULES_FORWARD_PAGE("/rules.jsp", DispatchType.FORWARD),
    ERROR_FORWARD_PAGE("/error.jsp", DispatchType.FORWARD),
    ERROR_REDIRECT_PAGE("/error.jsp", DispatchType.REDIRECT),
    STATS_ADMIN_FORWARD_PAGE("/WEB-INF/jsp/admin/stats.jsp", DispatchType.FORWARD),
    EXAM_USER_FORWARD_PAGE("/WEB-INF/jsp/user/exam.jsp", DispatchType.FORWARD),
    TESTS_USER_FORWARD_PAGE("/WEB-INF/jsp/user/tests.jsp", DispatchType.FORWARD),
    TESTS_ADMIN_FORWARD_PAGE("/WEB-INF/jsp/admin/tests.jsp", DispatchType.FORWARD),
    TESTS_ADMIN_REDIRECT_PAGE("/WEB-INF/jsp/admin/tests.jsp", DispatchType.REDIRECT),
    TOPICS_ADMIN_FORWARD_PAGE("/WEB-INF/jsp/admin/topics.jsp", DispatchType.FORWARD),
    TOPICS_USER_FORWARD_PAGE("/WEB-INF/jsp/user/topics.jsp", DispatchType.FORWARD),
    TOPICS_ADMIN_FORWARD_CREATE("/WEB-INF/jsp/admin/topic-create.jsp", DispatchType.FORWARD),
    TOPICS_ADMIN_FORWARD_EDIT("/WEB-INF/jsp/admin/topic-edit.jsp", DispatchType.FORWARD),
    STATS_ADMIN_FORWARD_DETAILS("/WEB-INF/jsp/admin/stats-detail.jsp", DispatchType.FORWARD),
    TEST_USER_FORWARD_RESULTS("/WEB-INF/jsp/user/exam-results.jsp", DispatchType.FORWARD),
    ERROR_FORWARD_ACTION("/Error", DispatchType.FORWARD),
    ERROR_REDIRECT_ACTION("/Error", DispatchType.REDIRECT),
    LOGIN_FORWARD_ACTION("/Login/SignIn", DispatchType.FORWARD),
    LOGIN_REDIRECT_ACTION("/Login/SignIn", DispatchType.REDIRECT),
    TOPICS_FORWARD_ACTION("/Topic/List", DispatchType.FORWARD),
    TOPICS_REDIRECT_ACTION("/Topic/List", DispatchType.REDIRECT),
    INDEX_FORWARD_ACTION("/Home/Index", DispatchType.FORWARD),
    INDEX_REDIRECT_ACTION("/Home/Index", DispatchType.REDIRECT),
    STATS_FORWARD_ACTION("/Home/Stats", DispatchType.FORWARD),
    STATS_REDIRECT_ACTION("/Home/Stats", DispatchType.REDIRECT),
    STAND_STILL_PAGE("", DispatchType.STAND_STILL);

    final String path;
    final DispatchType dispatchType;

    public enum DispatchType{FORWARD, REDIRECT, STAND_STILL}

    WebPage(String path, DispatchType dispatchType) {
        this.path = path;
        this.dispatchType = dispatchType;
    }


    public String getPath() {
        return path;
    }

    public DispatchType getDispatchType() {
        return dispatchType;
    }
}
