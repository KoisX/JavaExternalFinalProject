        <%@ page import="com.javacourse.user.role.Role" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <fmt:setLocale value="${sessionScope.lang}"/>
        <fmt:setBundle basename="header_messages"/>
        <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
                <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        </button>
                </div>
                <c:set var="ADMIN" value="<%=Role.ADMIN%>"/>
                <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                                <li><a href="${pageContext.request.contextPath}/Home/Index"><fmt:message key="msg.home"/></a></li>
                                <li><a href="${pageContext.request.contextPath}/Topic/List"><fmt:message key="msg.tests"/></a></li>
                                <li><a href="${pageContext.request.contextPath}/Home/Rules"><fmt:message key="msg.rules"/></a></li>
                                <li><a href="${pageContext.request.contextPath}/Home/About"><fmt:message key="msg.about"/></a></li>
                                <c:if test="${sessionScope.role == ADMIN}">
                                        <li><a href="${pageContext.request.contextPath}/Home/Stats"><fmt:message key="msg.stats"/></a></li>
                                </c:if>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                                <c:choose>
                                        <c:when test="${not empty sessionScope.login && not empty sessionScope.password}">
                                                <li><a href="${pageContext.request.contextPath}/Login/Logout"><span class="glyphicon glyphicon-user"></span> <fmt:message key="msg.logout"/></a></li>
                                        </c:when>
                                        <c:otherwise>
                                                <li><a href="${pageContext.request.contextPath}/Login/SignUp"><span class="glyphicon glyphicon-user"></span> <fmt:message key="msg.signup"/></a></li>
                                                <li><a href="${pageContext.request.contextPath}/Login/SignIn"><span class="glyphicon glyphicon-log-in"></span> <fmt:message key="msg.login"/></a></li>
                                        </c:otherwise>
                                </c:choose>
                </ul>
                </div>
        </div>
</div>