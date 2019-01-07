<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="tests_messages"/><!DOCTYPE html>
<t:page title="Tests">
    <h1><fmt:message key="msg.header"/></h1>
    <div class="list-group">
        <c:if test="${not empty error}">
            <div class="alert alert-warning">
                <strong>Warning!</strong> ${error}
            </div>
        </c:if>
        <c:choose>
            <c:when test="${fn:length(requestScope.tests) eq 0}">
                <div id="status-message" class="alert alert-info">
                    <p>There are no tests yet :(</p>
                    <a href="${pageContext.request.contextPath}/Test/Create?id=${topicId}">Create new</a>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="test" items="${requestScope.tests}">
                    <c:if test="${test.isPublic == true}">
                        <a class="list-group-item list-group-item-action" data-toggle="modal" data-target="#myModal${test.id}" style="cursor: pointer;">
                            <h3>${test.header}</h3>
                            <p>${test.description}</p>
                        </a>
                        <!-- Modal -->
                        <div id="myModal${test.id}" class="modal fade" role="dialog">
                            <div class="modal-dialog">
                                <!-- Modal content-->
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <h4 class="modal-title">${test.header}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <p>${test.description}</p>
                                    </div>
                                    <div class="modal-footer">
                                        <a href="${pageContext.request.contextPath}/Test/Exam?id=${test.id}" class="btn btn-success">Start test</a>
                                        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</t:page>
