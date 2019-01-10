<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="error_page_messages"/>
<t:page title="Error">
    <h1><fmt:message key="msg.error"/></h1>
</t:page>
