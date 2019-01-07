package com.javacourse.shared;

/**
 * Represents a wrapper for a path,
 * which the request will be forwarded or redirected to
 */

public class WebPage{

    private String queryString;
    private WebPageBase base;
    private DispatchType dispatchType;

    /**
     * Default page, which makes WebPageRequestDispatcher leave user at the current page
     * without redirecting. It does come into handy when working with AJAX.
     * This object is immutable, thus there is really no use in creating multiple copies of it
     */
    public static final WebPage STAND_STILL_PAGE = new WebPage(WebPage.WebPageBase.STAND_STILL_PAGE).setDispatchType(WebPage.DispatchType.STAND_STILL);

    public WebPage(WebPageBase baseUrl) {
        base = baseUrl;
        queryString = "";
        dispatchType = DispatchType.FORWARD;
    }

    public String getQueryString() {
        return queryString;
    }

    public WebPage setQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public String getPath() {
        return base.getPath() + queryString;
    }

    public DispatchType getDispatchType() {
        return dispatchType;
    }

    public WebPage setDispatchType(DispatchType dispatchType) {
        this.dispatchType = dispatchType;
        return this;
    }

    public enum DispatchType{FORWARD, REDIRECT, STAND_STILL}

    public enum WebPageBase {
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
        TEST_ADMIN_ADD_PAGE("/WEB-INF/jsp/admin/test-create.jsp"),
        TEST_ADMIN_DETAILS_PAGE("/WEB-INF/jsp/admin/test-details.jsp"),
        TEST_ADMIN_EDIT_HEADER("/WEB-INF/jsp/admin/test-header-edit.jsp"),
        TEST_ADMIN_EDIT_DESCRIPTION("/WEB-INF/jsp/admin/test-description-edit.jsp"),
        TASK_ADMIN_CREATE_PAGE("/WEB-INF/jsp/admin/task-create.jsp"),
        TASK_ADMIN_EDIT_PAGE("/WEB-INF/jsp/admin/task-edit.jsp"),
        ANSWER_ADMIN_ADD_PAGE("/WEB-INF/jsp/admin/answer-create.jsp"),
        ANSWER_ADMIN_EDIT_PAGE("/WEB-INF/jsp/admin/answer-edit.jsp"),
        ERROR_ACTION("/Error"),
        LOGIN_ACTION("/Login/SignIn"),
        TOPICS_ACTION("/Topic/List"),
        TESTS_ACTION("/Test/Tests"),
        INDEX_ACTION("/Home/Index"),
        STATS_ACTION("/Home/Stats"),
        TEST_ADMIN_ADD_ACTION("/Test/Create"),
        TEST_ADMIN_DETAILS_ACTION("/Test/Details"),
        STAND_STILL_PAGE("");

        final String path;

        WebPageBase(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

    }

    @Override
    public String toString() {
        return getPath();
    }
}
