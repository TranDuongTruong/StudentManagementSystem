package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.DatabaseConnection;

import model.ClassesManager;
import model.Classroom;
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
        	ClassesManager findClassroomList=new ClassesManager();
        	Classroom classroom;
        	classroom=classes.findClassroomByCode(classCode);
        	findClassroomList.addClassroom(classroom);
        	view.displayClassList(findClassroomList);
        }

       
    }
    private class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       	
        	displayClasses();
        }

       
    }
    private class AddNewClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       	
        	Classroom classroom;
        	classroom=view.getNewClass();
        	classes.addClassroom(classroom);
        	displayClasses();
        	db.addClassToDatabase(classroom);
        }

       
    }
    private class DeleteClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       	
        	int index= view.getIndexofClassToDelete();
        	db.deleteClassFromDatabase(classes.getClassroom(index).getClassCode());
        	classes.remove(index);
        	displayClasses();
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
	        }
	        
	    }
	}
    private class SaveClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       
            if (view.currentClassroom != null) {
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
            }
        }
    }
    private class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {       
            // Xóa thông tin lớp đang được cập nhật
            view.currentClassroom = null;
            
            // Xóa nội dung các textfield
            view.deleteForm();
            
            // Tắt bảng
            view.hideUI();
        }
    }
}
