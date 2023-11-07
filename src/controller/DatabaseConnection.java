package controller;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Classroom;
import model.CreditsPerformance;
import model.Student;
import model.ClassesManager;

public class DatabaseConnection {
	 private static Connection con = null;
	 //FOR Local
	    private static final String URL = "jdbc:mysql://localhost:3306/spmdatabase11";   
	    private static final String USER = "root";
	    private static final String PASSWORD = "";

	 
	 //FOR OTHER
//	 private static final String URL = "jdbc:mysql://192.168.1.11:3306/spmdatabase11";
//     private static final String USER = "truongdaica";
//     private static final String PASSWORD = "truong123456";
	    
	    public static Connection connectToBB() {
			try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(URL, USER, PASSWORD);
	            if (!con.isClosed()) {
	                System.out.println("Successfully connected to MySQL server...");
	            }
	        } catch (Exception e) { // Corrected the exception parameter name
	            System.err.println("Exception: " + e.getMessage()); // Print the exception message
	        }
			return con;
		}
	    
	    public ClassesManager retrieveClassesFromDatabase() {
	    	
	        ClassesManager classes = new ClassesManager();
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
	                classes.addClassroom(classroom);

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
	        int studentID; String name; LocalDate dob; String address; boolean gender;
			 String phoneNumber; int creditsCompleted; int  creditsOwed;
	        try {
	            String query = "SELECT s.* FROM student s INNER JOIN studentclassroom sc ON s.studentID = sc.studentID WHERE sc.classCode = ?";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setString(1, classCode);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	            	 System.err.println(  rs.getString(1));
	             studentID = rs.getInt(1); 
	             name= rs.getString(2);
	            // System.err.println(name+"\t");
	             
	             Date dobDate = rs.getDate(3); 
	             //System.err.println(dobDate+"\t");
	             dob= dobDate.toLocalDate();
	             
	       		  address=rs.getString(4); 
	       		  gender=rs.getBoolean(5);
	       		  phoneNumber=rs.getString(6);
	       		  creditsCompleted=rs.getInt(7);
	       		  creditsOwed=rs.getInt(8);
	       		  
	       		 
	       		 Student student=new Student( studentID, name, dob, address, gender, phoneNumber, creditsCompleted, creditsOwed); 
	       		 students.add(student);
	       		  
	       		 System.err.println(name+"\t"+dob);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return students;
	    }
	    void addClassToDatabase(Classroom classroom) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(URL, USER, PASSWORD);

	            String query = "INSERT INTO classroom (classCode, className, totalStudents, maxCapacity) VALUES (?, ?, ?, ?)";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setString(1, classroom.getClassCode());
	            stmt.setString(2, classroom.getClassName());
	            stmt.setInt(3, classroom.getNumberOfStudents());
	            stmt.setInt(4, classroom.getMaximumNumOfStudents());

	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Class added successfully to the database.");
	            } else {
	                System.out.println("Failed to add class to the database.");
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
	    }
	    void addStudentToDatabase(Student student) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(URL, USER, PASSWORD);

	            String query = "INSERT INTO student (studentID, name, dob, address, gender, phoneNumber, creditsCompleted, creditsOwed) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setInt(1, student.getStudentID());
	            stmt.setString(2, student.getName());
	            
	            LocalDate dob = student.getDob();
	            // Chuyển đổi LocalDate thành java.sql.Date
	            Date dobDate = Date.valueOf(dob);
	            stmt.setDate(3, dobDate);
	           
	            stmt.setString(4, student.getAddress());
	            stmt.setBoolean(5, student.isGender());
	            stmt.setString(6, student.getPhoneNumber());
	            stmt.setInt(7, student.getCreditsCompleted());
	            stmt.setInt(8, student.getCreditsOwed());

	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Student added successfully to the database.");
	            } else {
	                System.out.println("Failed to add student to the database.");
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
	    }
	    void deleteStudentFromDatabase(int studentID) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(URL, USER, PASSWORD);

	            String query = "DELETE FROM student WHERE studentID = ?";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setInt(1, studentID);

	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Student deleted successfully from the database.");
	            } else {
	                System.out.println("Failed to delete student from the database. Student ID not found.");
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
	    }
	    void deleteClassFromDatabase(String classCode) {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection(URL, USER, PASSWORD);

	            String query = "DELETE FROM classroom WHERE classCode = ?";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setString(1, classCode);

	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Class deleted successfully from the database.");
	            } else {
	                System.out.println("Failed to delete class from the database. Class not found.");
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
	    }
	  public  CreditsPerformance getCreditsPerformance() {
	    	Connection con = connectToBB(); // Kết nối đến cơ sở dữ liệu
	    	   int numStudentsOwedMoreThan5=0;
               int numStudentsOwed1To5 =0;
               int numStudentsNotOwed = 0 ;
	        try {
	            Statement stmt = con.createStatement();
	            String query = "SELECT * FROM creditsPerformance";
	            ResultSet rs = stmt.executeQuery(query);

	            while (rs.next()) {
	                 numStudentsOwedMoreThan5 = rs.getInt("numStudentsOwedMoreThan5");
	                 numStudentsOwed1To5 = rs.getInt("numStudentsOwed1To5");
	                 numStudentsNotOwed = rs.getInt("numStudentsNotOwed");

//	                System.out.println("Số học sinh nợ hơn 5 môn: " + numStudentsOwedMoreThan5);
//	                System.out.println("Số học sinh nợ từ 1-5 môn: " + numStudentsOwed1To5);
//	                System.out.println("Số học sinh không nợ môn: " + numStudentsNotOwed);
	            }

	            rs.close();
	            stmt.close();
	            con.close();
	       
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return new CreditsPerformance(numStudentsOwedMoreThan5,numStudentsOwed1To5,numStudentsNotOwed);
	        
	    }
	  public List<Student> retrieveStudentsCredited() {
		    System.out.println("Aaa");Connection con = connectToBB();
		    List<Student> students = new ArrayList();
		    int studentID;
		    String name;
		    LocalDate dob;
		    String address;
		    boolean gender;
		    String phoneNumber;
		    int creditsCompleted;
		    int creditsOwed;
		    try {
		        String query = "SELECT * FROM student WHERE creditsOwed > 0 ORDER BY creditsOwed DESC";
		        PreparedStatement stmt = con.prepareStatement(query);
		        ResultSet rs = stmt.executeQuery();
		        while (rs.next()) {
		            studentID = rs.getInt("studentID");
		            name = rs.getString("name");
		            Date dobDate = rs.getDate("dob");
		            dob = dobDate.toLocalDate();
		            address = rs.getString("address");
		            gender = rs.getBoolean("gender");
		            phoneNumber = rs.getString("phoneNumber");
		            creditsCompleted = rs.getInt("creditsCompleted");
		            creditsOwed = rs.getInt("creditsOwed");
		            System.out.println("Aaa"+creditsOwed);
		            Student student = new Student(studentID, name, dob, address, gender, phoneNumber, creditsCompleted, creditsOwed);
		            students.add(student);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    
		    return students;
		}
	    public static void main(String[] args) {
//	    	DatabaseConnection a = new DatabaseConnection();
//	    	 List<Classroom> classes = new ArrayList();
//	    	 classes=a.retrieveClassesFromDatabase();
//	    	
	       }
	    }




		

