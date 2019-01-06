<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="signup_messages"/>
<t:page title="Sign up">
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
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="surname" class="col-md-2 control-label"><fmt:message key="msg.surname"/></label>
                        <div class="col-md-10">
                            <input type="text" id="surname" name="surname" class="col-md-2 form-control" required pattern=".{3,50}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-md-2 control-label"><fmt:message key="msg.email"/></label>
                        <div class="col-md-10">
                            <input type="email" id="email" name="login" class="col-md-2 form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-2 control-label"><fmt:message key="msg.password"/></label>
                        <div class="col-md-10">
                            <input type="password" name="password" id="password" class="col-md-2 form-control" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-2 control-label"><fmt:message key="msg.confirm"/></label>
                        <div class="col-md-10">
                            <input type="password" name="password-confirm" id="password-confirm" class="col-md-2 form-control" required>
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
</t:page>