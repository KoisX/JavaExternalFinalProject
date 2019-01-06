<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:page title="Create task">
    <h2>Create new task</h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm">
                <form id="task-form" action="${pageContext.request.contextPath}/Test/CreateTask" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="question" class="col-md-2 control-label">Task name</label>
                        <div class="col-md-10">
                            <input type="text" id="question" name="question" class="col-md-2 form-control" required pattern=".{3,350}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" title="Task price is a score, which user can get for answering correctly" class="col-md-2 control-label">Task price</label>
                        <div class="col-md-10">
                            <input type="number" id="price" name="price" class="col-md-2 form-control" required>
                        </div>
                    </div>
                    <p id="error-msg" class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="Create" class="btn btn-default" />
                        </div>
                    </div>
                    <input type="hidden" name="id" value="${testId}"/>
                </form>
            </section>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/task-validation.js"></script>
</t:page>