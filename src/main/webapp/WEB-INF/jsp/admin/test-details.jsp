<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="signup_messages"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web Exam</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/header.jsp"/>
<div class="container body-content">
    <div class="well">
        <h1 align="center" style="text-transform: uppercase;">Test constructor</h1>
    </div>
    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading"><h4 style="display: inline-block;"><b>Test header</b></h4> <a href="${pageContext.request.contextPath}/Test/HeaderEdit?id=${test.id}">Edit</a></div>
            <div class="panel-body">${test.header}</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading"><h4 style="display: inline-block;"><b>Test description</b></h4> <a href="${pageContext.request.contextPath}/Test/DescriptionEdit?id=${test.id}">Edit</a></div>
            <div class="panel-body">${test.description}</div>
        </div>
    </div>
    <br/>
    <h2 align="center">Test tasks:</h2>
    <a href="#">Create new</a>
    <c:forEach var="task" items="${requestScope.tasks}" varStatus="testIndex">
        <div class="list-group-item task-item" id="task_${task.id}">
            <fieldset class="form-group">
                <legend>${testIndex.index+1}. ${task.question} <span class="badge">${task.price} point(s)</span></legend>
                <input type="hidden" name="id" value="${testId}"/>
                <c:choose>
                    <c:when test="${fn:length(task.correctAnswers) gt 1}">
                        <c:forEach var="answer" items="${task.possibleAnswers}">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <c:choose >
                                        <c:when test="${task.correctAnswers.contains(answer)}">
                                            <input type="checkbox" class="form-check-input" name="field_${task.id}"  value="${answer.value}" checked disabled>
                                            ${answer.value}
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="form-check-input" name="field_${task.id}"  value="${answer.value}" disabled>
                                            ${answer.value}
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:when test="${fn:length(task.possibleAnswers) gt 1}">
                        <c:forEach var="answer" items="${task.possibleAnswers}">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <c:choose>
                                        <c:when test="${task.correctAnswers.contains(answer)}">
                                            <input type="radio" class="form-check-input" name="field_${task.id}" value="${answer.value}" checked disabled>
                                            ${answer.value}
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" class="form-check-input" name="field_${task.id}" value="${answer.value}" disabled>
                                            ${answer.value}
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <label for="exampleInput">Answer:</label>
                            <input type="text" class="form-control" id="exampleInput" name="field_${task.id}" placeholder="${task.possibleAnswers[0].value}" disabled>
                        </div>
                    </c:otherwise>
                </c:choose>
            </fieldset>
        </div>
    </c:forEach>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
</div>
</body>
</html>
