package controller;

import java.util.List;

import model.Classroom;
import model.Student;
import view.CreditsPerformanceView_Admin;
import view.StudentView;

public class PerformanceController_Admin {
	public CreditsPerformanceView_Admin  creditsPerformanceView_;
	public Classroom classRoom;
	public PerformanceController_Admin(CreditsPerformanceView_Admin creditsPerformanceView_, Classroom classRoom) {
		super();
		
		this.creditsPerformanceView_ = creditsPerformanceView_;
		this.classRoom = classRoom;
		displayListOfStudent();
	}
	public void displayListOfStudent() {
		creditsPerformanceView_.displayStudentList(classRoom.getStudentList());
    }
	
	
}
