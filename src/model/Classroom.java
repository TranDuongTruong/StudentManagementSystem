package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	 	private String classCode;
	    private String className;
	    private int numOfCurentStudents;
	    private int maximumNumOfStudents;
	    private List<Student> studentList;
	    
	    
	    
	    public int getMaximumNumOfStudents() {
			return maximumNumOfStudents;
		}


		public void setMaximumNumOfStudents(int maximumNumOfStudents) {
			this.maximumNumOfStudents = maximumNumOfStudents;
		}

	    

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
			return numOfCurentStudents;
		}


		public void setNumberOfStudents(int numberOfStudents) {
			this.numOfCurentStudents = numberOfStudents;
		}



	  
	    public Classroom(String classCode, String className, int numOfCurentStudents, int maximumNumOfStudents,
			List<Student> studentList) {
			super();
			this.classCode = classCode;
			this.className = className;
			this.numOfCurentStudents = numOfCurentStudents;
			this.maximumNumOfStudents = maximumNumOfStudents;
			this.studentList = studentList;
		}

	    public Classroom(String classCode, String className, int numOfCurentStudents, int maximumNumOfStudents
				) {
				super();
				this.classCode = classCode;
				this.className = className;
				this.numOfCurentStudents = numOfCurentStudents;
				this.maximumNumOfStudents = maximumNumOfStudents;
			
			}
		public void addStudent(Student student) {
	        studentList.add(student);
	        numOfCurentStudents++;
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
