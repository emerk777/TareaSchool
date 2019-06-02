<%@page import="com.academik.mvc.utils.TimeUtils" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Edit Courses"/>
</jsp:include>

<jsp:useBean 
    id="single_course" 
    scope="request" 
    class="com.academik.mvc.model.Course"/>

<h1>Edit Courses</h1>
<form method="POST">
    <input type="hidden" name="_method" value="PUT"/>
    <input type="hidden" name="code" value="${single_course.code}"/>
    <div class="form-group">
        <label for="c_name">Name</label>
        <input class="form-control" type="text" name="c_name" value="${single_course.name}"/>
    </div>
    <div class="form-group">
        <label for="c_description">Description</label>
        <input class="form-control" type="text" name="c_description" value="${single_course.description}"/>
    </div>
    <div class="form-group">
        <label for="c_credits">Credits</label>
        <input class="form-control" type="number" name="c_credits" value="${single_course.credits}"/>
    </div>
    <input class="btn btn-primary" type="submit" value="Guardar"/>
</form>

<%@include file="../templates/footer.jsp" %>