<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="task_details_messages"/>
<t:page title="Task edit">
    <h2><fmt:message key="msg.header"/></h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm" style="position: relative;">
                <form id="task-form" action="${pageContext.request.contextPath}/Test/TaskDetails" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="question" class="col-md-2 control-label"><fmt:message key="msg.name"/></label>
                        <div class="col-md-10">
                            <input type="text" id="question" name="question" class="col-md-2 form-control" value="${task.question}" required pattern=".{3,350}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" title="Task price is a score, which user can get for answering correctly" class="col-md-2 control-label"><fmt:message key="msg.price"/></label>
                        <div class="col-md-10">
                            <input type="number" id="price" value="${task.price}" name="price" class="col-md-2 form-control" required>
                        </div>
                    </div>
                    <p id="error-msg" class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <button type="submit" name="command" value="edit" class="btn btn-success"><fmt:message key="msg.save"/></button>
                        </div>
                    </div>
                    <input type="hidden" name="command" value="edit"/>
                    <input type="hidden" name="testId" value="${testId}"/>
                    <input type="hidden" name="taskId" value="${task.id}"/>
                </form>
                <form action="${pageContext.request.contextPath}/Test/TaskDetails" method="post" class="form-horizontal inline-form-btn" role="form">
                    <input type="hidden" name="command" value="delete"/>
                    <input type="hidden" name="testId" value="${testId}"/>
                    <input type="hidden" name="taskId" value="${task.id}"/>
                    <input type="submit" value="<fmt:message key="msg.delete"/>" class="btn btn-danger"/>
                </form>
            </section>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/task-validation.js"></script>
</t:page>
