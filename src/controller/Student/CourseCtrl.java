package controller.Student;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DatabaseConnection;
import controller.Admin.LoginController;
import model.ClassesManager;
import model.Classroom;
import view.Student.CourseView;
import view.Student.ScheduleView;
import view.Student.StudentAccountMainView;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class CourseCtrl {
	ClassesManager classes;
	public CourseView view;
	DatabaseConnection db;
//	public int studentID;
//	public String[] className;
	public CourseCtrl(CourseView view) {
		classes=new ClassesManager();
        this.view=view;
        db=new DatabaseConnection();
        DatabaseConnection a= new DatabaseConnection();
        classes=a.retrieveClassesFromDatabaseWithSchedule();
        view.model=classes;
        setDataToCombobox();
//        view.FilteredButtonListener(new FilterClassListener());
        view.displayRegisteredClasses(view.currentRegisteredClass);
        view.displayAvailableClasses(classes);
//		className = getClassName();
//		studentID= LoginController.studentId;
//		view.setDataToCombobox(className);
		
	}
	
	public void setDataToCombobox() {
	    if (view.model != null) {
	        String[] classCodes = new String[classes.getClassroomList().size()];
	        for (int i = 0; i < classes.getClassroomList().size(); i++) {
	            classCodes[i] = classes.getClassroomList().get(i).getClassCode();
	        }
	        view.setDataToCombobox(classCodes);
	        DefaultComboBoxModel<String> existingModel = (DefaultComboBoxModel<String>) view.comboBoxTenMon.getModel();
	        DefaultComboBoxModel<String> newModel = new DefaultComboBoxModel<>();
	        // Sao chép dữ liệu từ existingModel sang newModel
	        newModel.addElement("");
	        for (int i = 0; i < existingModel.getSize(); i++) {
	            newModel.addElement(existingModel.getElementAt(i));
	        }
	        view.comboBoxTenMon.setModel(newModel);
	    }
	}
//	public void removeClassesRegistered(ClassesManager classes) {
//		String[] classCode = new String[view.currentRegisteredClass.getClassroomList().size()];
//		for (int i = 0; i<view.model.getClassroomList().size();i++) {
//            for(int j=0;j<view.currentRegisteredClass.getClassroomList().size();i++)
////            	if(view.model.)
//
//    }
//		}
//	public String[] getClassName() {
//        List<String> classNames = new ArrayList<>();
//        DatabaseConnection db = new DatabaseConnection();
//        Connection con = db.connectToBB();
//
//        // Thực hiện truy vấn SQL để lấy thông tin className từ bảng classroom
//        String sql = "SELECT className FROM classroom";
//
//        try (PreparedStatement preparedStatement = con.prepareStatement(sql);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            while (resultSet.next()) {
//                // Đọc giá trị từ cột className trong kết quả truy vấn
//                String className = resultSet.getString("className");
//                classNames.add(className);
//          
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } 
//
//        // Chuyển danh sách className thành mảng String và trả về
//        return classNames.toArray(new String[0]);
//    }
	
}
