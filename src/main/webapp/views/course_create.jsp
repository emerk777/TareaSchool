<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Create Course"/>
</jsp:include>

    <h1>New Course</h1>
    <form method="POST">
        <div class="form-group">
            <label for="c_name">Name</label>
            <input class="form-control" type="text" name="c_name"/>
        </div>
        <div class="form-group">
            <label for="c_Description">Description</label>
            <input class="form-control" type="text" name="c_Description"/>
        </div>
        <div class="form-group">
            <label for="c_credits">Credits</label>
            <input class="form-control" type="text" name="c_credits"/>
        </div>

        <input class="btn btn-primary" type="submit" value="Create"/>
    </form>

<%@include file="../templates/footer.jsp" %>