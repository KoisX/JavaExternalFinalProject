<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="index_messages"/>
<t:page title="Home">
    <div class="jumbotron">
        <h1>WebExam</h1>
        <p class="lead"><fmt:message key="msg.header"/></p>
        <p><a href="${pageContext.request.contextPath}/Test/Topic" class="btn btn-primary btn-lg"><fmt:message key="msg.go-to-tests"/></a></p>
    </div>

    <div class="row">
        <div class="col-md-4">
            <h2><fmt:message key="msg.h1"/></h2>
            <p>
                <fmt:message key="msg.p1"/>
            </p>
            <p><a class="btn btn-default" href="${pageContext.request.contextPath}/Home/About"><fmt:message key="msg.learn-more"/></a></p>
        </div>
        <div class="col-md-4">
            <h2><fmt:message key="msg.h2"/></h2>
            <p><fmt:message key="msg.p2"/></p>
            <p><a class="btn btn-default" href="${pageContext.request.contextPath}/Home/About"><fmt:message key="msg.learn-more"/></a></p>
        </div>
        <div class="col-md-4">
            <h2><fmt:message key="msg.h3"/></h2>
            <p><fmt:message key="msg.p3"/></p>
            <p><a class="btn btn-default" href="${pageContext.request.contextPath}/Home/About"><fmt:message key="msg.learn-more"/></a></p>
        </div>
    </div>
    <hr />
</t:page>
