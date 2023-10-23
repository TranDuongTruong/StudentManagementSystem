package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Classroom;
import model.Student;

public class DatabaseConnection {
	 private static Connection con = null;
	 //FOR TRANDUONGTRUONG
//	    private static final String URL = "jdbc:mysql://localhost:3306/studentmanagementdb?useSSL=false";   
//	    private static final String USER = "root";
//	    private static final String PASSWORD = "";

	 
	 //FOR OTHER
	 private static final String URL = "jdbc:mysql://192.168.1.11:3306/spmdatabase11";
     private static final String USER = "truongdaica";
     private static final String PASSWORD = "truong123456";
	    
	    private static void connectToBB() {
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(URL, USER, PASSWORD);
	            if (!con.isClosed()) {
	                System.out.println("Successfully connected to MySQL server...");
	            }
	        } catch (Exception e) { // Corrected the exception parameter name
	            System.err.println("Exception: " + e.getMessage()); // Print the exception message
	        } finally {
	            try {
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace(); 
	            }
	        }
		}
	    
	    List<Classroom> retrieveClassesFromDatabase() {
	    	
	        List<Classroom> classes = new ArrayList();
	        String f1, f2;
	        int f3, f4;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(URL, USER, PASSWORD);

	            String query = "SELECT * FROM classroom";
	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            while (rs.next()) {
	                f1 = rs.getString(1);
	                f2 = rs.getString(2);
	                f3 = rs.getInt(3);
	                f4 = rs.getInt(4);

	                List<Student> students = retrieveStudentsFromClassroom(f1);

	                Classroom classroom = new Classroom(f1, f2, f3, f4, students);
	                classes.add(classroom);

	                System.out.println(f1 + "  " + f2);
	            }

	            if (!con.isClosed()) {
	                System.out.println("Successfully connected to MySQL server...");
	            }
	        } catch (Exception e) {
	            System.err.println("Exception: " + e.getMessage());
	        } finally {
	            try {
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return classes;
	    }

	    
	    List<Student> retrieveStudentsFromClassroom(String classCode) {
	        List<Student> students = new ArrayList();
	        int studentID; String name; String dob; String address; boolean gender;
			 String phoneNumber; int creditsCompleted; int  creditsOwed;
	        try {
	            String query = "SELECT s.* FROM student s INNER JOIN studentclassroom sc ON s.studentID = sc.studentID WHERE sc.classCode = ?";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setString(1, classCode);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	 System.err.println(  rs.getString(1));
	             studentID = rs.getInt(1); name= rs.getString(2); dob= rs.getString(3);
	       		  address=rs.getString(4); gender=rs.getBoolean(5);
	       		  phoneNumber=rs.getString(6); creditsCompleted=rs.getInt(7);
	       		  creditsOwed=rs.getInt(8);
	       		  
	       		 
	       		 Student student=new Student( studentID, name, dob, address, gender,
	       		  phoneNumber, creditsCompleted, creditsOwed); students.add(student);
	       		  
	       		 System.err.println(name+"\t"+studentID);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return students;
	    }
	    
	    
	    public static void main(String[] args) {
	    	DatabaseConnection a = new DatabaseConnection();
	    	 List<Classroom> classes = new ArrayList();
	    	 classes=a.retrieveClassesFromDatabase();
	
	       }
	    }




		

