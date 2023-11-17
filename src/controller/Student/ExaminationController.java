package controller.Student;

import view.Student.ExaminationView;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseConnection;
import controller.Admin.LoginController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExaminationController {

    private ExaminationView view;

    public ExaminationController(ExaminationView view) {
        this.view = view;
    }

    public void loadDataFromDatabase() {
        int studentId = LoginController.studentId;

        try {
            // Establish a connection
            Connection connection = DatabaseConnection.connectToBB(); // Get the database connection

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query to get data from the database
            String query = "SELECT classroom.classCode, classroom.className " +
                           "FROM classroom " +
                           "JOIN studentclassroom ON classroom.classCode = studentclassroom.classCode " +
                           "WHERE studentclassroom.studentID = " + studentId;

            ResultSet resultSet = statement.executeQuery(query);

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            model.setRowCount(0); // Clear existing data

            // Populate the table with data from the result set
            while (resultSet.next()) {
                String classCode = resultSet.getString("classCode");
                String className = resultSet.getString("className");
                
                // Thêm trạng thái vào mô hình bảng
                String examStatus = getExamStatus(classCode);
                model.addRow(new Object[] { classCode, className, examStatus });
            }

            // Close the connections
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
 // Phương thức kiểm tra xem có bài kiểm tra nào cho lớp cụ thể hay không
    public boolean hasExams(String classCode) {
        try {
            Connection connection = DatabaseConnection.connectToBB();
            Statement statement = connection.createStatement();

            // Truy vấn kiểm tra xem có bài kiểm tra nào cho lớp này hay không
            String query = "SELECT COUNT(*) as examCount FROM questions WHERE classCode = '" + classCode + "'";
            ResultSet resultSet = statement.executeQuery(query);

            // Lấy kết quả
            if (resultSet.next()) {
                int examCount = resultSet.getInt("examCount");
                return examCount > 0; // Nếu số lượng bài kiểm tra lớn hơn 0, có ít nhất một bài kiểm tra
            }

            // Đóng kết nối
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false; // Trả về false nếu có lỗi xảy ra
    }
    
    // Hàm này trả về trạng thái (Yes/No) dựa trên việc có bài kiểm tra hay không
    public String getExamStatus(String classCode) {
        return hasExams(classCode) ? "Yes" : "No";
    }

}
