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

import controller.DatabaseConnection;
import model.Classroom;
import model.Student;


import view.Teacher.StudentViewP;

public class StudentController implements ActionListener{
	public StudentViewP studentView;
	List<Student> studentList;DatabaseConnection db;
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
	public StudentController(StudentViewP view,Classroom classRoom) {
		super();
		this.studentView = view;
		this.classRoom=classRoom;
		db=new DatabaseConnection();
		view.searchStudentListener(new SearchListener());
		view.huyTimListener(new HuyTimListener());
		view.addStudentListener(new AddNewStudentListener ());
		view.deleteStudentListener(new DeleteStudentListener());
		view.updateStudentListener(new UpdateStudentListener());
		view.saveStudentListener(new SaveStudentListener());
		view.cancelStudentListener(new CancelStudentListener());
		displayListOfStudent();
	}

	public StudentController(StudentViewP view) {
		super();
		this.studentView = view;
	
	
	}

	  // ActionListener for the Search button
    private class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	boolean isFind=false;
        	String searchInput = studentView.getSearchInp();

        	if (searchInput != null && !searchInput.trim().isEmpty()) {
        	    List<Student> currentSTList = new ArrayList<>();

        	    for (Student student : classRoom.getStudentList()) {
        	        if (String.valueOf(student.getStudentID()).contains(searchInput)) {
        	            isFind = true;
        	            currentSTList.add(student);
        	        }
        	    } 
        	    
        	    if(isFind) studentView.displayStudentList(currentSTList);
        		else studentView.notFindStudent(searchInput);
        	
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
	        
	        	db.addStudentToDatabase(st);

	        }
	    }
	 private class DeleteStudentListener implements ActionListener {
		 public void actionPerformed(ActionEvent e) {
		        int selectedIndex = studentView.getIndexofSelectedRow();

		        if (selectedIndex != -1) {
		            int option = JOptionPane.showConfirmDialog(studentView,
		                    "Are you sure you want to delete this student?",
		                    "Confirm Deletion",
		                    JOptionPane.YES_NO_OPTION);

		            if (option == JOptionPane.YES_OPTION) {
		                // Xoá sinh viên khỏi cơ sở dữ liệu
		                db.deleteStudentFromDatabase(selectedIndex);

		                // Xoá sinh viên khỏi danh sách lớp học
		                classRoom.removeStudent(selectedIndex);

		                // Hiển thị lại danh sách sinh viên
		                displayListOfStudent();
		            }
		        } else {
		            JOptionPane.showMessageDialog(studentView, "Please select a student to delete.", "No Student Selected", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		}

	 private class UpdateStudentListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	studentView.textField_ID.setEditable(false); 
	        	studentView.setInfoOfNewStudent();
	        
	        	
	        }
	      
	    }
	 private class SaveStudentListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	
	        	if(!studentView.isUpdating)return;
	        	
	        	Student st=studentView.selectedStu;
	        	if(!studentView.test(false))return;
	        	classRoom.findAStudent(st.getStudentID()).SetStudent(studentView.getInfoOfExitsStudent());
	        	
	        	System.out.println(st.getCreditsCompleted());
	        	studentView.textField_ID.setEditable(true); 
	        	displayListOfStudent();
	        	studentView.xoaForm();
	        	JOptionPane.showMessageDialog(null, "Update successful", "Success", JOptionPane.INFORMATION_MESSAGE);
	        	db.updateStudentInDatabase(st);
	        }
	      
	    }
	
	 private class CancelStudentListener implements ActionListener {
		// public MainView mmainview;
	        public void actionPerformed(ActionEvent e) {
	        	studentView.xoaForm();studentView.textField_ID.setEditable(true); 
	        	
	        }
	      
	    }
	 
	 


	
    
	public static void main(String[] args) {
    	
    	
    }
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

