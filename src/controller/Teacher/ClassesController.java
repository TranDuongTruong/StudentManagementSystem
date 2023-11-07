package controller.Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import controller.DatabaseConnection;
import model.Classroom;
import model.Student;
import view.Teacher.ClassesView;
import model.ClassesManager;

public class ClassesController  {
    ClassesManager classes;
    public ClassesView view;
   
    public ClassesController(ClassesView view){
        classes=new ClassesManager();
        this.view=view;
        DatabaseConnection a= new DatabaseConnection();
        classes=a.retrieveClassesFromDatabase();
        view.searchClassListener(new SearchListener() );
        view.undoClassListener(new UndoListener());
        view.addClassListener(new AddNewClassListener());
        view.deleteClassListener(new DeleteClassListener());
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
        }

       
    }
    private class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       	
        	displayClasses();
        }

       
    }
    private class AddNewClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       	
        	Classroom classroom;
        	classroom=view.getNewClass();
        	classes.addClassroom(classroom);
        	displayClasses();
        }

       
    }
    private class DeleteClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       	
        	int index= view.getIndexofClassToDelete();
        	classes.remove(index);
        	displayClasses();
        }

       
    }
    private class EditClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       	
        	
        	displayClasses();
        }

       
    }
	
}