package controller;

import java.util.ArrayList;
import java.util.List;

import model.Classroom;
import view.ClassesView;

public class ClassesController {
	List <Classroom> classes;
	ClassesView view;
	void displayClasses() {
		DatabaseConnection a= new DatabaseConnection();
		classes=a.retrieveClassesFromDatabase();
		view.displayClassList(classes);
	}
	public ClassesController(ClassesView view){
		classes=new ArrayList<Classroom>();
		this.view=view;
		displayClasses();
	}
	
	
}
