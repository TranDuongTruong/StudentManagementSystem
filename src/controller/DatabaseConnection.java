package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Classroom;
import model.Student;

public class DatabaseConnection {
	 private static Connection con = null;
	    private static final String URL = "jdbc:mysql://localhost:3306/studentmanagementdb";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";

	    
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
	    
	   
	    List<Classroom>  retrieveClassesFromDatabase() {
	    	 List<Classroom> classes = new ArrayList();
	    	String f1,f2;
	    	int f3,f4;
	    	try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(URL, USER, PASSWORD);
	            
	            String query = "Select * FROM classes";
	            Statement stmt = con.createStatement();
	            ResultSet rs= stmt.executeQuery(query);
	            while (rs.next()){
	            	 
	            	f1 = rs.getString(1);
	            	f2=	rs.getString(2);
	            	f3=	rs.getInt(3);
	            	f4=rs.getInt(4);
	            	
	            	List<Student> stu = new ArrayList();
	            	stu=retrieveStudentFromDatabase(f1);
	            	
	            	Classroom cl=new Classroom(f1,f2,f3,f4,stu);
	            	classes.add(cl);
	            	System.out.println(f1+"  "+f2);}
	            
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
	    	return classes;
	    	
	    }
	    
	    
	    List<Student>  retrieveStudentFromDatabase(String classCode){
	    	List<Student> stu = new ArrayList();
	    	String clas="students"+classCode.toLowerCase();
	    	 String query = "Select * FROM "+clas;
	    	 
	    	 
	    	 int studentID;
	    	  String name;
	    	  String dob;
	    	  String address;
	    	   boolean gender;// male: true; female: false
	    	    String phoneNumber;
	    	  int creditsCompleted;
	    	   int creditsOwed;
	    	   
	    	   
	    	   
	    		try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            con = DriverManager.getConnection(URL, USER, PASSWORD);
		            String aa = "Select * FROM "+ clas;
		            Statement stmt = con.createStatement();
		            ResultSet rs= stmt.executeQuery(aa);	 
	    	 while (rs.next()){
            	 
	            	studentID = rs.getInt(1);
	            	name=	rs.getString(2);
	            	dob=	rs.getString(3);
	            	address=rs.getString(4);
	            	gender=rs.getBoolean(5);
	            	phoneNumber=rs.getString(6);
	            	creditsCompleted=rs.getInt(7);
	            	creditsOwed=rs.getInt(8);
	            	
	            	
	            	Student student=new Student( studentID,  name,  dob,  address,  gender,  phoneNumber,  creditsCompleted,  creditsOwed);
	       	         stu.add(student);
	       	         
	       	      System.out.println(studentID+"\t");
	    	 }
	    	 
	    		}catch (Exception e) { // Corrected the exception parameter name
		            System.err.println("Exception: " + e.getMessage()); 
	    		}
	    		
	    	
	    	
	    	return stu;
	    }
	    
	    public static void main(String[] args) {
	    	DatabaseConnection a = new DatabaseConnection();
	    	 List<Classroom> classes = new ArrayList();
	    	 classes=a.retrieveClassesFromDatabase();
	       }
	    }




		

