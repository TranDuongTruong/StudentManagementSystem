package controller.Admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.DatabaseConnection;
import view.Admin.CreateUserAccountsView;
import view.Admin.LoginView;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CreateUserAccountsController {
    private final CreateUserAccountsView createUserAccountsView;

    public CreateUserAccountsController(CreateUserAccountsView createUserAccountsView) {
        this.createUserAccountsView = createUserAccountsView;

//        createUserAccountsView.addSubmitListener(new SubmitListener());
//        createUserAccountsView.addBackListener(new BackListener());
    }

    public void handleCreateAccount() {
        String email = createUserAccountsView.getEmail();
        String password = createUserAccountsView.getPassword();
        String name = createUserAccountsView.getName();
        String role = createUserAccountsView.getRole();
        String studentID = createUserAccountsView.getStudentID();
        String teacherID = createUserAccountsView.getTeacherID();

        

        if (isValidEmail(email) && isValidPassword(password) && isValidName(name)) {
            if (isEmailExists(email)) {
                createUserAccountsView.displayEmailError("Email already exists. Please choose another email.");
            } else {
            	createUserAccountsView.clearError();
            	 // Kiểm tra nếu role là "admin" hoặc "teacher", thì đặt studentID thành null
            	if ("admin".equalsIgnoreCase(role)) {
            	    studentID = null;
            	    teacherID = null;
            	} else if("student".equalsIgnoreCase(role)){
            	    teacherID = null;
            	}else {
            		 studentID = null;
            	}
                if (addUserToDatabase(email, password, name, role, studentID, teacherID)) {
                  clearFieldsAndSetDefaultRole();
                  createUserAccountsView.updateStudentIDComboBox();
                  createUserAccountsView.updateTeacherIDComboBox();
//                  createUserAccountsView.displaySuccessMessage("User added successfully.");
                  JOptionPane.showMessageDialog(null, "User added successfully.", "Add User",
  						JOptionPane.INFORMATION_MESSAGE);
               

                } else {
                	createUserAccountsView.updateTeacherIDComboBox();
                	createUserAccountsView.updateStudentIDComboBox();
                    createUserAccountsView.displayErrorMessage("Failed to add user.");
                }
            }
        } else {
            createUserAccountsView.clearError();
            if (!isValidEmail(email)) {
                createUserAccountsView.displayEmailError("Invalid email format.");
            }
            if (!isValidPassword(password)) {
                createUserAccountsView.displayPasswordError("Password must be 6 characters or more.");
            }
            if (!isValidName(name)) {
                createUserAccountsView.displayNameError("Name must be 4 characters or more.");
            }
        }
    }
    
    private void clearFieldsAndSetDefaultRole() {
        // Clear text fields
        createUserAccountsView.emailField.setText("");
        createUserAccountsView.passwordField.setText("");
        createUserAccountsView.nameField.setText("");
        
        // Set role to "Teacher"
        createUserAccountsView.roleComboBox.setSelectedItem("Admin");
        
        // Clear studentID-related fields (if visible)
        createUserAccountsView.studentIDComboBox.setSelectedIndex(0);
    }
    private boolean isValidEmail(String email) {
        // Basic email validation using regex
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailPattern);
    }

    private boolean isValidPassword(String password) {
        // Check for password length
        return password.length() >= 6;
    }

    private boolean isValidName(String name) {
        // Check for name length
        return name.length() >= 4;
    }

    private boolean isEmailExists(String email) {
        try {
            Connection connection = DatabaseConnection.connectToBB();
            String query = "SELECT COUNT(*) FROM accounts WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Kết hợp mật khẩu và salt trước khi băm
            String passwordWithSalt = password + salt;

            byte[] hashedBytes = md.digest(passwordWithSalt.getBytes());

            // Chuyển đổi byte array thành chuỗi hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        
        // Chuyển đổi byte array thành chuỗi hex
        StringBuilder sb = new StringBuilder();
        for (byte b : saltBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    private boolean addUserToDatabase(String email, String password, String name, String role, String studentID, String teacherID) {
        try {
            Connection connection = DatabaseConnection.connectToBB();

            // Tạo salt mới cho mỗi lần thêm người dùng
            String salt = generateSalt();
            String hashedPassword = hashPassword(password, salt); // Mã hóa mật khẩu với salt

            String query = "INSERT INTO accounts (email, password, name, role, studentID, teacherID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, role);
            preparedStatement.setString(5, studentID);
            preparedStatement.setString(6, teacherID);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public String[] getStudentIDs() {
        try {
            Connection connection = DatabaseConnection.connectToBB();
            String query = "SELECT student.studentID, student.name " +
                           "FROM student " +
                           "LEFT JOIN accounts ON student.studentID = accounts.studentID " +
                           "WHERE accounts.studentID IS NULL";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> studentIDs = new ArrayList<>();
            while (resultSet.next()) {
                String studentID = resultSet.getString("studentID");
                String studentName = resultSet.getString("name");
                String formattedID = String.format("%s: %s", studentID, studentName);
                studentIDs.add(formattedID);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return studentIDs.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
    public String[] getTeacherIDs() {
        try {
            Connection connection = DatabaseConnection.connectToBB();
            String query = "SELECT teacher.teacherID, teacher.name " +
                           "FROM teacher " +
                           "LEFT JOIN accounts ON teacher.teacherID = accounts.teacherID " +
                           "WHERE accounts.teacherID IS NULL";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<String> teacherIDs = new ArrayList<>();
            while (resultSet.next()) {
                String teacherID = resultSet.getString("teacherID");
                String teacherName = resultSet.getString("name");
                String formattedID = String.format("%s: %s", teacherID, teacherName);
                teacherIDs.add(formattedID);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return teacherIDs.toArray(new String[0]);
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        }
    }



//    private class SubmitListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            handleCreateAccount();
//        }
//    }
//
//    private class BackListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            // Xử lý sự kiện khi người dùng nhấn nút "Back"
//            // Ví dụ: quay lại màn hình trước đó hoặc đóng cửa sổ hiện tại
//        }
//    }
}
