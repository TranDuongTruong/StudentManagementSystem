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
        classes=a.retrieveClassesFromDatabase();
        view.model=classes;
        setDataToCombobox();
        view.FilteredButtonListener(new FilterClassListener());
//        view.displayAvailableClasses(classes);
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
	    }
	}
	private class FilterClassListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {  
			String selectedCourse = (String) view.comboBoxTenMon.getSelectedItem();
			String classCode=selectedCourse;
        	ClassesManager findClassroomList=new ClassesManager();
        	Classroom classroom;
        	classroom=classes.findClassroomByCode(classCode);
        	findClassroomList.addClassroom(classroom);
        	view.displayAvailableClasses(findClassroomList);           
			
		}
	}
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
