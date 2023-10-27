package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.result.LocalDateTimeValueFactory;

import model.Classroom;
import model.Student;
import view.MainView;
import view.StudentView;

public class StudentController implements ActionListener{
	public StudentView studentView;
	List<Student> studentList;
	Classroom classRoom;
	 
	public Classroom getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(Classroom classRoom) {
		this.classRoom = classRoom;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public StudentController(StudentView view,Classroom classRoom) {
		super();
		this.studentView = view;
		this.classRoom=classRoom;
		view.searchStudentListener(new SearchListener());
		view.huyTimListener(new HuyTimListener());
		displayListOfStudent();
	}

	public StudentController(StudentView view) {
		super();
		this.studentView = view;
	
	
	}

	  // ActionListener for the Search button
    private class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	boolean isFind=false;
        	int studentID = studentView.getSearchInp();
        	if(studentID!=0) {
        	List<Student> currentSTList=new ArrayList<Student>();
        	
        	for (Student student : classRoom.getStudentList()) {
                if (student.getStudentID()==(studentID)) {
                	isFind=true;
                	currentSTList.add(student);
                	break;
                }
            }
        	if(isFind)studentView.displayStudentList(currentSTList);
        		else studentView.notFindStudent(studentID);
        	
        	}
        	
        }

       
    }
    private class HuyTimListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	displayListOfStudent();        		        	            	
        }

       
    }
	 public void displayListOfStudent() {
	        studentView.displayStudentList(classRoom.getStudentList());
	    }
	 public void displayListOfStudent(String classCode) {
	    	
	        //...
	 }
	

	 
	 
	 
	 
	 
	 public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		JOptionPane.showMessageDialog(studentView, "Ban vua nhan vao: "+cm);
		if(cm.equals("Add"));{
			this.studentView.xoaForm();
			this.studentView.model.setLuachon("Add");
		}if(cm.equals("save")) {
			try {
				int studentID = Integer.valueOf(this.studentView.textField_ID.getText());
				String name =this.studentView.textField_name.getText();
				String address =this.studentView.comboBox_queQuan.getSelectedIndex()+"";
				//LocalDate dob= new LocalDate(this.view.textField_dob.getText());
				
				
					
				//		    private LocalDate  dob;
				//		    private String address;
				//		    private boolean gender;// male: true; female: false
				//		    private String phoneNumber;
				//		    private int creditsCompleted;
				//		    private int creditsOwed;
				if (this.studentView.model.getLuachon().equals("") || this.studentView.model.getLuachon().equals("Add")) {

				} else if (this.studentView.model.getLuachon().equals("Update")) {

				} 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}if(cm.equals("")) {
			
		}
		
	}

	
    
	public static void main(String[] args) {
    	
    	
    }
}
