package controller.Teacher;

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
import view.Teacher.MainView;
import view.Teacher.StudentView;

public class StudentController implements ActionListener{
	public StudentView studentView;
	List<Student> studentList;
	public Classroom classRoom;
	 
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
		view.addStudentListener(new AddNewStudentListener ());
		view.deleteStudentListener(new DeleteStudentListener());
		view.updateStudentListener(new UpdateStudentListener());
		view.saveStudentListener(new SaveStudentListener());
		view.cancelStudentListener(new CancelStudentListener());
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
	 private class AddNewStudentListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	Student st=studentView.getInfoOfNewStudent();
	        	if(st==null)
	        		return ;
	        	//if(classRoom.checkAStudent(st.getStudentID())) {
	        	classRoom.addStudent(st);
	        	displayListOfStudent();
	        //	}
	        	System.out.println("adding");
	        
	        	

	        }
	    }
	 private class DeleteStudentListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	classRoom.removeStudent(studentView.getIndexofSelectedRow());
	        	displayListOfStudent();
	        	
	        }
	      
	    }
	 private class UpdateStudentListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	//System.out.println(studentView.getIndexofSelectedRow());
	        	studentView.setInfoOfNewStudent();
	        	//System.out.println(classRoom.findAStudent(studentView.getIndexofSelectedRow()).getName());
	        //	classRoom.removeStudent(studentView.getIndexofSelectedRow());
	        	//displayListOfStudent();
	        	
	        }
	      
	    }
	 private class SaveStudentListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	System.out.println("12345666666666666");
	        	if(!studentView.isUpdating)return;
	        	
	        	Student st=studentView.selectedStu;
	        	
	        	classRoom.findAStudent(st.getStudentID()).SetStudent(studentView.getInfoOfExitsStudent());
	        	
	        	System.out.println(st.getCreditsCompleted());
	        	
	        	displayListOfStudent();
	        	studentView.xoaForm();
	        	
	        }
	      
	    }
	
	 private class CancelStudentListener implements ActionListener {
		// public MainView mmainview;
	        public void actionPerformed(ActionEvent e) {
	        	studentView.xoaForm();
	        	
	        }
	      
	    }
	 
	 


	
    
	public static void main(String[] args) {
    	
    	
    }
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

