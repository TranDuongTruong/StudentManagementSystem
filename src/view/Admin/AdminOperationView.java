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
import java.awt.event.ActionEvent;



public class AdminOperationView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Email;
	private JTextField textField_Name;
	private JTextField textField_ID;
	private JTextField passwordField;

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
		setBounds(100, 100, 539, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		emailLabel.setBounds(61, 144, 83, 20);
		contentPane.add(emailLabel);
		
		textField_Email = new JTextField();
		textField_Email.setBounds(150, 144, 200, 20);
		contentPane.add(textField_Email);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordLabel.setBounds(61, 175, 83, 20);
		contentPane.add(passwordLabel);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		nameLabel.setBounds(61, 111, 83, 20);
		contentPane.add(nameLabel);
		
		textField_Name = new JTextField();
		textField_Name.setBounds(150, 113, 200, 20);
		contentPane.add(textField_Name);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 44, 523, 13);
		contentPane.add(separator);
		
		final JComboBox<String> roleComboBox = new JComboBox<String>();
		roleComboBox.setModel(new DefaultComboBoxModel(new String[] {"Student", "Admin", "Teacher"}));
		roleComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		roleComboBox.setBounds(150, 213, 200, 20);
		contentPane.add(roleComboBox);
		
		JLabel setRoleLabel = new JLabel("Role:");
		setRoleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		setRoleLabel.setBounds(61, 211, 83, 20);
		contentPane.add(setRoleLabel);
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminOperationController.updateButtonClicked(textField_ID.getText(), textField_Email, passwordField, 
						textField_Name, roleComboBox);
			}
		});
		ImageIcon updateIcon = new ImageIcon(AdminOperationView.class.getResource("/Assert/admin/submit.png"));
		updateButton.setIcon(updateIcon);
		updateButton.setBounds(61, 263, 103, 30);
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
		backButton.setBounds(293, 263, 103, 30);
		contentPane.add(backButton);
		
		JLabel errorLabel = new JLabel("");
		errorLabel.setForeground(Color.RED);
		errorLabel.setBounds(61, 312, 335, 20);
		contentPane.add(errorLabel);
		
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
				AdminOperationController.deleteButtonClicked(textField_ID.getText(), textField_Email, passwordField, 
																		textField_Name, roleComboBox);
			}
		});
		ImageIcon deleteIcon = new ImageIcon(AdminOperationView.class.getResource("/Assert/admin/cancel1.png"));
		deleteButton.setIcon(deleteIcon);
		deleteButton.setBounds(180, 263, 103, 30);
		contentPane.add(deleteButton);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                AdminOperationController.searchButtonClicked(textField_ID.getText(), textField_Email, passwordField, textField_Name, roleComboBox);
            }
		});
		ImageIcon searchIcon = new ImageIcon(AdminOperationView.class.getResource("/Assert/admin/loupe.png"));
		btnSearch.setIcon(searchIcon);
		btnSearch.setBounds(396, 77, 103, 30);
		contentPane.add(btnSearch);
		
		passwordField = new JTextField();
		passwordField.setBounds(150, 177, 200, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
	}
}
