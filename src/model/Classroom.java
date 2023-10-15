package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	 	private String classCode;
	    private String className;
	    private int numberOfStudents;
	    private List<Student> studentList;

	    

	    public String getClassCode() {
			return classCode;
		}


		public void setClassCode(String classCode) {
			this.classCode = classCode;
		}


		public String getClassName() {
			return className;
		}


		public void setClassName(String className) {
			this.className = className;
		}


		public int getNumberOfStudents() {
			return numberOfStudents;
		}


		public void setNumberOfStudents(int numberOfStudents) {
			this.numberOfStudents = numberOfStudents;
		}


		public Classroom(String classCode, String className) {
	        this.classCode = classCode;
	        this.className = className;
	        this.numberOfStudents = 0;
	        this.studentList = new ArrayList();
	    }

	  
	    public void addStudent(Student student) {
	        studentList.add(student);
	        numberOfStudents++;
	    }
	    public void removeStudent(Student student) {
	        //to do
	    }
	    public void displayStudentList() {
	    		
	    }


		public List<Student> getStudentList() {
			return studentList;
		}


		public void setStudentList(List<Student> studentList) {
			this.studentList = studentList;
		}
	    

}
