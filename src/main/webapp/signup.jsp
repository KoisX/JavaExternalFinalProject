<!DOCTYPE html>
<html
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns:fn="http://java.sun.com/jsp/jstl/functions" >
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign up</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
</head>
<body>
<jsp:include page="jsp/layout/header.jsp"/>
<div class="container body-content">
    <h2>Sign up</h2>
    <div class="row">
        <div class="col-md-8">
            <section id="loginForm">
                <form action="" method="post" class="form-horizontal" role="form">
                    <h4>Create a new account.</h4>
                    <hr />
                    <%--Put error msg here--%>
                    <div class="form-group">
                        <label for="email" class="col-md-2 control-label">Email:</label>
                        <div class="col-md-10">
                            <input type="email" id="email" class="col-md-2 form-control">
                            <%--Error msg here--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-2 control-label">Password:</label>
                        <div class="col-md-10">
                            <input type="password" id="password" class="col-md-2 form-control">
                            <%--Error msg here--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-2 control-label">Confirm password:</label>
                        <div class="col-md-10">
                            <input type="password" id="password" class="col-md-2 form-control">
                            <%--Error msg here--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="Sign up" class="btn btn-default" />
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
    <jsp:include page="jsp/layout/footer.jsp"/>
</div>
</body>
</html>
