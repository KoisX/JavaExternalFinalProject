        <%@ page import="com.javacourse.user.role.Role" %>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <li><a href="${pageContext.request.contextPath}/Home/Index">HOME</a></li>
                                <li><a href="${pageContext.request.contextPath}/Test/Topic">TESTS</a></li>
                                <li><a href="${pageContext.request.contextPath}/Home/Rules">RULES</a></li>
                                <li><a href="${pageContext.request.contextPath}/Home/About">ABOUT</a></li>
                                <c:if test="${sessionScope.role == ADMIN}">
                                        <li><a href="${pageContext.request.contextPath}/Home/Stats">STATS</a></li>
                                </c:if>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                                <c:choose>
                                        <c:when test="${not empty sessionScope.login && not empty sessionScope.password}">
                                                <li><a href="${pageContext.request.contextPath}/Login/Logout"><span class="glyphicon glyphicon-user"></span> Logout</a></li>
                                        </c:when>
                                        <c:otherwise>
                                                <li><a href="${pageContext.request.contextPath}/Login/SignUp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                                                <li><a href="${pageContext.request.contextPath}/Login/SignIn"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                                        </c:otherwise>
                                </c:choose>
                                
                        </ul>
                </div>
        </div>
</div>