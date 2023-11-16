package controller.Student;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DatabaseConnection;
import controller.Admin.LoginController;
import view.Student.CourseView;
import view.Student.ScheduleView;
import view.Student.StudentAccountMainView;

public class CourseCtrl {
	public CourseView view;
	public int studentID;
	public String[] className;
	public CourseCtrl(CourseView view) {
		super();
		this.view = view;
		className = getClassName();
		studentID= LoginController.studentId;
		view.setDataToCombobox(className);
		
	}
	public String[] getClassName() {
        List<String> classNames = new ArrayList<>();
        DatabaseConnection db = new DatabaseConnection();
        Connection con = db.connectToBB();

        // Thực hiện truy vấn SQL để lấy thông tin className từ bảng classroom
        String sql = "SELECT className FROM classroom";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Đọc giá trị từ cột className trong kết quả truy vấn
                String className = resultSet.getString("className");
                classNames.add(className);
          
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 

        // Chuyển danh sách className thành mảng String và trả về
        return classNames.toArray(new String[0]);
    }
	
}
