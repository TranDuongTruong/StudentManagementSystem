package view.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.EmptyBorder;

import controller.Admin.CreateUserAccountsController;

import java.awt.event.ActionEvent;

public class CreateUserAccountsView extends JFrame {
    private JPanel contentPane;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JComboBox<String> roleComboBox;
    private JButton submitButton;
    private JButton backButton;
    private JLabel errorLabel;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateUserAccountsView frame = new CreateUserAccountsView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public CreateUserAccountsView() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 327);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        emailLabel.setBounds(61, 99, 83, 20);
        contentPane.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 99, 200, 20);
        contentPane.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordLabel.setBounds(61, 130, 83, 20);
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 132, 200, 20);
        contentPane.add(passwordField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        nameLabel.setBounds(61, 66, 83, 20);
        contentPane.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 68, 200, 20);
        contentPane.add(nameField);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 44, 424, 13);
        contentPane.add(separator);

        roleComboBox = new JComboBox<String>();
        roleComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        roleComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Student", "Admin", "Teacher"}));
        roleComboBox.setBounds(150, 168, 200, 20);
        contentPane.add(roleComboBox);

        JLabel setRoleLabel = new JLabel("Set role:");
        setRoleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        setRoleLabel.setBounds(61, 166, 83, 20);
        contentPane.add(setRoleLabel);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Xử lý sự kiện khi người dùng nhấn nút "Submit"
                CreateUserAccountsController controller = new CreateUserAccountsController(CreateUserAccountsView.this);
                controller.handleCreateAccount(); 
            }
        });
        ImageIcon submitIcon = new ImageIcon(CreateUserAccountsView.class.getResource("/Assert/admin/submit.png"));
        submitButton.setIcon(submitIcon);

        submitButton.setBounds(61, 223, 103, 30);
        contentPane.add(submitButton);

        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        ImageIcon backIcon = new ImageIcon(CreateUserAccountsView.class.getResource("/Assert/admin/back.png"));
        backButton.setIcon(backIcon);
        backButton.setBounds(234, 223, 103, 30);
        contentPane.add(backButton);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AdminHomeView adminhomeView = new AdminHomeView();
            	adminhomeView.setVisible(true);
                dispose();
            }
        });

        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setBounds(61, 260, 300, 20);
        contentPane.add(errorLabel);
        
        JLabel lblAdmin = new JLabel("Admin: Create User Accounts");
        lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblAdmin.setBounds(10, 11, 322, 30);
        contentPane.add(lblAdmin);

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

        nameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                clearError();
            }
        });
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getName() {
        return nameField.getText();
    }

    public String getRole() {
        return roleComboBox.getSelectedItem().toString();
    }

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addBackListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    public void displayEmailError(String message) {
        errorLabel.setText(message);
        emailField.setBackground(Color.PINK);
        errorLabel.setForeground(Color.RED);
    }

    public void displayPasswordError(String message) {
        errorLabel.setText(message);
        passwordField.setBackground(Color.PINK);
        errorLabel.setForeground(Color.RED);
    }

    public void displayNameError(String message) {
        errorLabel.setText(message);
        nameField.setBackground(Color.PINK);
        errorLabel.setForeground(Color.RED);
    }

    public void clearError() {
        errorLabel.setText("");
        emailField.setBackground(Color.WHITE);
        passwordField.setBackground(Color.WHITE);
        nameField.setBackground(Color.WHITE);
    }
    public void displayErrorMessage(String message) {
        errorLabel.setText(message);
        errorLabel.setForeground(Color.RED);
    }

    public void displaySuccessMessage(String message) {
        errorLabel.setText(message);
        errorLabel.setForeground(Color.GREEN);
    }
}
