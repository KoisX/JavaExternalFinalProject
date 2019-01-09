<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="edit_answer_messages"/>
<t:page title="Answer edit">
    <h2><fmt:message key="msg.header"/></h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm">
                <form id="answer-form" action="${pageContext.request.contextPath}/Test/EditAnswer" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="value" class="col-md-2 control-label"><fmt:message key="msg.value"/></label>
                        <div class="col-md-10">
                            <input type="text" id="value" name="value" value="${answer.value}" class="col-md-2 form-control" required pattern=".{3,250}">
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${not empty correct}">
                            <div class="form-check">
                                <label class="form-check-label" for="isCorrect1"><fmt:message key="msg.correct"/></label>
                                <input type="checkbox" class="form-check-input" name="isCorrect" id="isCorrect1" checked>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-check">
                                <label class="form-check-label" for="isCorrect"><fmt:message key="msg.correct"/></label>
                                <input type="checkbox" class="form-check-input" name="isCorrect" id="isCorrect">
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <p class="text-danger">${requestScope.error}</p>
                    <input type="hidden" name="id" value="${answer.id}" />
                    <input type="hidden" name="testId" value="${testId}" />
                    <input type="hidden" name="taskId" value="${taskId}" />
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="<fmt:message key="msg.edit"/>" class="btn btn-default" />
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/answer-validation.js"></script>
</t:page>