package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Classroom;
import model.Student;
import view.CreditsPerformanceView_Admin;
import view.PerformanceView_Admin;
import view.StudentView;

public class PerformanceController_Admin {
	public CreditsPerformanceView_Admin  creditsPerformanceView;
	public PerformanceView_Admin performanceView_Admin;
	public List<Student> student;
	public PerformanceController_Admin(PerformanceView_Admin performanceView_Admin) {
		
		
		this.performanceView_Admin = performanceView_Admin;		
		System.out.println("Aaaaaaaaaa22");
		performanceView_Admin.creditsChartListener(new CreditsChartListener());
		System.out.println("Aaaaaaaaaa2232");
		
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
	public static void main(String []args) {
//		DatabaseConnection con=new DatabaseConnection();
//		
//		if(con==null) System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//		List<Student> student=con.retrieveStudentsCredited();
//		
    	//System.out.println(student.get(0).getCreditsOwed());
	}
	
}
