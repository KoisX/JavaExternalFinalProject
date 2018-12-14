<!DOCTYPE html>
<html
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns:fn="http://java.sun.com/jsp/jstl/functions"
        xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebExam</title>
    <link rel="stylesheet" type="text/css" href="styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="styles/bootstrap.css"/>
</head>
<body>
    <jsp:include page="jsp/layout/header.jsp"/>

    <div class="container body-content">
        <div class="jumbotron">
            <h1>WebExam</h1>
            <p class="lead">Check your knowledge here!</p>
            <p><a href="${pageContext.request.contextPath}/Test" class="btn btn-primary btn-lg">Go to tests page &raquo;</a></p>
        </div>

        <div class="row">
            <div class="col-md-4">
                <h2>Variety of tests</h2>
                <p>
                    Master your skills on a great number of tests, which cover such popular topics like
                    mathematics, programming, language learning and much-much more
                </p>
                <p><a class="btn btn-default" href="#">Learn more &raquo;</a></p>
            </div>
            <div class="col-md-4">
                <h2>Competitive spirit</h2>
                <p>Your test results will be put to the rank table, so that your high results will get cheers. Best participants will be listed publicly</p>
                <p><a class="btn btn-default" href="#">Learn more &raquo;</a></p>
            </div>
            <div class="col-md-4">
                <h2>Tests available 24/7</h2>
                <p>You can easily pass any desired test whenever you wish to to that. You can access all our tests from your favourite gadgets</p>
                <p><a class="btn btn-default" href="#">Learn more &raquo;</a></p>
            </div>
        </div>
        <hr />
        <jsp:include page="jsp/layout/footer.jsp"/>
    </div>
</body>
</html>
