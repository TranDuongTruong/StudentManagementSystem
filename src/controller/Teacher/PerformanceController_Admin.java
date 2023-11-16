package controller.Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.DatabaseConnection;
import model.Classroom;
import model.Student;
import view.Teacher.CreditsPerformanceView_Admin;
import view.Teacher.PerformanceView_Admin;
import view.Teacher.StudentView;

public class PerformanceController_Admin {
	public CreditsPerformanceView_Admin  creditsPerformanceView;
	public PerformanceView_Admin performanceView_Admin;
	public List<Student> student;
	public PerformanceController_Admin(PerformanceView_Admin performanceView_Admin) {
		
		
		this.performanceView_Admin = performanceView_Admin;		
		
		performanceView_Admin.creditsChartListener(new CreditsChartListener());
		
		
	}
	
	public PerformanceController_Admin(CreditsPerformanceView_Admin creditsPerformanceView_) {
		super();
		
		this.creditsPerformanceView = creditsPerformanceView_;
		this.student=student;
		//displayListOfStudent();
	}
	public void displayListOfStudent() {
		 creditsPerformanceView=new CreditsPerformanceView_Admin();
		creditsPerformanceView.displayStudentList(student);
		creditsPerformanceView.setVisible(true);
    }
	private class CreditsChartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	System.out.println("Aaaaaaaaaa");
        	DatabaseConnection con=new DatabaseConnection();
        	student=con.retrieveStudentsCredited();
        	
        	displayListOfStudent();
        	
        }
      
    }
	
	
}
