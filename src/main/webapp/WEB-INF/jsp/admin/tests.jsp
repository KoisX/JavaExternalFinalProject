<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="tests_messages"/><!DOCTYPE html>
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
    <h1><fmt:message key="msg.header"/></h1>
    <div class="list-group">
        <c:if test="${not empty error}">
            <div class="alert alert-warning">
                <strong>Warning!</strong> ${error}
            </div>
        </c:if>
        <a href="${pageContext.request.contextPath}/Test/Create?id=${topicId}">Create new</a>
        <c:forEach var="test" items="${requestScope.tests}">
            <a class="list-group-item list-group-item-action" data-toggle="modal" data-target="#myModal${test.id}" style="cursor: pointer;">
                <h3>
                    ${test.header}
                    <c:if test="${test.isPublic == false}">
                        <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                    </c:if>
                </h3>
                <p>${test.description}</p>
            </a>
            <!-- Modal -->
            <div id="myModal${test.id}" class="modal fade" role="dialog">
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
                            <div class="btn-group pull-left" role="group">
                                <form method="post" action="${pageContext.request.contextPath}/Test/Delete" style="display: inline-block;">
                                    <input type="hidden" name="command" value="delete"/>
                                    <input name="id" type="hidden" value="${test.id}"/>
                                    <input name="topicId" type="hidden" value="${topicId}"/>
                                    <input type="submit" value="Delete" class="btn btn-danger">
                                </form>
                                <div style="display: inline-block;">
                                    <a href="${pageContext.request.contextPath}/Test/Details?id=${test.id}" class="btn btn-info">Edit test</a>
                                </div>
                            </div>

                            <a href="${pageContext.request.contextPath}/Test/Exam?id=${test.id}" class="btn btn-success">Start test</a>
                            <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
</div>
</body>
</html>
