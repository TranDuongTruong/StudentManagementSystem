package controller.Teacher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.DatabaseConnection;
import model.Classroom;
import model.Student;

import view.Admin.AttendanceRateChart;
import view.Teacher.CreditsPerformanceView_Admin;
import view.Teacher.PerformanceView_Admin;

public class PerformanceController_Admin {
	public CreditsPerformanceView_Admin  creditsPerformanceView;
	
	public List<Student> student;
	
	
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
        	//performanceView_Admin.setVisible(false);
        	
        	displayListOfStudent();
        	
        }
      
    }
	private class AttendanceRate implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
        	AttendanceRateChart a=new AttendanceRateChart();
        	a.setVisible(true);           
        	
        }
      
    }
	
}
