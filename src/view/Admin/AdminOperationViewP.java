package view.Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.Admin.AdminOperationController;

public class AdminOperationViewP extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField emailField;
	private JTextField nameField;
	private JTextField textField_ID;
	private JTextField passwordField;
	private JLabel errorLabel;
	private JLabel studentIDLabel;
	private JTextField studentIDField;
	private JTextField roleField;
	private JLabel teacherIDLabel;
	private JTextField teacherIDField;


	/**
	 * Create the panel.
	 */
	public AdminOperationViewP() {
		 setBounds(162, 0, 835, 640);
		setLayout(null);
		contentPane = new JPanel();
		contentPane.setBounds(10, 51, 815, 629);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPane);
		contentPane.setLayout(null);
		
	
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		emailLabel.setBounds(10, 144, 134, 20);
		contentPane.add(emailLabel);
		
		emailField = new JTextField();
		emailField.setBounds(150, 144, 409, 20);
		contentPane.add(emailField);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLabel.setBounds(10, 175, 134, 20);
		contentPane.add(passwordLabel);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setBounds(10, 111, 134, 20);
		contentPane.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(150, 113, 409, 20);
		contentPane.add(nameField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 44, 559, 13);
		contentPane.add(separator);
		

		JLabel setRoleLabel = new JLabel("Role:");
		setRoleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		setRoleLabel.setBounds(10, 211, 134, 20);
		contentPane.add(setRoleLabel);
		
		roleField = new JTextField();
		roleField.setFont(new Font("Tahoma", Font.BOLD, 14));
		roleField.setBounds(150, 213, 409, 20);
		contentPane.add(roleField);
		roleField.setEditable(false);
		
		roleField.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminOperationController.updateButtonClicked(textField_ID.getText(), emailField, passwordField, 
						nameField, roleField, errorLabel,studentIDLabel, studentIDField, teacherIDLabel, teacherIDField);
			}
		});
		ImageIcon updateIcon = new ImageIcon(AdminOperationViewP.class.getResource("/Assert/admin/submit.png"));
		updateButton.setIcon(updateIcon);
		updateButton.setBounds(245, 311, 103, 33);
		contentPane.add(updateButton);
		ImageIcon backIcon = new ImageIcon(AdminOperationViewP.class.getResource("/Assert/admin/back.png"));
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(61, 280, 335, 20);
		contentPane.add(errorLabel);
		
		 studentIDLabel = new JLabel("Student ID:");
	     studentIDLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	     studentIDLabel.setBounds(10, 249, 134, 20);
	     contentPane.add(studentIDLabel);

	     studentIDField = new JTextField();
	     studentIDField.setBounds(150, 249, 409, 20);
	     contentPane.add(studentIDField);
	     
	     teacherIDLabel = new JLabel("Teacher ID:");
	     teacherIDLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	     teacherIDLabel.setBounds(61, 249, 83, 20);
	     contentPane.add(teacherIDLabel);

	     teacherIDField = new JTextField();
	     teacherIDField.setBounds(150, 249, 200, 20);
	     contentPane.add(teacherIDField);
		
		JLabel lblAdmin = new JLabel("Admin: Operation");
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdmin.setBounds(10, 11, 322, 30);
		contentPane.add(lblAdmin);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(10, 80, 134, 20);
		contentPane.add(lblId);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(150, 82, 409, 20);
		contentPane.add(textField_ID);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminOperationController.deleteButtonClicked(textField_ID.getText(), emailField, passwordField, 
																		nameField, roleField, errorLabel,studentIDLabel, studentIDField, teacherIDLabel, teacherIDField);
			}
		});
		ImageIcon deleteIcon = new ImageIcon(AdminOperationViewP.class.getResource("/Assert/admin/cancel1.png"));
		deleteButton.setIcon(deleteIcon);
		deleteButton.setBounds(385, 311, 103, 33);
		contentPane.add(deleteButton);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                AdminOperationController.searchButtonClicked(textField_ID.getText(), emailField, passwordField, nameField, roleField, errorLabel,studentIDLabel, studentIDField, teacherIDLabel, teacherIDField);
            }
		});
		ImageIcon searchIcon = new ImageIcon(AdminOperationViewP.class.getResource("/Assert/admin/loupe.png"));
		btnSearch.setIcon(searchIcon);
		btnSearch.setBounds(597, 77, 103, 30);
		contentPane.add(btnSearch);
		
		passwordField = new JTextField();
		passwordField.setBounds(150, 177, 409, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		
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
        
        
        updateStudentIDFieldVisibility(roleField.getText().trim().toLowerCase());
	}
	private void clearError() {
		 errorLabel.setText("");
	     emailField.setBackground(Color.WHITE);
	     passwordField.setBackground(Color.WHITE);
	     nameField.setBackground(Color.WHITE);
		
	}

private void updateStudentIDFieldVisibility(String selectedRole) {
    if ("student".equals(selectedRole)) {
        System.out.println("Displaying studentIDField");
        studentIDLabel.setVisible(true);
        studentIDField.setVisible(true);
        studentIDField.setEditable(false);

        System.out.println("Hiding teacherIDField");
        teacherIDLabel.setVisible(false);
        teacherIDField.setVisible(false);
        teacherIDField.setEditable(false);
    } else if ("teacher".equals(selectedRole)) {
        System.out.println("Displaying teacherIDField");
        teacherIDLabel.setVisible(true);
        teacherIDField.setVisible(true);
        teacherIDField.setEditable(false);

        System.out.println("Hiding studentIDField");
        studentIDLabel.setVisible(false);
        studentIDField.setVisible(false);
        studentIDField.setEditable(false);
    } else {
        System.out.println("Hiding both studentIDField and teacherIDField");
        studentIDLabel.setVisible(false);
        studentIDField.setVisible(false);
        studentIDField.setEditable(false);

        teacherIDLabel.setVisible(false);
        teacherIDField.setVisible(false);
        teacherIDField.setEditable(false);
    }
}
}
