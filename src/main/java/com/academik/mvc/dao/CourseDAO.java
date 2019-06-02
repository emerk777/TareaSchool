package com.academik.mvc.dao;

import com.academik.mvc.model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Emer
 */
public class CourseDAO implements GeneralDAO<Course>{

    @Override
    public List<Course> queryAll() {
               List<Course> temp = new ArrayList<>();
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            Statement stmnt = conn.createStatement();
            ResultSet result = stmnt.executeQuery("SELECT code, name, description, credits FROM course");
            while (result.next()) {
                Course s = new Course();
                s.setCode(result.getLong("code"));
                s.setName(result.getString("name"));
                s.setDescription(result.getString("description"));
                s.setCredits(result.getLong("credits"));
                temp.add(s);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    @Override
    public Course findById(long id) {
        Course s = null;
        try {
            Connection conn = CONN_WRAPPER.getConnection();           
            PreparedStatement stmnt = conn.prepareStatement("SELECT code, name, description, credits FROM course WHERE code = ?");
            stmnt.setLong(1, id);

            ResultSet result = stmnt.executeQuery();
            if (result.next()) {
                s = new Course();
                s.setCode(result.getLong("code"));
                s.setName(result.getString("name"));
                s.setDescription(result.getString("description"));
                s.setCredits(result.getLong("credits"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        return s;
    }

    @Override
    public void create(Course element) {
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(
                "INSERT INTO course"
              + " (name, description, credits)"
              + " VALUES (?, ?, ?)"
            );
            stmnt.setString(1, element.getName());
            stmnt.setString(2, element.getDescription());
            stmnt.setLong(3, element.getCredits());
            stmnt.execute();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void edit(long id, Course edited) {
        
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            PreparedStatement stmnt = conn.prepareStatement(
                "UPDATE course SET "
              + " name = ?,"
              + " description = ?,"
              + " credits = ?"
              + " WHERE code = ?"
            );
            stmnt.setString(1, edited.getName());
            stmnt.setString(2, edited.getDescription());
            stmnt.setLong(3, edited.getCredits());

            stmnt.setLong(4, id);
            stmnt.execute();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void delete(long id) {
        try {
            Connection conn = CONN_WRAPPER.getConnection();
            
            PreparedStatement stmnt = conn.prepareStatement(
                    "DELETE FROM course WHERE code = ?"
            );
            stmnt.setLong(1, id);
            stmnt.executeUpdate();
        } catch(ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    
}
