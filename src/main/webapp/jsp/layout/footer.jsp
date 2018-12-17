<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate value="${date}" pattern="yyyy" var="currentYear" />
<footer>
    <p>&copy; <c:out value="${currentYear}"/> - Igor Konobas</p>
    <ul class="lang-list">
    <li class="lang-flag"><a href="?lang=ukr" title="UA"><img class="lang-pic" src="${pageContext.request.contextPath}/images/ukraine.jpg"/></a></li>
    <li class="lang-flag"><a href="?lang=en" title="ENG"><img class="lang-pic" src="${pageContext.request.contextPath}/images/england.png"/></a></li>
    </ul>
</footer>
<script src="${pageContext.request.contextPath}/scripts/jquery-1.10.2.js"></script>
<script src="${pageContext.request.contextPath}/scripts/bootstrap.js"></script>