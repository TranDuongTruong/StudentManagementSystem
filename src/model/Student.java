package model;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
public class Student {
	  	private int studentID;
	 	private String name;
	    private LocalDate  dob;
	    private String address;
	    private boolean gender;// male: true; female: false
	    private String phoneNumber;
	    private int creditsCompleted;
	    private int creditsOwed;
	    private boolean attendance=false;
	    
	    
	    
	    private List<Classroom> classroomList;
	    
	    
	    
	    
	    
 	    public Student(int studentID, String name, LocalDate dob, String address, boolean gender, String phoneNumber,
				int creditsCompleted, int creditsOwed, boolean attendance, List<Classroom> classroomList) {
			super();
			this.studentID = studentID;
			this.name = name;
			this.dob = dob;
			this.address = address;
			this.gender = gender;
			this.phoneNumber = phoneNumber;
			this.creditsCompleted = creditsCompleted;
			this.creditsOwed = creditsOwed;
			this.attendance = attendance;
			this.classroomList = classroomList;
		}
 	    	



		public boolean isAttendance() {
			return attendance;
		}




		public void setAttendance(boolean attendance) {
			this.attendance = attendance;
		}




		public Student(int studentID, String name, LocalDate dob, String address, Boolean gender, String phoneNumber, int creditsCompleted, int creditsOwed) {
	        this.studentID = studentID; 
	        this.name = name;
	        this.dob = dob;
	        this.address = address;
	        this.gender = gender;
	        this.phoneNumber = phoneNumber;
	        this.creditsCompleted = creditsCompleted;
	        this.creditsOwed = creditsOwed;
	    }
 	    
 	    
 	    

	    public Student(int studentID, String name, LocalDate dob, String address, boolean gender, String phoneNumber,
				int creditsCompleted, int creditsOwed, List<Classroom> classroomList) {
			super();
			this.studentID = studentID;
			this.name = name;
			this.dob = dob;
			this.address = address;
			this.gender = gender;
			this.phoneNumber = phoneNumber;
			this.creditsCompleted = creditsCompleted;
			this.creditsOwed = creditsOwed;
			this.classroomList = classroomList;
		}




		public List<Classroom> getClassroomList() {
			return classroomList;
		}




		public void setClassroomList(List<Classroom> classroomList) {
			this.classroomList = classroomList;
		}




		public void SetStudent(Student st) {
	        this.studentID = st.studentID; 
	        this.name = st.name;
	        this.dob = st.dob;
	        this.address = st.address;
	        this.gender = st.gender;
	        this.phoneNumber = st.phoneNumber;
	        this.creditsCompleted = st.creditsCompleted;
	        this.creditsOwed = st.creditsOwed;
	    }
	  
	    
	    
	    
	    public int getStudentID() {
	        return studentID;
	    }

	    public void setStudentID(int studentID) {
	        this.studentID = studentID;
	    }
	    public String getName() {
	        return name;
	    }

	    public LocalDate getDob() {
	        return dob;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public int getCreditsCompleted() {
	        return creditsCompleted;
	    }

	    public int getCreditsOwed() {
	        return creditsOwed;
	    }

		public boolean isGender() {
			return gender;
		}

		public void setGender(boolean gender) {
			this.gender = gender;
		}


		
	    
}
