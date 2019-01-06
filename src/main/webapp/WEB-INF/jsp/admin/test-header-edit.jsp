<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:page title="Header edit">
    <h2>Edit test header</h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm">
                <form id="header-form" action="${pageContext.request.contextPath}/Test/HeaderEdit" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="header" class="col-md-2 control-label">Test header</label>
                        <div class="col-md-10">
                            <input type="text" id="header" name="header" class="col-md-2 form-control" required pattern=".{2,50}" value="${test.header}">
                        </div>
                    </div>
                    <input type="hidden" name="id" value="${test.id}"/>
                    <p id="error-msg" class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="Edit" class="btn btn-default" />
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/scripts/header-validation.js"></script>
</t:page>