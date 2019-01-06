<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:page title="Edit topic">
    <h2>Edit topic</h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm">
                <form action="${pageContext.request.contextPath}/Topic/Edit" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="name" class="col-md-2 control-label">Topic name</label>
                        <div class="col-md-10">
                            <input type="text" id="name" name="name" class="col-md-2 form-control" required pattern=".{3,50}" value="${topic.name}">
                        </div>
                    </div>
                    <input type="hidden" name="command" value="edit"/>
                    <input type="hidden" name="id" value="${topic.id}"/>
                    <p class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="Edit" class="btn btn-default" />
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</t:page>