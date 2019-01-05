<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<t:template>
    <jsp:attribute name="css">
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/site.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
    </jsp:attribute>
    <jsp:attribute name="header">
      <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/header.jsp"/>
      <script src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js"></script>
      <script src="${pageContext.request.contextPath}/scripts/bootstrap.js"></script>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</t:template>
