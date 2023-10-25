package controller;

import view.LoginView;
import view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginController {
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;

        // Attach action listeners to the view's components
        loginView.addLoginListener(new LoginListener());
        loginView.addCancelListener(new CancelListener());
    }

    // ActionListener for the Login button
    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Retrieve email and password from the view
            String email = loginView.getEmail();
            String password = loginView.getPassword();

            if (isValidLogin(email, password)) {
//            	loginView.displayErrorMessage("Login successful!");
                MainView mainView = new MainView();
                mainView.setVisible(true);
                loginView.dispose(); // Close the login view
            } else {
                loginView.displayErrorMessage("Login failed. Invalid email or password.");
            }
        }

        private boolean isValidLogin(String email, String password) {
        	  Connection connection = null;
              PreparedStatement statement = null;
              ResultSet resultSet = null;

              try {
                  connection = DatabaseConnection.connectToBB(); // Get the database connection
                  String query = "SELECT * FROM admin WHERE mail = ? AND password = ?";            statement = connection.prepareStatement(query);
                  statement.setString(1, email);
                  statement.setString(2, password);

                  resultSet = statement.executeQuery();

                  // If a record is found, login is successful
                  return resultSet.next();
              } catch (SQLException e) {
                  e.printStackTrace();
              } finally {
                  try {
                      if (resultSet != null) resultSet.close();
                      if (statement != null) statement.close();
                      if (connection != null) connection.close();
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
              }

              return false; // Login failed
        }
    }

    // ActionListener for the Cancel button
    private class CancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
