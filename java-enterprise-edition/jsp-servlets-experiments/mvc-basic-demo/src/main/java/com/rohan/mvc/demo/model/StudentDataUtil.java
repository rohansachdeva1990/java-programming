package com.rohan.mvc.demo.model;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {

    
    public static List<Student> getStudents(){
        
        // Create an empty list
        List<Student> students = new ArrayList<>();
        
        // Add sample data
        students.add(new Student("Rohan", "Sachdeva","rohan.sachdeva@dreamcompany.com"));
        students.add(new Student("Rajat", "Sachdeva","rajat.sachdeva@dreamcompany.com"));
        students.add(new Student("Becky", "Martin","becky.martin@dreamcompany.com"));
        
        // return list
        return students;
    }
    
    
}
