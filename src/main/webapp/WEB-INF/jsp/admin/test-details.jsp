<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<t:page title="Test details">
    <div class="well">
        <h1 align="center" style="text-transform: uppercase;">Test constructor</h1>
    </div>
    <c:if test="${test.isPublic == false}">
        <div id="status-message" class="alert alert-info">
            <p><strong>Attention!</strong> This test is <strong>NOT</strong> public!</p>
            <form method="post" action="${pageContext.request.contextPath}/Test/PublicStatus">
                <input type="hidden" name="id" value="${test.id}"/>
                <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                <button type="submit" class="btn btn-link"><i>Grant public access</i></button>
            </form>
            <p id="status-warn">Make sure that the test has at least one task and each task has at least one correct answer</p>
        </div>
    </c:if>
    <c:if test="${test.isPublic == true}">
        <div class="alert alert-info">
            <p><strong>Attention!</strong> This test is public!</p>
            <form method="post" action="${pageContext.request.contextPath}/Test/PrivateStatus">
                <input type="hidden" name="id" value="${test.id}"/>
                <span class="glyphicon glyphicon-lock" aria-hidden="true"></span>
                <button type="submit" data-toggle="tooltip" title="This test will no longer be publicly accessible" class="btn btn-link"><i>Grant private access</i></button>
            </form>
        </div>
    </c:if>
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
    <a href="${pageContext.request.contextPath}/Test/CreateTask?id=${test.id}">Create new</a>
    <c:forEach var="task" items="${requestScope.tasks}" varStatus="testIndex">
        <div class="list-group-item task-item" id="task_${task.id}">
            <fieldset class="form-group">
                <legend>${testIndex.index+1}. ${task.question} <span class="badge">${task.price} point(s)</span><span style="font-size: 15px;"><a href="${pageContext.request.contextPath}/Test/TaskDetails?id=${task.id}">  Details</a></span></legend>
                <c:choose>
                    <c:when test="${fn:length(task.correctAnswers) gt 1}">
                        <c:forEach var="answer" items="${task.possibleAnswers}">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <c:choose >
                                        <c:when test="${task.correctAnswers.contains(answer)}">
                                            <input type="checkbox" class="form-check-input" name="field_${task.id}"  value="${answer.value}" checked disabled>
                                            ${answer.value}
                                            <a style="font-weight: normal;" href="${pageContext.request.contextPath}/Test/EditAnswer?id=${answer.id}&correct=true">Edit</a>
                                            <form method="post" style="display: inline-block;" action="${pageContext.request.contextPath}/Test/DeleteAnswer?id=${answer.id}">
                                                <input style="display: inline-block;" type="submit" value="Delete" class="btn-link"/>
                                                <input type="hidden" name="testId" value="${test.id}"/>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" class="form-check-input" name="field_${task.id}"  value="${answer.value}" disabled>
                                            ${answer.value}
                                            <a style="font-weight: normal;" href="${pageContext.request.contextPath}/Test/EditAnswer?id=${answer.id}">Edit</a>
                                            <form method="post" style="display: inline-block;" action="${pageContext.request.contextPath}/Test/DeleteAnswer?id=${answer.id}">
                                                <input style="display: inline-block;" type="submit" value="Delete" class="btn-link"/>
                                                <input type="hidden" name="testId" value="${test.id}"/>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                            </div>
                        </c:forEach>
                        <a href="${pageContext.request.contextPath}/Test/AddAnswer?id=${task.id}&testId=${test.id}">Add possible or correct answer</a>
                    </c:when>
                    <c:when test="${fn:length(task.possibleAnswers) gt 1}">
                        <c:forEach var="answer" items="${task.possibleAnswers}">
                            <div class="form-check">
                                <label class="form-check-label">
                                    <c:choose>
                                        <c:when test="${task.correctAnswers.contains(answer)}">
                                            <input type="radio" class="form-check-input" name="field_${task.id}" value="${answer.value}" checked disabled>
                                            ${answer.value}
                                            <a style="font-weight: normal;" href="${pageContext.request.contextPath}/Test/EditAnswer?id=${answer.id}&correct=true">Edit</a>
                                            <form method="post" style="display: inline-block;" action="${pageContext.request.contextPath}/Test/DeleteAnswer?id=${answer.id}">
                                                <input style="display: inline-block;" type="submit" value="Delete" class="btn-link"/>
                                                <input type="hidden" name="testId" value="${test.id}"/>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" class="form-check-input" name="field_${task.id}" value="${answer.value}" disabled>
                                            ${answer.value}
                                            <a style="font-weight: normal;" href="${pageContext.request.contextPath}/Test/EditAnswer?id=${answer.id}">Edit</a>
                                            <form method="post" style="display: inline-block;" action="${pageContext.request.contextPath}/Test/DeleteAnswer?id=${answer.id}">
                                                <input style="display: inline-block;" type="submit" value="Delete" class="btn-link"/>
                                                <input type="hidden" name="testId" value="${test.id}"/>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                            </div>
                        </c:forEach>
                        <a href="${pageContext.request.contextPath}/Test/AddAnswer?id=${task.id}&testId=${test.id}">Add possible or correct answer</a>
                    </c:when>
                    <c:otherwise>
                        <div class="form-group">
                            <label for="exampleInput">Answer:</label>
                            <input type="text" class="form-control" id="exampleInput" name="field_${task.id}" placeholder="${task.possibleAnswers[0].value}" disabled>
                            <a style="font-weight: normal;" href="${pageContext.request.contextPath}/Test/EditAnswer?id=${task.possibleAnswers[0].id}&correct=true">Edit</a>
                            <form method="post" style="display: inline-block;" action="${pageContext.request.contextPath}/Test/DeleteAnswer?id=${task.possibleAnswers[0].id}">
                                <input style="display: inline-block;" type="submit" value="Delete" class="btn-link"/>
                                <input type="hidden" name="testId" value="${test.id}"/>
                            </form>
                        </div>
                        <a href="${pageContext.request.contextPath}/Test/AddAnswer?id=${task.id}&testId=${test.id}">Add possible or correct answer</a>
                    </c:otherwise>
                </c:choose>
            </fieldset>
        </div>
    </c:forEach>
    <c:if test="${status=='Error'}">
        <%--TODO: move script to header--%>
        <script src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js"></script>
        <script>
            $('#status-warn').css('color', 'red');
        </script>
    </c:if>
</t:page>
