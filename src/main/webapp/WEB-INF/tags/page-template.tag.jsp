<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template>
    <jsp:attribute name="css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/site.css"/>
    </jsp:attribute>
    <jsp:attribute name="header">
      <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <main roleEntity="main" class="container pt-3">
            <jsp:doBody/>
        </main>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-slim.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </jsp:body>
</t:template>
