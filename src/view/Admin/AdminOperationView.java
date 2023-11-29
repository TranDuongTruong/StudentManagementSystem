package view.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Admin.AdminOperationController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;



public class AdminOperationView extends JFrame {

	private JPanel contentPane;
	private JTextField emailField;
	private JTextField nameField;
	private JTextField textField_ID;
	private JLabel errorLabel;
	private JLabel studentIDLabel;
	private JTextField studentIDField;
	private JTextField roleField;
	private JLabel teacherIDLabel;
	private JTextField teacherIDField;
	private JPasswordField passwordField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminOperationView frame = new AdminOperationView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminOperationView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		emailLabel.setBounds(61, 144, 83, 20);
		contentPane.add(emailLabel);
		
		emailField = new JTextField();
		emailField.setBounds(150, 144, 200, 20);
		contentPane.add(emailField);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLabel.setBounds(61, 175, 83, 20);
		contentPane.add(passwordLabel);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setBounds(61, 111, 83, 20);
		contentPane.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(150, 113, 200, 20);
		contentPane.add(nameField);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 44, 559, 13);
		contentPane.add(separator);
		

		JLabel setRoleLabel = new JLabel("Role:");
		setRoleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		setRoleLabel.setBounds(61, 211, 83, 20);
		contentPane.add(setRoleLabel);
		
		roleField = new JTextField();
		roleField.setFont(new Font("Tahoma", Font.BOLD, 14));
		roleField.setBounds(150, 213, 200, 20);
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
		ImageIcon updateIcon = new ImageIcon(AdminOperationView.class.getResource("/Assert/admin/submit.png"));
		updateButton.setIcon(updateIcon);
		updateButton.setBounds(61, 311, 103, 33);
		contentPane.add(updateButton);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHomeView adminhomeView = new AdminHomeView();
            	adminhomeView.setVisible(true);
                dispose();
			}
		});
		ImageIcon backIcon = new ImageIcon(AdminOperationView.class.getResource("/Assert/admin/back.png"));
        backButton.setIcon(backIcon);
		backButton.setBounds(328, 311, 103, 33);
		contentPane.add(backButton);
		
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(61, 280, 335, 20);
		contentPane.add(errorLabel);
		
		studentIDLabel = new JLabel("Student ID:");
	    studentIDLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
	    studentIDLabel.setBounds(61, 249, 83, 20);
	    contentPane.add(studentIDLabel);

	    studentIDField = new JTextField();
	    studentIDField.setBounds(150, 249, 200, 20);
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
		lblId.setBounds(61, 80, 83, 20);
		contentPane.add(lblId);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(150, 82, 200, 20);
		contentPane.add(textField_ID);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminOperationController.deleteButtonClicked(textField_ID.getText(), emailField, passwordField, 
																		nameField, roleField, errorLabel,studentIDLabel, studentIDField, teacherIDLabel, teacherIDField);
			}
		});
		ImageIcon deleteIcon = new ImageIcon(AdminOperationView.class.getResource("/Assert/admin/cancel1.png"));
		deleteButton.setIcon(deleteIcon);
		deleteButton.setBounds(190, 311, 103, 33);
		contentPane.add(deleteButton);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                AdminOperationController.searchButtonClicked(textField_ID.getText(), emailField, passwordField, nameField, roleField, errorLabel,studentIDLabel, studentIDField,teacherIDLabel, teacherIDField);
            }
		});
		ImageIcon searchIcon = new ImageIcon(AdminOperationView.class.getResource("/Assert/admin/loupe.png"));
		btnSearch.setIcon(searchIcon);
		btnSearch.setBounds(424, 77, 103, 30);
		contentPane.add(btnSearch);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 177, 200, 20);
		contentPane.add(passwordField);
		
		
		// Add focus listeners to clear error status when the user starts typing
		emailField.addFocusListener(new FocusAdapter() {
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
