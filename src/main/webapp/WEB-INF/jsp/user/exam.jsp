<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="exam_messages"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebExam</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/site.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/bootstrap.css"/>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/header.jsp"/>
<form method="post" action="${pageContext.request.contextPath}/Test/Revise">
<div class="container body-content">
    <h1>Choose correct answer for tests:</h1>
    <div class="list-group">
        <c:forEach var="task" items="${requestScope.tasks}">
            <div class="list-group-item">
                <fieldset class="form-group">
                    <legend>${task.question}</legend>
                   <c:choose>
                       <c:when test="${fn:length(task.correctAnswers) gt 1}">
                           <c:forEach var="answer" items="${task.possibleAnswers}">
                               <div class="form-check">
                                   <label class="form-check-label">
                                       <input type="checkbox" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="option1">
                                           ${answer.value}
                                   </label>
                               </div>
                           </c:forEach>
                       </c:when>
                       <c:when test="${fn:length(task.possibleAnswers) gt 1}">
                           <c:forEach var="answer" items="${task.possibleAnswers}">
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="optionsRadios" id="optionsRadios1" value="option1">
                                        ${answer.value}
                                    </label>
                                </div>
                            </c:forEach>
                       </c:when>
                       <c:otherwise>
                           <div class="form-group">
                               <label for="exampleInput">Answer:</label>
                               <input type="text" class="form-control" id="exampleInput" placeholder="Your answer">
                           </div>
                       </c:otherwise>
                   </c:choose>
                </fieldset>
            </div>
        </c:forEach>
        <c:if test="${not empty requestScope.tasks}">
            <button type="submit" class="btn btn-primary">Submit</button>
        </c:if>
    </div>

    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/layout/footer.jsp"/>
</div>
</form>
</body>
</html>
