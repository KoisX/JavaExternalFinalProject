<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="signup_messages"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign up</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/header.jsp"/>
<div class="container body-content">
    <h2><fmt:message key="msg.signup"/></h2>
    <div class="row">
        <div class="col-md-8">
            <section id="loginForm">
                <form action="${pageContext.request.contextPath}/Login/SignUp" method="post" class="form-horizontal" role="form">
                    <h4><fmt:message key="msg.create"/></h4>
                    <hr />
                    <div class="form-group">
                        <label for="name" class="col-md-2 control-label"><fmt:message key="msg.name"/></label>
                        <div class="col-md-10">
                            <input type="text" id="name" name="name" class="col-md-2 form-control" required pattern=".{3,50}">
                            <%--Error msg here--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="surname" class="col-md-2 control-label"><fmt:message key="msg.surname"/></label>
                        <div class="col-md-10">
                            <input type="text" id="surname" name="surname" class="col-md-2 form-control" required pattern=".{3,50}">
                            <%--Error msg here--%>
                        </div>
                    </div>
                    <%--Put error msg here--%>
                    <div class="form-group">
                        <label for="email" class="col-md-2 control-label"><fmt:message key="msg.email"/></label>
                        <div class="col-md-10">
                            <input type="text" id="email" name="login" class="col-md-2 form-control" required>
                            <%--Error msg here--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-2 control-label"><fmt:message key="msg.password"/></label>
                        <div class="col-md-10">
                            <input type="password" name="password" id="password" class="col-md-2 form-control" required>
                            <%--Error msg here--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-2 control-label"><fmt:message key="msg.confirm"/></label>
                        <div class="col-md-10">
                            <input type="password" name="password-confirm" id="password-confirm" class="col-md-2 form-control" required>
                            <%--Error msg here--%>
                        </div>
                    </div>
                    <p class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="<fmt:message key="msg.signup"/>" class="btn btn-default" />
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
</div>
</body>
</html>
