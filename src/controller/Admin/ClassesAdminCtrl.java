package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import controller.DatabaseConnection;

import model.ClassesManager;
import model.Classroom;
import model.Student;
import view.Admin.ClassesAdminView;
import view.Teacher.ClassesViewP;

public class ClassesAdminCtrl {
	ClassesManager classes;
    public ClassesAdminView view;
    DatabaseConnection db;
    public ClassesAdminCtrl(ClassesAdminView view){
    	classes=new ClassesManager();
        this.view=view;
        db=new DatabaseConnection();
        DatabaseConnection a= new DatabaseConnection();
        classes=a.retrieveClassesFromDatabase();
        view.searchClassListener(new SearchListener() );
        view.undoClassListener(new UndoListener());
        view.addClassListener(new AddNewClassListener());
        view.deleteClassListener(new DeleteClassListener());
        view.updateClassListener(new UpdateClassListener());
        view.saveClassListener(new SaveClassListener());
        view.CancelButtonListener(new CancelListener());
        displayClasses();
    }
    
     void displayClasses() {
        
        view.model=classes;
        view.displayClassList(classes);
        
    }
  
   
    
    private class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String classCode=view.getClassCode();
//        	ClassesManager findClassroomList=new ClassesManager();
//        	Classroom classroom;
//        	classroom=classes.findClassroomByCode(classCode);
//        	findClassroomList.addClassroom(classroom);
//        	view.displayClassList(findClassroomList);
        	
        	
        	
        	boolean isFind=false;
        	
        	ClassesManager findClassroomList=new ClassesManager();
        	if (classCode != null && !classCode.trim().isEmpty()) {
        	    

        	    for (Classroom classroom : classes.getClassroomList()) {
        	        if (String.valueOf(classroom.getClassCode()).contains(classCode)) {
        	            isFind = true;
        	            findClassroomList.addClassroom(classroom);
        	        }
        	    } 
        	    
        	    if(isFind) view.displayClassList(findClassroomList);
        		else view.notFindClass(classCode);
        	
        	}
        	
        }

       
    }
    private class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       	
        	displayClasses();
        }

       
    }
    private class AddNewClassListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
        	try {
        		if (view.showConfirmationDialog("Are you sure you want to add?")) {
        		String enteredClassCode = view.textField_MaLop.getText();
                Classroom classCodeExists = classes.findClassroomByCode(enteredClassCode);
                if (classCodeExists!=null) 
                    view.showErrorDialog("The class code already exists. Please enter a different class code.");
                else {
        			Classroom classroom;
		        	classroom=view.getNewClass();
		        	classes.addClassroom(classroom);
		        	displayClasses();
		        	db.addClassToDatabase(classroom);
		        	view.showNotification("Add completed successfully.");
        		}
        		}
        	}catch (NumberFormatException ex) {
                // Display error dialog for incorrect data type
                view.showErrorDialog("Please enter a valid number for the number of students.");
            }

       
    }
    }
    private class DeleteClassListener implements ActionListener {
    	 public void actionPerformed(ActionEvent e) {
         	if (view.showConfirmationDialog("Are you sure you want to delete?")) {
 	        	int index= view.getIndexofClassToDelete();
 	        	db.deleteClassFromDatabase(classes.getClassroom(index).getClassCode());
 	        	classes.remove(index);
 	        	displayClasses();
 	        	view.showNotification("Delete completed successfully.");
 	        }

       
    }
    }
    private class UpdateClassListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {       
	        // Lấy thông tin lớp đang được chọn
	        int selectedIndex = view.getSelectedRowIndex();
	        if (selectedIndex != -1) {
	            view.currentClassroom = classes.getClassroom(selectedIndex);
	            
	            // Hiển thị thông tin lớp lên các textfield
	            view.setClassInfo(view.currentClassroom);
	        }else {
	        	view.showNotification("Please select a class in the table to update.");
	        }
	        
	    }
	}
    private class SaveClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       
        	try {
        		if (view.showConfirmationDialog("Are you sure you want to save?")) {
        		String field1Value = view.textField_TenLop.getText();
                String field2Value = view.textField_MaLop.getText();
                String field3Value = view.textField_SoHSHT.getText();
                String field4Value = view.textField_SoHSTD.getText();
                if(field1Value.isEmpty() ||field2Value.isEmpty() ||field3Value.isEmpty() ||field4Value.isEmpty() )
                	 view.showReminderDialog("Please fill in all the required fields.");
                else {
        			 if (view.currentClassroom != null) {
     	            	if (view.getNumOfCurrentStudents() < 0 || view.getMaximumNumOfStudents() < 0||view.getNumOfCurrentStudents()>view.getMaximumNumOfStudents()) {
     	                    // Display error dialog for invalid input
     	                    view.showErrorDialog("Please enter a valid number of students.");}
     	            	else {
     		                // Cập nhật thông tin lớp từ các textfield
     		                view.currentClassroom.setClassName(view.getClassName());
     		                view.currentClassroom.setNumOfCurentStudents(view.getNumOfCurrentStudents());
     		                view.currentClassroom.setMaximumNumOfStudents(view.getMaximumNumOfStudents());

     		                // Lưu lại thông tin lớp trong danh sách lớp
     		                classes.updateClassroom(view.currentClassroom);
     		                db.updateClassInDatabase(view.currentClassroom);
     		                // Hiển thị lại danh sách lớp
     		                displayClasses();

     		                // Xóa thông tin lớp đang được cập nhật
     		                view.currentClassroom = null;
     		                view.showNotification("Save completed successfully.");
     	                }
     	            }else {
     	            	view.showNotification("Please select a class in the table to update before save.");
     	            }
	        		}
		        }
        	}catch (NumberFormatException ex) {
                // Display error dialog for incorrect data type
                view.showErrorDialog("Please enter a valid number for the number of students.");
            }
        }
    }
    private class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       
        	if (view.showConfirmationDialog("Are you sure you want to cancel?")) {
	            // Xóa nội dung các textfield
	            view.deleteForm();
	            view.showNotification("Cancel completed successfully.");
            }
        }
    }
}