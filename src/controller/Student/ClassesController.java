package controller.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import controller.DatabaseConnection;
import controller.Admin.LoginController;
import model.Classroom;
import model.Student;
import view.Student.TranscriptView;
import view.Teacher.ClassesViewP;
import model.ClassesManager;

public class ClassesController  {
    ClassesManager classes;
    public TranscriptView view;
    DatabaseConnection db;
    public ClassesController(TranscriptView view){
    	classes=new ClassesManager();
        this.view=view;
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
   
	
}