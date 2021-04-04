package com.rohan.mvc.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.rohan.mvc.db.dao.StudentDAO;
import com.rohan.mvc.model.Student;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JAVA EE Resource injection
    @Resource(name = "jdbc/web_student_tracker")
    private DataSource dataSource;

    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Create instance of database util
        studentDAO = new StudentDAO(dataSource);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // Read the "command" parameter
            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "LIST";
            }

            switch (theCommand) {
            case "LIST":
                listStudents(request, response);
                break;

            case "ADD":
                addStudent(request, response);
                break;

            case "LOAD":
                loadStudent(request, response);
                break;

            case "UPDATE":
                updateStudent(request, response);
                break;

            case "DELETE":
                deleteStudent(request, response);
                break;

            default:
                listStudents(request, response);
            }

        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Get students from DB
        List<Student> students = studentDAO.getStudents();

        // Add student to the request
        request.setAttribute("student_list", students);

        // Send to JSP page (View)
        RequestDispatcher dispactcher = request.getRequestDispatcher("/list-students-jstl.jsp");
        dispactcher.forward(request, response);

    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // Read the student from the form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        // Create a new student
        Student theStudent = new Student(firstName, lastName, email);

        // Add the student to the database
        studentDAO.addStudent(theStudent);

        // Send back to main page
        listStudents(request, response);
    }

    private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String theStudentId = request.getParameter("studentId");

        Student theStudent = studentDAO.getStudent(theStudentId);

        request.setAttribute("the_student", theStudent);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");

        dispatcher.forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("studentId"));

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student theStudent = new Student(id, firstName, lastName, email);

        // Add the student to the database
        studentDAO.udpdateStudent(theStudent);

        // Send back to main page
        listStudents(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int id = Integer.parseInt(request.getParameter("studentId"));

        studentDAO.deleteStudent(id);

        listStudents(request, response);
    }

}
