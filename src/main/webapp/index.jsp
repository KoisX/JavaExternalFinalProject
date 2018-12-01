<html
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns:fn="http://java.sun.com/jsp/jstl/functions" >
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebExam</title>
    <link rel="stylesheet" type="text/css" href="styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="styles/bootstrap.css"/>
</head>
<body>
    <jsp:include page="layout/header.jsp"/>

    <div class="container body-content">
        <div class="jumbotron">
            <h1>ASP.NET</h1>
            <p class="lead">ASP.NET is a free web framework for building great Web sites and Web applications using HTML, CSS and JavaScript.</p>
            <p><a href="https://asp.net" class="btn btn-primary btn-lg">Learn more &raquo;</a></p>
        </div>

        <div class="row">
            <div class="col-md-4">
                <h2>Getting started</h2>
                <p>
                    ASP.NET MVC gives you a powerful, patterns-based way to build dynamic websites that
                    enables a clean separation of concerns and gives you full control over markup
                    for enjoyable, agile development.
                </p>
                <p><a class="btn btn-default" href="https://go.microsoft.com/fwlink/?LinkId=301865">Learn more &raquo;</a></p>
            </div>
            <div class="col-md-4">
                <h2>Get more libraries</h2>
                <p>NuGet is a free Visual Studio extension that makes it easy to add, remove, and update libraries and tools in Visual Studio projects.</p>
                <p><a class="btn btn-default" href="https://go.microsoft.com/fwlink/?LinkId=301866">Learn more &raquo;</a></p>
            </div>
            <div class="col-md-4">
                <h2>Web Hosting</h2>
                <p>You can easily find a web hosting company that offers the right mix of features and price for your applications.</p>
                <p><a class="btn btn-default" href="https://go.microsoft.com/fwlink/?LinkId=301867">Learn more &raquo;</a></p>
            </div>
        </div>
        <hr />
        <footer>
            <p>&copy; @DateTime.Now.Year - My ASP.NET Application</p>
        </footer>
    </div>
    <script src="scripts/jquery-1.10.2.js" defer></script>
    <script src="scripts/bootstrap.js" defer></script>
</body>
</html>
