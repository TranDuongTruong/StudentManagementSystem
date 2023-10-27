package controller;

import view.CreateUserAccountsView;
import view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        if (isValidEmail(email) && isValidPassword(password) && isValidName(name)) {
            if (isEmailExists(email)) {
                createUserAccountsView.displayEmailError("Email already exists. Please choose another email.");
            } else {
                if (addUserToDatabase(email, password, name, role)) {
                    createUserAccountsView.clearError();
//                  createUserAccountsView.displaySuccessMessage("User added successfully.");
                    LoginController loginController = new LoginController(new LoginView());
                    loginController.displayLoginView();
                    createUserAccountsView.dispose(); // Close the registration view
                } else {
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

    private boolean addUserToDatabase(String email, String password, String name, String role) {
        try {
            Connection connection = DatabaseConnection.connectToBB();
            String query = "INSERT INTO accounts (email, password, name, role) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, role);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
