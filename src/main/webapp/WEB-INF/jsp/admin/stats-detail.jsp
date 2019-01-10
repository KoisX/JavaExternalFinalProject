<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="stats-details"/>
<t:page title="Stats detail">
    <h2><fmt:message key="msg.header"/></h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm" style="position: relative;">
                <form id="statsForm" action="${pageContext.request.contextPath}/Home/Stats" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="name" class="col-md-2 control-label"><fmt:message key="msg.person"/></label>
                        <div class="col-md-10">
                            <input type="text" id="name" name="name" class="col-md-2 form-control" readonly value="${stat.user.name} ${stat.user.surname}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-md-2 control-label"><fmt:message key="msg.email"/></label>
                        <div class="col-md-10">
                            <input type="text" id="email" name="email" class="col-md-2 form-control" readonly value="${stat.user.email}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="test" class="col-md-2 control-label"><fmt:message key="msg.test"/></label>
                        <div class="col-md-10">
                            <input type="text" id="test" name="test" class="col-md-2 form-control" readonly value="${stat.test.header}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="time" class="col-md-2 control-label"><fmt:message key="msg.date"/></label>
                        <div class="col-md-10">
                            <input type="text" id="time" name="time" class="col-md-2 form-control" readonly value="<fmt:formatDate value="${stat.timePassed}" pattern="dd/MM/yyyy HH:mm:ss"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="score" class="col-md-2 control-label"><fmt:message key="msg.score"/></label>
                        <div class="col-md-10">
                                <input type="number" id="score" name="score" class="col-md-2 form-control" value="${stat.score}">
                        </div>
                    </div>
                    <input type="hidden" name="id" value="${stat.id}"/>
                    <input type="hidden" name="command" value="edit"/>
                    <p id="error-msg" class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <button type="submit" name="command" value="edit" class="btn btn-success"><fmt:message key="msg.save"/></button>
                        </div>
                    </div>
                </form>
                <form action="${pageContext.request.contextPath}/Home/Stats" method="post" class="form-horizontal inline-form-btn" role="form">
                    <input type="hidden" name="command" value="delete"/>
                    <input type="hidden" name="id" value="${stat.id}"/>
                    <input type="hidden" name="page" value="${requestScope.page}"/>
                    <input type="submit" value="<fmt:message key="msg.delete"/>" class="btn btn-danger"/>
                </form>
            </section>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/stats-validation.js"></script>
</t:page>