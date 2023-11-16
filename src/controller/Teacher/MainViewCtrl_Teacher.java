package controller.Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import model.ClassesManager;
import model.Classroom;
import model.Student;
import view.Teacher.ClassesView;
import view.Teacher.MainView;
import view.Teacher.StudentView;

public class MainViewCtrl_Teacher {
	public MainView view;

	public MainViewCtrl_Teacher(MainView view) {
		super();
		this.view = view;
		view.classesListener(new ClassesListener ());
		
		}
	private class ClassesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	ClassesView clas=new ClassesView();
        	view.setVisible(false);
        	
        }
	}
//	private class StudentsListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//        	DatabaseConnection a = new DatabaseConnection();
//	    	 ClassesManager classes = new ClassesManager();
//	    	 classes=a.retrieveClassesFromDatabase();
//	    	 //Classroom classRoom=classes.getClassroomList().get(row);
//	    	 StudentView stu=new StudentView(classRoom);
//        	StudentView stu=new StudentView();
//        	view.setVisible(false);
//        	
//        }
//	}
}
