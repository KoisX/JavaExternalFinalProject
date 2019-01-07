<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:page title="Stats detail">
    <h2>More info about stats</h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm" style="position: relative;">
                <form id="statsForm" action="${pageContext.request.contextPath}/Home/Stats" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="name" class="col-md-2 control-label">Person</label>
                        <div class="col-md-10">
                            <input type="text" id="name" name="name" class="col-md-2 form-control" readonly value="${stat.user.name} ${stat.user.surname}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-md-2 control-label">Email</label>
                        <div class="col-md-10">
                            <input type="text" id="email" name="email" class="col-md-2 form-control" readonly value="${stat.user.email}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="test" class="col-md-2 control-label">Test</label>
                        <div class="col-md-10">
                            <input type="text" id="test" name="test" class="col-md-2 form-control" readonly value="${stat.test.header}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="time" class="col-md-2 control-label">Time</label>
                        <div class="col-md-10">
                            <input type="text" id="time" name="time" class="col-md-2 form-control" readonly value="<fmt:formatDate value="${stat.timePassed}" pattern="dd/MM/yyyy HH:mm:ss"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="score" class="col-md-2 control-label">Score</label>
                        <div class="col-md-10">
                                <input type="number" id="score" name="score" class="col-md-2 form-control" value="${stat.score}">
                        </div>
                    </div>
                    <input type="hidden" name="id" value="${stat.id}"/>
                    <input type="hidden" name="command" value="edit"/>
                    <p id="error-msg" class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <button type="submit" name="command" value="edit" class="btn btn-success">Save changes</button>
                        </div>
                    </div>
                </form>
                <form action="${pageContext.request.contextPath}/Home/Stats" method="post" class="form-horizontal inline-form-btn" role="form">
                    <input type="hidden" name="command" value="delete"/>
                    <input type="hidden" name="id" value="${stat.id}"/>
                    <input type="submit" value="Delete" class="btn btn-danger"/>
                </form>
            </section>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/stats-validation.js"></script>
</t:page>