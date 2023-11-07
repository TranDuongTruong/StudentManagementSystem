package view.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.border.EmptyBorder;

import controller.Admin.LoginController;

import java.awt.event.ActionEvent;

public class LoginView extends JFrame {
    private JPanel contentPane;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private JLabel errorLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView frame = new LoginView();
                    LoginController controller = new LoginController(frame); // Pass the view to the controller
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginView() {
    	
        initializeUI();
        LoginController controller = new LoginController(this); // Pass the view to the controller
        setVisible(true);
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 503, 371);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Logo Image
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(LoginView.class.getResource("/Assert/admin/login.png"));
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
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        ImageIcon loginIcon = new ImageIcon(LoginView.class.getResource("/Assert/admin/login1.png"));
        loginButton.setIcon(loginIcon);
        loginButton.setBounds(77, 229, 100, 30);
        contentPane.add(loginButton);

        // Cancel Button
        cancelButton = new JButton("Cancel");
        ImageIcon cancelIcon = new ImageIcon(LoginView.class.getResource("/Assert/admin/cancel1.png"));
        cancelButton.setIcon(cancelIcon);
        cancelButton.setBounds(260, 229, 111, 30);
        contentPane.add(cancelButton);

        // Error Label for displaying error messages
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(77, 260, 300, 20);
        contentPane.add(errorLabel);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 11, 58, 30);
        contentPane.add(lblNewLabel);
        
        // Add focus listeners to clear error status when the user starts typing
        emailField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                clearError();
            }
        });

        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                clearError();
            }
        });
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addCancelListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void displayEmailError(String message) {
        errorLabel.setText(message);
        emailField.setBackground(Color.PINK);
    }

    public void displayPasswordError(String message) {
        errorLabel.setText(message);
        passwordField.setBackground(Color.PINK);
    }
    public void displayErrorMessage(String message) {
        errorLabel.setText(message);

        // Highlight email and password fields in red
        emailField.setBackground(Color.PINK);
        passwordField.setBackground(Color.PINK);
    }


    public void clearError() {
        errorLabel.setText("");
        emailField.setBackground(Color.WHITE);
        passwordField.setBackground(Color.WHITE);
    }
}
