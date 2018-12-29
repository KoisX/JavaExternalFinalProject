<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="exam-results_messages"/>
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
    <h1>Exam results:</h1>
    <h3>Good work! Result: ${result}/${maximalResult}</h3>

    <c:forEach var="task" items="${requestScope.tasks}" varStatus="testIndex">
        <div class="list-group-item">
            <fieldset class="form-group">
                <legend>${testIndex.index+1}. ${task.question} <span class="badge">${task.price} point(s)</span></legend>
                <input type="hidden" name="id" value="${testId}"/>
                <c:choose>
                    <c:when test="${fn:length(task.correctAnswers) gt 1}">
                        <c:forEach var="answer" items="${task.possibleAnswers}">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="checkbox" class="form-check-input" name="field_${task.id}"  value="${answer.value}">
                                    ${answer.value}
                                </label>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:when test="${fn:length(task.possibleAnswers) gt 1}">
                        <c:forEach var="answer" items="${task.possibleAnswers}">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <input type="radio" class="form-check-input" name="field_${task.id}" value="${answer.value}" required>
                                    ${answer.value}
                                </label>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <label for="exampleInput">Answer:</label>
                            <input type="text" class="form-control" id="exampleInput" name="field_${task.id}" placeholder="Your answer" required>
                        </div>
                    </c:otherwise>
                </c:choose>
            </fieldset>
        </div>
    </c:forEach>

    <hr />
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
</div>
</body>
</html>
