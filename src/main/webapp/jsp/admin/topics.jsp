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
<jsp:include page="${pageContext.request.contextPath}/jsp/layout/header.jsp"/>
<div class="container body-content">
    <h1><fmt:message key="msg.header"/></h1>
    <div class="list-group">
        <c:forEach var="topic" items="${requestScope.topics}">
            <a href="${pageContext.request.contextPath}/Test/Tests?id=${topic.id}" class="list-group-item list-group-item-action">
                ${topic.name}
            </a>
        </c:forEach>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</div>
</body>
</html>
