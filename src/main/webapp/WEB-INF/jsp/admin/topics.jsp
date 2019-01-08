<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="topics_messages"/>
<t:page title="Topics">
    <h1><fmt:message key="msg.header"/></h1>
    <div class="list-group">
        <c:if test="${not empty error}">
            <div class="alert alert-warning">
                <strong>Warning!</strong> ${error}
            </div>
        </c:if>
        <a href="${pageContext.request.contextPath}/Topic/Create"><fmt:message key="msg.create"/></a>
        <c:forEach var="topic" items="${requestScope.topics}">
            <a href="${pageContext.request.contextPath}/Test/Tests?id=${topic.id}" class="list-group-item list-group-item-action" style="padding-bottom: 20px;">
                    ${topic.name}
                <div class="btn-group pull-right" role="group" style="width: 200px;">
                    <form method="post" action="${pageContext.request.contextPath}/Topic/List" style="display: inline-block;">
                        <input type="hidden" name="command" value="delete"/>
                        <input name="id" type="hidden" value="${topic.id}"/>
                        <input type="submit" value="<fmt:message key="msg.delete"/>" class="btn btn-sm btn-danger">
                    </form>
                    <form method="post" action="${pageContext.request.contextPath}/Topic/Edit" style="display: inline-block;">
                        <input name="id" type="hidden" value="${topic.id}"/>
                        <input type="submit" value="<fmt:message key="msg.edit"/>" class="btn btn-sm btn-warning">
                    </form>
                </div>
            </a>
        </c:forEach>
    </div>
</t:page>