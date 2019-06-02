<%@page import="com.academik.mvc.model.Course" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="../templates/header.jsp">
    <jsp:param name="custom-title" value="Academik | Courses"/>
</jsp:include>

<jsp:useBean 
    id="list_of_courses"
    scope="request" 
    class="List<Course>" />

<table class="table">
    <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Credits</th>
        </tr>
    </thead>
    <tbody>
        <% for(Course s : list_of_courses) { %> 
        <tr>
            <td><%= s.getName() %></td>
            <td><%= s.getDescription() %></td>
            <td><%= s.getCredits() %></td>
            <td>
                <a class="btn btn-primary" href="courses/edit?id=<%= s.getCode() %>">Editar</a>
                <a class="btn btn-primary" href="courses/view?id=<%= s.getCode() %>">Ver</a>
            </td>
        </tr>
        <%}%>
    </tbody>
</table>

<%@include file="../templates/footer.jsp" %>
