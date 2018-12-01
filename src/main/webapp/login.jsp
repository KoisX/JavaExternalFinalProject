<!DOCTYPE html>
<html
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns:fn="http://java.sun.com/jsp/jstl/functions" >
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in</title>
    <link rel="stylesheet" type="text/css" href="styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="styles/bootstrap.css"/>
</head>
<body>
    <jsp:include page="layout/header.jsp"/>
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
                    <div class="col-md-offset-2 col-md-10">
                        <div class="checkbox">
                            <input type="checkbox" id="remember_me">
                            <label for="remember_me">Remember me</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <input type="submit" value="Log in" class="btn btn-default" />
                    </div>
                </div>
                <p>
                    <a href="#">Register as a new user</a>
                </p>
                </form>
            </section>
        </div>
    </div>
    <jsp:include page="layout/footer.jsp"/>
    </div>
</body>
</html>
