<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="exam_messages"/>
<t:page title="Exam">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/exam.css"/>
    <form method="post" id="exam-form" action="${pageContext.request.contextPath}/Test/Results">
        <h1 id="header"><fmt:message key="msg.header"/></h1>
        <div class="alert alert-success email-msg" id="email-msg">
            <strong><fmt:message key="msg.attention"/></strong> <fmt:message key="msg.email"/>
        </div>
        <div class="progress" id="progress" style="display: none;">
            <div class="progress-bar bg-success" id="progress-bar" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
        </div>
        <div class="list-group">
            <c:forEach var="task" items="${requestScope.tasks}" varStatus="testIndex">
                <div class="list-group-item task-item" id="task_${task.id}">
                    <fieldset class="form-group">
                        <legend>${testIndex.index+1}. ${task.question} <span class="badge">${task.price} <fmt:message key="msg.points"/></span></legend>
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
                                    <label for="exampleInput"><fmt:message key="msg.answer"/></label>
                                    <input type="text" class="form-control" id="exampleInput" name="field_${task.id}" placeholder="<fmt:message key="msg.placeholder"/>" required>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </fieldset>
                </div>
            </c:forEach>
            <c:if test="${not empty requestScope.tasks}">
                <button type="submit" id="checkBtn" class="btn btn-primary"><fmt:message key="msg.submit"/></button>
            </c:if>
        </div>
    </form>
    <script src="${pageContext.request.contextPath}/scripts/validateHtmlForm.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/exam-revision.js"></script>
</t:page>