<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="test_constructor_messages"/>
<t:page title="Test details">
    <div class="well">
        <h1 align="center" style="text-transform: uppercase;"><fmt:message key="msg.header"/></h1>
    </div>
    <c:if test="${test.isPublic == false}">
        <div id="status-message" class="alert alert-info">
            <p><strong><fmt:message key="msg.attention"/></strong> <fmt:message key="msg.private1"/> <strong><fmt:message key="msg.private2"/></strong> <fmt:message key="msg.private3"/></p>
            <form id="make-public" method="post" action="${pageContext.request.contextPath}/Test/PublicStatus">
                <input type="hidden" name="id" value="${test.id}"/>
                <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                <button type="submit" class="btn btn-link"><i><fmt:message key="msg.grantPublic"/></i></button>
            </form>
            <p id="status-warn"><fmt:message key="msg.grantPublicDetails"/></p>
        </div>
    </c:if>
    <c:if test="${test.isPublic == true}">
        <div class="alert alert-info">
            <p><strong><fmt:message key="msg.attention"/></strong> <fmt:message key="msg.grantPrivateDetails"/></p>
            <form method="post" action="${pageContext.request.contextPath}/Test/PrivateStatus">
                <input type="hidden" name="id" value="${test.id}"/>
                <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                <button type="submit" data-toggle="tooltip" title="This test will no longer be publicly accessible" class="btn btn-link"><i><fmt:message key="msg.grantPrivate"/></i></button>
            </form>
        </div>
    </c:if>
    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading"><h4 style="display: inline-block;"><b><fmt:message key="msg.testHeader"/></b></h4> <a href="${pageContext.request.contextPath}/Test/HeaderEdit?id=${test.id}"><fmt:message key="msg.edit"/></a></div>
            <div class="panel-body">${test.header}</div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading"><h4 style="display: inline-block;"><b><fmt:message key="msg.testDescription"/></b></h4> <a href="${pageContext.request.contextPath}/Test/DescriptionEdit?id=${test.id}"><fmt:message key="msg.edit"/></a></div>
            <div class="panel-body">${test.description}</div>
        </div>
    </div>
    <br/>
    <c:choose>
        <c:when test="${fn:length(requestScope.tasks) eq 0}">
            <div id="status-message" class="alert alert-info">
                <p><fmt:message key="msg.noTasks"/></p>
                <a href="${pageContext.request.contextPath}/Test/CreateTask?id=${test.id}"><strong><fmt:message key="msg.create"/></strong></a>

            </div>
        </c:when>
        <c:otherwise>
            <h2 align="center"><fmt:message key="msg.tasks"/></h2>
            <a href="${pageContext.request.contextPath}/Test/CreateTask?id=${test.id}"><fmt:message key="msg.create"/></a>
            <c:forEach var="task" items="${requestScope.tasks}" varStatus="testIndex">
                <div class="list-group-item task-item" id="task_${task.id}">
                    <fieldset class="form-group">
                        <legend>${testIndex.index+1}. ${task.question} <span class="badge">${task.price} <fmt:message key="msg.points"/></span><span style="font-size: 15px;"><a href="${pageContext.request.contextPath}/Test/TaskDetails?taskId=${task.id}&testId=${test.id}">  <fmt:message key="msg.details"/></a></span></legend>
                        <c:choose>
                            <c:when test="${fn:length(task.correctAnswers) gt 1}">
                                <c:forEach var="answer" items="${task.possibleAnswers}">
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <c:choose >
                                                <c:when test="${task.correctAnswers.contains(answer)}">
                                                    <input type="checkbox" class="form-check-input" name="field_${task.id}"  value="${answer.value}" checked disabled>
                                                    ${answer.value}
                                                    <a style="font-weight: normal;" href="${pageContext.request.contextPath}/Test/EditAnswer?id=${answer.id}&correct=true&taskId=${task.id}&testId=${test.id}"><fmt:message key="msg.edit"/></a>
                                                    <form method="post" style="display: inline-block;" action="${pageContext.request.contextPath}/Test/DeleteAnswer?id=${answer.id}">
                                                        <input style="display: inline-block;" type="submit" value="<fmt:message key="msg.delete"/>" class="btn-link"/>
                                                        <input type="hidden" name="testId" value="${test.id}"/>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="checkbox" class="form-check-input" name="field_${task.id}"  value="${answer.value}" disabled>
                                                    ${answer.value}
                                                    <a style="font-weight: normal;" href="${pageContext.request.contextPath}/Test/EditAnswer?id=${answer.id}&taskId=${task.id}&testId=${test.id}"><fmt:message key="msg.edit"/></a>
                                                    <form method="post" style="display: inline-block;" action="${pageContext.request.contextPath}/Test/DeleteAnswer?id=${answer.id}">
                                                        <input style="display: inline-block;" type="submit" value="<fmt:message key="msg.delete"/>" class="btn-link"/>
                                                        <input type="hidden" name="testId" value="${test.id}"/>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </label>
                                    </div>
                                </c:forEach>
                                <a href="${pageContext.request.contextPath}/Test/AddAnswer?id=${task.id}&testId=${test.id}"><fmt:message key="msg.addAnswer"/></a>
                            </c:when>
                            <c:when test="${fn:length(task.possibleAnswers) gt 1}">
                                <c:forEach var="answer" items="${task.possibleAnswers}">
                                    <div class="form-check">
                                        <label class="form-check-label">
                                            <c:choose>
                                                <c:when test="${task.correctAnswers.contains(answer)}">
                                                    <input type="radio" class="form-check-input" name="field_${task.id}" value="${answer.value}" checked disabled>
                                                    ${answer.value}
                                                    <a style="font-weight: normal;" href="${pageContext.request.contextPath}/Test/EditAnswer?id=${answer.id}&correct=true&testId=${test.id}&taskId=${task.id}"><fmt:message key="msg.edit"/></a>
                                                    <form method="post" style="display: inline-block;" action="${pageContext.request.contextPath}/Test/DeleteAnswer?id=${answer.id}">
                                                        <input style="display: inline-block;" type="submit" value="<fmt:message key="msg.delete"/>" class="btn-link"/>
                                                        <input type="hidden" name="testId" value="${test.id}"/>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="radio" class="form-check-input" name="field_${task.id}" value="${answer.value}" disabled>
                                                    ${answer.value}
                                                    <a style="font-weight: normal;" href="${pageContext.request.contextPath}/Test/EditAnswer?id=${answer.id}&testId=${test.id}&taskId=${task.id}"><fmt:message key="msg.edit"/></a>
                                                    <form method="post" style="display: inline-block;" action="${pageContext.request.contextPath}/Test/DeleteAnswer?id=${answer.id}">
                                                        <input style="display: inline-block;" type="submit" value="<fmt:message key="msg.delete"/>" class="btn-link"/>
                                                        <input type="hidden" name="testId" value="${test.id}"/>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </label>
                                    </div>
                                </c:forEach>
                                <a href="${pageContext.request.contextPath}/Test/AddAnswer?id=${task.id}&testId=${test.id}"><fmt:message key="msg.addAnswer"/></a>
                            </c:when>
                            <c:when test="${fn:length(task.correctAnswers) eq 1}">
                                <div class="form-group">
                                    <label for="exampleInput"><fmt:message key="msg.answer"/></label>
                                    <input type="text" class="form-control" id="exampleInput" name="field_${task.id}" placeholder="${task.possibleAnswers[0].value}" disabled>
                                    <a style="font-weight: normal;" href="${pageContext.request.contextPath}/Test/EditAnswer?id=${task.possibleAnswers[0].id}&correct=true&testId=${test.id}&taskId=${task.id}"><fmt:message key="msg.edit"/></a>
                                    <form method="post" style="display: inline-block;" action="${pageContext.request.contextPath}/Test/DeleteAnswer?id=${task.possibleAnswers[0].id}">
                                        <input style="display: inline-block;" type="submit" value="<fmt:message key="msg.delete"/>" class="btn-link"/>
                                        <input type="hidden" name="testId" value="${test.id}"/>
                                    </form>
                                </div>
                                <a href="${pageContext.request.contextPath}/Test/AddAnswer?id=${task.id}&testId=${test.id}"><fmt:message key="msg.addAnswer"/></a>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/Test/AddAnswer?id=${task.id}&testId=${test.id}"><fmt:message key="msg.addAnswer"/></a>
                            </c:otherwise>
                        </c:choose>
                    </fieldset>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <script src="${pageContext.request.contextPath}/scripts/grantPublicAccessValidation.js"></script>
</t:page>
