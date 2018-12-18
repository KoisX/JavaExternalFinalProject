<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="tests_messages"/><!DOCTYPE html>
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
        <c:forEach var="test" items="${requestScope.tests}">
            <a class="list-group-item list-group-item-action" data-toggle="modal" data-target="#myModal" style="cursor: pointer;">
                <h3>${test.header}</h3>
                <p>${test.description}</p>
            </a>
            <!-- Modal -->
            <div id="myModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">${test.header}</h4>
                        </div>
                        <div class="modal-body">
                            <p>${test.description}</p>
                        </div>
                        <div class="modal-footer">
                            <a href="${pageContext.request.contextPath}/Test/Exam?id=${test.id}" class="btn btn-success">Start test</a>
                            <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/jsp/layout/footer.jsp"/>
</div>
</body>
</html>
