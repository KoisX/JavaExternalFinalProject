<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate value="${date}" pattern="yyyy" var="currentYear" />
<footer>
    <p>&copy; <c:out value="${currentYear}"/> - Igor Konobas</p>
</footer>
<script src="../scripts/jquery-1.10.2.js" defer></script>
<script src="../scripts/bootstrap.js" defer></script>