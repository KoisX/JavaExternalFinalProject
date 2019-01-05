<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:template>
    <jsp:attribute name="css">
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>WebExam</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/site.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
    </jsp:attribute>
    <jsp:attribute name="header">
      <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/header.jsp"/>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <div class="container body-content">
            <jsp:doBody/>
        </div>
    </jsp:body>
</t:template>
