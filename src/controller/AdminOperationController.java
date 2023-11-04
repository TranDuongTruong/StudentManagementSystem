package controller;

import view.AdminOperationView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminOperationController {


	 public static void searchButtonClicked(String id, JTextField emailField, JTextField passwordField, JTextField nameField, JComboBox<String> roleComboBox) {
	        String[] result = searchInDatabase(id);
	        if (result != null) {
	            emailField.setText(result[0]);
	            passwordField.setText(result[1]);
	            nameField.setText(result[2]);

	            // Chuyển đổi giá trị "role" từ cơ sở dữ liệu để trùng khớp với JComboBox
	            String role = result[3];
	            if (role.equals("admin")) {
	                roleComboBox.setSelectedItem("Admin");
	            } else if (role.equals("student")) {
	                roleComboBox.setSelectedItem("Student");
	            } else if (role.equals("teacher")) {
	                roleComboBox.setSelectedItem("Teacher");
	            }
	        } else {
	        	// Xử lý trường hợp không tìm thấy dữ liệu
	        	 // Xóa dữ liệu trong các textField
	            emailField.setText("");
	            passwordField.setText("");
	            nameField.setText("");
	            
	            // Đặt Role về "Student"
	            roleComboBox.setSelectedItem("Student");
	            
	            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu với ID này.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	            
	           
	        }
	    }
    private static String[] searchInDatabase(String id) {
        String[] result = null;
        Connection connection = null;

        try {
   
            connection = connection = DatabaseConnection.connectToBB();

            String query = "SELECT email, password, name, role FROM accounts WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = new String[4];
                result[0] = resultSet.getString("email");
                result[1] = resultSet.getString("password");
                result[2] = resultSet.getString("name");
                result[3] = resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
