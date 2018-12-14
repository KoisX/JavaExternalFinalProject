<div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
                <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        </button>
                </div>
                <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                                <%--TODO: change to /Home--%>
                                <li><a href="${pageContext.request.contextPath}/index.jsp">HOME</a></li>
                                <%--TODO: change to /Test--%>
                                <li><a href="${pageContext.request.contextPath}/Test">TESTS</a></li>
                                <%--TODO: change to /Home/Rules--%>
                                <li><a href="${pageContext.request.contextPath}/Rules">RULES</a></li>
                                <%--TODO: change to /About--%>
                                <li><a href="${pageContext.request.contextPath}/About">ABOUT</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                                <%--TODO: change to /Login--%>
                                <li><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                                <%--TODO: change to /Home--%>
                                <li><a href="${pageContext.request.contextPath}/login/Register"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                        </ul>
                </div>
        </div>
</div>