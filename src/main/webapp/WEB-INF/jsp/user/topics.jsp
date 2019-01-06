<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="topics_messages"/>
<t:page title="Topics">
    <h1><fmt:message key="msg.header"/></h1>
    <div class="list-group">
        <c:forEach var="topic" items="${requestScope.topics}">
            <a href="${pageContext.request.contextPath}/Test/Tests?id=${topic.id}" class="list-group-item list-group-item-action">
                    ${topic.name}
            </a>
        </c:forEach>
    </div>
</t:page>
