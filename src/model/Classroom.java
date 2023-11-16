package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

public class Classroom {
	 	private String classCode;
	    private String className;
	    private int numOfCurentStudents;
	    private int maximumNumOfStudents;
	    private List<Student> studentList;
	    
	    private List<LocalDateTime> thoiGian;
        private String diadiem;
        private int soTinchi;
	    
        
	    public Classroom(String classCode, String className, int numOfCurentStudents, int maximumNumOfStudents,
				List<Student> studentList, List<LocalDateTime> thoiGian, String diadiem, int soTinchi) {
			super();
			this.classCode = classCode;
			this.className = className;
			this.numOfCurentStudents = numOfCurentStudents;
			this.maximumNumOfStudents = maximumNumOfStudents;
			this.studentList = studentList;
			this.thoiGian = thoiGian;
			this.diadiem = diadiem;
			this.soTinchi = soTinchi;
		}


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
	    public void removeStudent(int index) {
	    	 studentList.remove(index);
		     numOfCurentStudents--;
	    }
	    public void displayStudentList() {
	    		
	    }
	    public Student findAStudent(int id) {
	    	
	    	for (int i=0;i< studentList.size();i++) {
                	if(id==studentList.get(i).getStudentID())
                		return  studentList.get(i);
                }
            
	    	return null;
	    	
	    }
	    public boolean checkAStudent(int id) {
	    	
	    	for (int i=0;i< studentList.size();i++) {
                	if(id==studentList.get(i).getStudentID())
                		return  true;
                }
            
	    	return false;
	    	
	    }
		public List<Student> getStudentList() {
			return studentList;
		}


		public void setStudentList(List<Student> studentList) {
			this.studentList = studentList;
		}


		public int getNumOfCurentStudents() {
			return numOfCurentStudents;
		}


		public void setNumOfCurentStudents(int numOfCurentStudents) {
			this.numOfCurentStudents = numOfCurentStudents;
		}
		
		public void updateClassroom(Classroom a) {
			
		}

		

}
