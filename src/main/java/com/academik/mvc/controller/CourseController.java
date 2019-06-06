package com.academik.mvc.controller;

import com.academik.mvc.dao.CourseDAO;
import com.academik.mvc.model.Course;
        

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Emer
 */
@WebServlet("/courses/*")
public class CourseController extends HttpServlet{
    
    CourseDAO dao = new CourseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String complement = req.getPathInfo();
        if(complement == null)
            complement = "";
        System.err.println(complement);
        String redirectPage;
        switch(complement) {
            case "/create":
                redirectPage = "course_create.jsp";
                break;
            case "/view":
                //Obtengo el parametro desde la URL
                long idToView = Long.parseLong(req.getParameter("id"));
                Course sToView = dao.findById(idToView);
                req.setAttribute("single_course", sToView);
                redirectPage = "course_view.jsp";
                break;
            case "/edit":
                long idToEdit = Long.parseLong(req.getParameter("id"));
                Course sToEdit = dao.findById(idToEdit);
                req.setAttribute("single_course", sToEdit);
                redirectPage = "course_edit.jsp";
                break;
            case "/list":
            case "/":
            case "":
                List<Course> allCourses = dao.queryAll();
                req.setAttribute("list_of_courses", allCourses);
                redirectPage = "course_list.jsp";
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/courses");
                return;
        }

        //Renderice este JSP
        RequestDispatcher rd = req.getRequestDispatcher(
                "/views/" + redirectPage
        );
        //Adelante con la renderización
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        if("PUT".equals(req.getParameter("_method"))){
            doPut(req, resp);
            return;
        }
        if("DELETE".equals(req.getParameter("_method"))){
            doDelete(req, resp);
            return;
        }
            
        System.out.println("Creating a new Course");
        //Variable vacia
        Course noob = new Course();
        
        //Valores para las propiedades que vienen desde el formulario HTML
        noob.setName(req.getParameter("c_name"));
        noob.setDescription(req.getParameter("c_description"));
        //String a = req.getParameter("c_credits");
        noob.setCredits(Long.parseLong(req.getParameter("c_credits"))); 
        
        //Utilizar el DAO para guardar la informacion en la base de datos
        dao.create(noob);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        System.out.println("Editing an student...");
        //Variable vacia
        Course edited = new Course();
        
        //Valores NUEVOS para las propiedades que vienen desde el formulario HTML
        edited.setName(req.getParameter("c_name"));
        edited.setDescription(req.getParameter("c_description"));
        edited.setCredits(Long.parseLong(req.getParameter("c_credits")));
        
        //Utilizar el DAO para guardar la informacion en la base de datos
        dao.edit(Integer.parseInt(req.getParameter("code")), edited);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("Deleting student...");
        long id = Long.parseLong(req.getParameter("code"));
        dao.delete(id);
        resp.sendRedirect(req.getContextPath() + "/courses");
    }    
    
}
