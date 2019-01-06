<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<t:page title="Test create">
    <h2>Create new test</h2>
    <div class="row">
        <div class="col-md-8">
            <section id="createForm">
                <form action="${pageContext.request.contextPath}/Test/Create" id="test-create-form" method="post" class="form-horizontal" role="form">
                    <hr />
                    <div class="form-group">
                        <label for="header" class="col-md-2 control-label">Test header</label>
                        <div class="col-md-10">
                            <input type="text" id="header" name="header" class="col-md-2 form-control" required pattern=".{3,100}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-2 control-label">Test description</label>
                        <div class="col-md-10">
                            <textarea  id="description" name="description" class="col-md-2 form-control" required placeholder="Enter test description...">
                            </textarea>
                        </div>
                    </div>
                    <input type="hidden" name="command" value="create"/>
                    <input type="hidden" name="id" value="${id}"/>
                    <p class="text-danger">${requestScope.error}</p>
                    <div class="form-group">
                        <div class="col-md-offset-2 col-md-10">
                            <input type="submit" value="Create" class="btn btn-default" />
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</t:page>
