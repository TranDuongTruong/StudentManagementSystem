package model;
import java.sql.Date;
import java.time.LocalDate;
public class Student {
	  	private int studentID;
	 	private String name;
	    private LocalDate  dob;
	    private String address;
	    private boolean gender;// male: true; female: false
	    private String phoneNumber;
	    private int creditsCompleted;
	    private int creditsOwed;
	    private String luachon;
	    public Student(int studentID, String name, LocalDate dob, String address, Boolean gender, String phoneNumber, int creditsCompleted, int creditsOwed) {
	        this.studentID = studentID; 
	        this.name = name;
	        this.dob = dob;
	        this.address = address;
	        this.gender = gender;
	        this.phoneNumber = phoneNumber;
	        this.creditsCompleted = creditsCompleted;
	        this.creditsOwed = creditsOwed;
	        this.luachon="";
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


		public String getLuachon() {
			return luachon;
		}


		public void setLuachon(String luachon) {
			this.luachon = luachon;
		}


		
	    
}
