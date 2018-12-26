<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="topics_messages"/>
<!DOCTYPE html>
<html
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns:fn="http://java.sun.com/jsp/jstl/functions"
        xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebExam</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/header.jsp"/>

<div class="container body-content">
    <h1>Stats page</h1>
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Person</th>
            <th scope="col">Email</th>
            <th scope="col">Test</th>
            <th scope="col">Score</th>
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
                <td>${stat.score}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/Home/StatsDetails?id=${stat.id}">
                        Learn more
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
                        <a class="page-link" href="${pageContext.request.contextPath}/Home/Stats?page=${currentPage - 1}" tabindex="-1">Previous</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Previous</a>
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
                        <a class="page-link" href="${pageContext.request.contextPath}/Home/Stats?page=${currentPage + 1}">Next</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Next</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>


    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
</div>
</body>
</html>
