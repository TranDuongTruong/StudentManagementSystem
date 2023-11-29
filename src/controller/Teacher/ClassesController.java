package controller.Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import controller.DatabaseConnection;
import controller.Admin.LoginController;
import model.Classroom;
import model.Student;

import view.Teacher.ClassesViewP;
import model.ClassesManager;

public class ClassesController  {
    ClassesManager classes;
    public ClassesViewP view;
    DatabaseConnection db;
    public ClassesController(ClassesViewP view){
    	classes=new ClassesManager();
        this.view=view;
        db=new DatabaseConnection();
        
        DatabaseConnection a= new DatabaseConnection();
//        LoginController.teacherId=9;
        classes=a.retrieveClassesFromDatabase(LoginController.teacherId);
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
        	Classroom classroom;
        	classroom=classes.findClassroomByCode(classCode);
        	findClassroomList.addClassroom(classroom);
        	view.displayClassList(findClassroomList);
        	view.classes=findClassroomList;
        }

       
    }
    private class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       	
        	displayClasses();
        }

       
    }
   
	
}