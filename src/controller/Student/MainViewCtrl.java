package controller.Student;

import model.Student;
import view.Student.StudentAccountMainView;

public class MainViewCtrl {
	StudentAccountMainView view;
	Student student;
	public MainViewCtrl(StudentAccountMainView view, Student student) {
		super();
		this.view = view;
		this.student = student;
	}
	
}
