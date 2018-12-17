<!DOCTYPE html>
<html
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns:fn="http://java.sun.com/jsp/jstl/functions" >
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
</head>
<body>
    <jsp:include page="jsp/layout/header.jsp"/>
    <div class="container body-content">
    <h2>Log in</h2>
    <div class="row">
        <div class="col-md-8">
            <section id="loginForm">
                <form action="" method="post" class="form-horizontal" role="form">
                <h4>Use a local account to log in.</h4>
                <hr />
                <%--Put error msh here--%>
                <div class="form-group">
                    <label for="login" class="col-md-2 control-label">Email:</label>
                    <div class="col-md-10">
                        <input type="email" class="col-md-2 form-control" name="login" id="login" required>
                        <%--Error msg here--%>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-md-2 control-label">Password:</label>
                        <div class="col-md-10">
                            <input type="password" id="password" name="password" class="col-md-2 form-control" required>
                            <%--Error msg here--%>
                        </div>
                </div>
                <p class="text-danger">${requestScope.error}</p>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <input type="submit" value="Log in" class="btn btn-default" />
                    </div>
                </div>
                <p>
                    Don't have an account? <a href="#">Register as a new user</a>
                </p>
                </form>
            </section>
        </div>
    </div>
    <jsp:include page="jsp/layout/footer.jsp"/>
    </div>
</body>
</html>
