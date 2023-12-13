package controller.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import controller.DatabaseConnection;
import controller.Admin.LoginController;
import model.Classroom;
import model.Student;
import view.Student.ExaminationView;
import view.Student.TranscriptView;
import view.Teacher.ClassesViewP;
import model.ClassesManager;

public class ExaminationController  {
    ClassesManager classes;
    public ExaminationView view;

    DatabaseConnection db;
   
    
    public ExaminationController(ExaminationView view){
    	classes=new ClassesManager();
        this.view = view;
        db=new DatabaseConnection();
        
        DatabaseConnection a= new DatabaseConnection();
        classes=a.retrieveClassesFromDatabaseforStudent(LoginController.studentId);
        view.searchClassListener(new SearchListener() );
        view.undoClassListener(new UndoListener());
       
        displayClasses();
    }
    
    
     void displayClasses() {
        
        view.model=classes;
        view.displayClassList(classes);
        
    }
  
   
    
    private class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String classCode=view.getClassCode();
        	
        	ClassesManager findClassroomList=new ClassesManager();
        	
        	if(classCode!=null&&!classCode.trim().isEmpty()) {       		        		
        		for (Classroom classroom: classes.getClassroomList()) {
        	        if (String.valueOf(classroom.getClassCode()).contains(classCode)) {
        	        	findClassroomList.addClassroom(classroom);
        	        }
        	    } 
        	    
        		
        		view.displayClassList(findClassroomList);
            	view.classes=findClassroomList;
        	}
        		        	        
        	
        }

       
    }
    private class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       	
        	displayClasses();
        }

       
    }
    
 // Phương thức kiểm tra xem có bài kiểm tra nào cho lớp cụ thể hay không
    public static boolean hasExams(String classCode) {
        try {
            Connection connection = DatabaseConnection.connectToBB();
            Statement statement = connection.createStatement();

            // Truy vấn kiểm tra xem có bài kiểm tra nào cho lớp này hay không
            String query = "SELECT COUNT(*) as examCount FROM questions WHERE classCode = '" + classCode + "'";
            ResultSet resultSet = statement.executeQuery(query);

            // Lấy kết quả
            if (resultSet.next()) {
                int examCount = resultSet.getInt("examCount");
                return examCount > 0; // Nếu số lượng bài kiểm tra lớn hơn 0, có ít nhất một bài kiểm tra
            }

            // Đóng kết nối
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Trả về false nếu có lỗi xảy ra
    }
    
    // Hàm này trả về trạng thái (Yes/No) dựa trên việc có bài kiểm tra hay không
    public static String getExamStatus(String classCode) {
        return hasExams(classCode) ? "Yes" : "No";
    }
   
	
}