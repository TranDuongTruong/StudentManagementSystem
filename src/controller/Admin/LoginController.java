package controller.Admin;

import view.Admin.AdminHomeView;
import view.Admin.CreateUserAccountsView;
import view.Admin.LoginView;
import view.Student.StudentAccountMainView;
import view.Teacher.TeacherAccountMainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.DatabaseConnection;

public class LoginController {
    private final LoginView loginView;
    public static int studentId ;
    public static int teacherId ;
    public LoginController(LoginView loginView) {
        this.loginView = loginView;

        // Attach action listeners to the view's components
        loginView.addLoginListener(new LoginListener());
        loginView.addCancelListener(new CancelListener());
    }

    // ActionListener for the Login button
    private class LoginListener implements ActionListener {
    	 public void actionPerformed(ActionEvent e) {
    	        String email = loginView.getEmail();
    	        String password = loginView.getPassword();

    	        if (isValidEmail(email) && isValidPassword(password)) {
    	            String role = getRoleForEmail(email); // Lấy vai trò từ cơ sở dữ liệu

    	            if (isValidLogin(email, password)) {
    	                if ("admin".equals(role)) {
    	                    // Redirect to CreateUserAccountsView for admin
    	                    AdminHomeView adminView = new AdminHomeView();
    	                    adminView.setVisible(true);
    	                } else if("teacher".equals(role)) {
    	                    // Redirect to MainView for teacher 
    	                	TeacherAccountMainView mainView = new TeacherAccountMainView();
    	                    mainView.setVisible(true);
    	                }else {
    	                	studentId = getStudentIDForEmail(email);
    	                	StudentAccountMainView studentAccountMainView = new StudentAccountMainView();
    	                	studentAccountMainView.setVisible(true);

    	                }
    	                loginView.dispose(); // Đóng cửa sổ đăng nhập
    	            } else {
    	                loginView.displayErrorMessage("Login failed. Invalid email or password.");
    	            }
    	        } else if (isValidEmail(email)) {
    	            loginView.displayPasswordError("Password must be 6 characters or more");
    	        } else if (isValidPassword(password)) {
    	            loginView.displayEmailError("Invalid email format.");
    	        } else {
    	            loginView.displayErrorMessage("Invalid email or password format.");
    	        }
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

        private boolean isValidLogin(String email, String password) {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;


              try {
                  connection = DatabaseConnection.connectToBB(); // Get the database connection
                  String query = "SELECT * FROM accounts WHERE email = ? AND password = ?";        
                  statement = connection.prepareStatement(query);
                  statement.setString(1, email);
                  statement.setString(2, password);
              //    System.out.println(statement);


                resultSet = statement.executeQuery();
                //System.out.println(resultSet);
                // If a record is found, login is successful
                return resultSet.next();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            return false; // Login failed
        }
    }

    private String getRoleForEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.connectToBB();
            String query = "SELECT role FROM accounts WHERE email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return null; // Trả về null nếu không tìm thấy vai trò
    }
    // ActionListener for the Cancel button
    private class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    public void displayLoginView() {
        loginView.setVisible(true);
    }
    private int getStudentIDForEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.connectToBB();
            String query = "SELECT studentID FROM accounts WHERE email = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("studentID");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return -1; // Trả về -1 nếu không tìm thấy studentID
    }
}
