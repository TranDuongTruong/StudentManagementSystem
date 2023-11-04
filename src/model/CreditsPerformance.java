package model;

import controller.DatabaseConnection;

public class CreditsPerformance {
	int numStudentsOwedMoreThan5;
	int numStudentsOwed1To5;
	int numStudentsNotOwed;
	
	public CreditsPerformance(int numStudentsOwedMoreThan5, int numStudentsOwed1To5, int numStudentsNotOwed) {
		super();
		this.numStudentsOwedMoreThan5 = numStudentsOwedMoreThan5;
		this.numStudentsOwed1To5 = numStudentsOwed1To5;
		this.numStudentsNotOwed = numStudentsNotOwed;
	}
	public int getNumStudentsOwedMoreThan5() {
		return numStudentsOwedMoreThan5;
	}
	public void setNumStudentsOwedMoreThan5(int numStudentsOwedMoreThan5) {
		this.numStudentsOwedMoreThan5 = numStudentsOwedMoreThan5;
	}
	public int getNumStudentsOwed1To5() {
		return numStudentsOwed1To5;
	}
	public void setNumStudentsOwed1To5(int numStudentsOwed1To5) {
		this.numStudentsOwed1To5 = numStudentsOwed1To5;
	}
	public int getNumStudentsNotOwed() {
		return numStudentsNotOwed;
	}
	public void setNumStudentsNotOwed(int numStudentsNotOwed) {
		this.numStudentsNotOwed = numStudentsNotOwed;
	}
	public static void main(String[]args) {
		DatabaseConnection a=new DatabaseConnection();
		CreditsPerformance b=a.getCreditsPerformance();
		System.out.println(b.getNumStudentsNotOwed());
	}
}
