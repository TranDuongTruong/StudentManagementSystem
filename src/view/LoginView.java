package view;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import java.awt.event.ActionEvent;


public class LoginView extends JFrame {
    private JPanel contentPane;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    
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
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        ImageIcon loginIcon = new ImageIcon(LoginView.class.getResource("/Assert/login1.png"));
        loginButton.setIcon(loginIcon);
        loginButton.setBounds(77, 229, 100, 30);
        contentPane.add(loginButton);

        // Cancel Button
        cancelButton = new JButton("Cancel");
        ImageIcon cancelIcon = new ImageIcon(LoginView.class.getResource("/Assert/cancel1.png"));
        cancelButton.setIcon(cancelIcon);
        cancelButton.setBounds(260, 229, 111, 30);
        contentPane.add(cancelButton);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 11, 58, 30);
        contentPane.add(lblNewLabel);
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

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
