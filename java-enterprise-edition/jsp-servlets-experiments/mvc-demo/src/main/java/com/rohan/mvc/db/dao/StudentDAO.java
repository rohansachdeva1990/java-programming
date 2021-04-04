package com.rohan.mvc.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.rohan.mvc.model.Student;

public class StudentDAO {

    private DataSource dataSource;

    public StudentDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {

            // Step 1: Get a connection
            myConn = dataSource.getConnection();

            // Step 2: Cretae sql statement
            String sql = "select * from student order by last_name";
            myStmt = myConn.createStatement();

            // Step 3: Execute SQL query
            myRs = myStmt.executeQuery(sql);

            // Step 4: Process the result
            while (myRs.next()) {

                // Retrieve data from result set row
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");

                // create new student object
                Student student = new Student(id, firstName, lastName, email);

                // add it to the list of students
                students.add(student);
            }
        }
        catch (Exception exec) {
            exec.printStackTrace();
        }
        finally {
            close(myRs, myStmt, myConn);
        }
        return students;
    }

    private void close(ResultSet myRs, Statement myStmt, Connection myConn) {
        try {
            if (null != myRs) {
                myRs.close();
            }

            if (null != myStmt) {
                myStmt.close();
            }

            if (null != myConn) {
                myConn.close(); // Makes the connection available for next use
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student theStudent) {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "insert into student (first_name, last_name, email) " + "values(?,?,?)";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theStudent.getFirstName());
            myStmt.setString(2, theStudent.getLastName());
            myStmt.setString(3, theStudent.getEmail());

            myStmt.execute();
        }
        catch (Exception exec) {
            exec.printStackTrace();
        }
        finally {
            close(null, myStmt, myConn);
        }
    }

    public Student getStudent(String theStudentId) throws Exception {

        Student theStudent = null;

        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {

            int studentId = Integer.parseInt(theStudentId);

            myConn = dataSource.getConnection();

            String sql = "select * from student where id=?";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, studentId);
            myRs = myStmt.executeQuery();

            // Step 4: Process the result
            if (myRs.next()) {

                // Retrieve data from result set row
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");

                // create new student object
                theStudent = new Student(studentId, firstName, lastName, email);
            }
            else {
                throw new Exception("Could not find student id: " + theStudentId);
            }
            return theStudent;
        }
        finally {
            close(myRs, myStmt, myConn);
        }
    }

    public void udpdateStudent(Student theStudent) {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "update student set first_name=?, last_name=?, email=? where id = ?";

            myStmt = myConn.prepareStatement(sql);

            myStmt.setString(1, theStudent.getFirstName());
            myStmt.setString(2, theStudent.getLastName());
            myStmt.setString(3, theStudent.getEmail());
            myStmt.setInt(4, theStudent.getId());

            myStmt.execute();
        }
        catch (SQLException sqlexc) {
            sqlexc.printStackTrace();
        }
        catch (Exception exec) {
            exec.printStackTrace();
        }
        finally {
            close(null, myStmt, myConn);
        }
    }

    public void deleteStudent(int id) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = dataSource.getConnection();
            String sql = "delete from student where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, id);
            myStmt.execute();
        }
        finally {
            close(null, myStmt, myConn);
        }
    }
}
