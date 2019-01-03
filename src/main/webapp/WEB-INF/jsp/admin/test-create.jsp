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
    <title>Web Exam</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/header.jsp"/>
<div class="container body-content">
    <h2>Create new test</h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm">
                <form action="${pageContext.request.contextPath}/Test/Create" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="header" class="col-md-2 control-label">Test header</label>
                        <div class="col-md-10">
                            <input type="text" id="header" name="header" class="col-md-2 form-control" required pattern=".{3,100}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-2 control-label">Test description</label>
                        <div class="col-md-10">
                            <textarea  id="description" name="description" class="col-md-2 form-control" required placeholder="Enter test description...">
                            </textarea>
                        </div>
                    </div>
                    <input type="hidden" name="command" value="create"/>
                    <p class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="Create" class="btn btn-default" />
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
