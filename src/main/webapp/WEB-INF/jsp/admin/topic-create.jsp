<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="topics_create"/>
<t:page title="Create topic">
    <h2><fmt:message key="msg.header"/></h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm">
                <form action="${pageContext.request.contextPath}/Topic/Create" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="name" class="col-md-2 control-label"><fmt:message key="msg.name"/></label>
                        <div class="col-md-10">
                            <input type="text" id="name" name="name" class="col-md-2 form-control" required pattern=".{3,50}">
                        </div>
                    </div>
                    <input type="hidden" name="command" value="create"/>
                    <p class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="<fmt:message key="msg.create"/>" class="btn btn-default" />
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</t:page>