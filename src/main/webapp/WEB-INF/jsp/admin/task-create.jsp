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
    <h2>Create new task</h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm">
                <form action="${pageContext.request.contextPath}/Topic/Create" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="question" class="col-md-2 control-label">Topic name</label>
                        <div class="col-md-10">
                            <input type="text" id="question" name="question" class="col-md-2 form-control" required pattern=".{3,350}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" title="Task price is a score, which user can get for answering correctly" class="col-md-2 control-label">Task price</label>
                        <div class="col-md-10">
                            <input type="number" id="price" name="price" class="col-md-2 form-control" required>
                        </div>
                    </div>
                    <p class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="Create" class="btn btn-default" />
                        </div>
                    </div>
                    <input type="hidden" name="id" value="${testId}"/>
                </form>
            </section>
        </div>
    </div>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
</div>
</body>
</html>
