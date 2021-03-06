<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="stats_messages"/>
<t:page title="Stats">
    <h1><fmt:message key="msg.stats"/></h1>
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="msg.person"/></th>
            <th scope="col"><fmt:message key="msg.email"/></th>
            <th scope="col"><fmt:message key="msg.test"/></th>
            <th scope="col"><fmt:message key="msg.date"/></th>
            <th scope="col"><fmt:message key="msg.score"/></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.stats}" var="stat" varStatus="number">
            <tr>
                <th scope="row">${(requestScope.recordsPerPage * (requestScope.currentPage - 1)) + number.index+1}</th>
                <td>${stat.user.name} ${stat.user.surname}</td>
                <td>${stat.user.email}</td>
                <td>${stat.test.header}</td>
                <td><fmt:formatDate value="${stat.timePassed}" pattern="dd/MM/yyyy HH:mm:ss"/></td>
                <td>${stat.score}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/Home/StatsDetails?id=${stat.id}&page=${currentPage}">
                        <fmt:message key="msg.learn-more"/>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav aria-label="...">
        <ul class="pagination">
            <c:choose>
                <c:when test="${requestScope.currentPage gt 1}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/Home/Stats?page=${currentPage - 1}" tabindex="-1"><fmt:message key="msg.previous"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1"><fmt:message key="msg.previous"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${requestScope.currentPage gt 1}">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Home/Stats?page=${currentPage - 1}">${requestScope.currentPage-1}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link disabled" href="#">&nbsp;</a></li>
                </c:otherwise>
            </c:choose>
            <li class="page-item active">
                <a class="page-link" href="#">${requestScope.currentPage} <span class="sr-only">(current)</span></a>
            </li>
            <c:choose>
                <c:when test="${requestScope.currentPage lt requestScope.pages}">
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/Home/Stats?page=${currentPage + 1}">${requestScope.currentPage+1}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link disabled" href="#">&nbsp;</a></li>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${requestScope.currentPage lt requestScope.pages}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/Home/Stats?page=${currentPage + 1}"><fmt:message key="msg.next"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1"><fmt:message key="msg.next"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</t:page>