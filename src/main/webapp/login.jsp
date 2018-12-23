<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="signin_messages"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}" >
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
</head>
<body>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/header.jsp"/>
    <div class="container body-content">
    <h2><fmt:message key="msg.login"/></h2>
    <div class="row">
        <div class="col-md-8">
            <section id="loginForm">
                <form action="" method="post" class="form-horizontal" role="form">
                <h4><fmt:message key="msg.description"/></h4>
                <hr />
                <%--Put error msh here--%>
                <div class="form-group">
                    <label for="login" class="col-md-2 control-label"><fmt:message key="msg.email"/></label>
                    <div class="col-md-10 input-group">
                        <span class="input-group-addon" id="sizing-addon2">@</span>
                        <input type="email" class="col-md-2 form-control" name="login" id="login" required>
                        <%--Error msg here--%>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-md-2 control-label"><fmt:message key="msg.password"/></label>
                        <div class="col-md-10 input-group">
                            <span class="input-group-addon" id="sizing-addon2">#</span>
                            <input type="password" id="password" name="password" class="col-md-2 form-control" required>
                            <%--Error msg here--%>
                        </div>
                </div>
                <p class="text-danger">${requestScope.error}</p>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <input type="submit" value="<fmt:message key="msg.login"/>" class="btn btn-default" />
                    </div>
                </div>
                <p>
                    <fmt:message key="msg.message"/> <a href="${pageContext.request.contextPath}/Login/SignUp"><fmt:message key="msg.register"/></a>
                </p>
                </form>
            </section>
        </div>
    </div>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
    </div>
</body>
</html>
