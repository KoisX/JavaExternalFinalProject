<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="../layout/header.jsp"/>
<div class="container body-content">
    <h1>Choose the desired test:</h1>
    <div class="list-group">
        <c:forEach var="test" items="${requestScope.tests}">
            <a href="${pageContext.request.contextPath}/Test/Details?id=${test.id}" class="list-group-item list-group-item-action">
                <h3>${test.header}</h3>
                <p>${test.description}</p>
            </a>
        </c:forEach>
    </div>

    <jsp:include page="../layout/footer.jsp"/>
</div>
</body>
</html>
