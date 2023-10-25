package view;

import java.awt.EventQueue;
import java.awt.Font;

import controller.DatabaseConnection;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginView extends JFrame {

    private JPanel contentPane;
    private JTextField emailField;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView frame = new LoginView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // Method to check admin login
    private boolean checkAdminLogin(String email, String password) {
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

    public LoginView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 503, 371);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Logo Image
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(LoginView.class.getResource("/Assert/login.png"));
        logoLabel.setIcon(logoIcon);
        logoLabel.setBounds(150, 11, 200, 112);
        contentPane.add(logoLabel);

        // Email Label and Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        emailLabel.setBounds(77, 150, 83, 20);
        contentPane.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(170, 150, 200, 20);
        contentPane.add(emailField);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordLabel.setBounds(77, 180, 83, 20);
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(170, 180, 200, 20);
        contentPane.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");   
        ImageIcon loginIcon = new ImageIcon(LoginView.class.getResource("/Assert/login1.png"));
        loginButton.setIcon(loginIcon);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieve email and password from input fields
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Query the database for a matching admin record
                if (checkAdminLogin(email, password)) {
                    // Successful login logic (e.g., open the main application window)
//                  JOptionPane.showMessageDialog(null, "Login successful!");
	                MainView mainView = new MainView();
	                mainView.setVisible(true);
	                dispose();
                } else {
                    // Failed login logic (e.g., display an error message)
                    JOptionPane.showMessageDialog(null, "Login failed. Invalid email or password.");
                }
            }
        });
        loginButton.setBounds(77, 229, 100, 30);
        contentPane.add(loginButton);

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        ImageIcon cancelIcon = new ImageIcon(LoginView.class.getResource("/Assert/cancel1.png"));
        cancelButton.setIcon(cancelIcon);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        cancelButton.setBounds(260, 229, 111, 30);
        contentPane.add(cancelButton);
        
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 11, 58, 30);
        contentPane.add(lblNewLabel);
    }
}