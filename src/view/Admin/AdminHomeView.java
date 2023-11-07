package view.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Admin.DisplayUserAccountsController;
import controller.Admin.LoginController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomeView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHomeView frame = new AdminHomeView();
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
	public AdminHomeView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHome.setBounds(0, 0, 322, 30);
		contentPane.add(lblHome);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 25, 746, 13);
		contentPane.add(separator);
		
		JLabel logoLabel = new JLabel("logoLable");
		 // Logo Image
        ImageIcon logoIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/logo.png"));
        logoLabel.setIcon(logoIcon);
        logoLabel.setBounds(223, 41, 291, 155);
		contentPane.add(logoLabel);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon addAdminIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/addAdmin.png"));
		lblNewLabel.setIcon(addAdminIcon);
		lblNewLabel.setBounds(20, 219, 178, 128);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		ImageIcon configurationIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/configuration1.png"));
		lblNewLabel_1.setIcon(configurationIcon);
		lblNewLabel_1.setBounds(261, 219, 178, 128);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		ImageIcon showAdminIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/showAdmin.png"));
		lblNewLabel_2.setIcon(showAdminIcon);
		lblNewLabel_2.setBounds(505, 219, 178, 128);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Create User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUserAccountsView createUserAccountsView = new CreateUserAccountsView();
				createUserAccountsView.setVisible(true);
                dispose();
			}
		});
		btnNewButton.setBounds(39, 392, 103, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Operation");
		btnNewButton_1.setBounds(273, 392, 98, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminOperationView adminOperationView = new AdminOperationView();
				adminOperationView.setVisible(true);
                dispose();
			}

		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Show User");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   DisplayUserAccountsView displayUserAccountsView = new DisplayUserAccountsView();
			       DisplayUserAccountsController controller = new DisplayUserAccountsController(displayUserAccountsView);
			       controller.displayUserView();
			       dispose(); 
			}



		});
		btnNewButton_2.setBounds(513, 392, 119, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Logout");
		ImageIcon logoutIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/logout.png"));
		btnNewButton_3.setIcon(logoutIcon);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController loginController = new LoginController(new LoginView());
                loginController.displayLoginView();
                dispose();

			}
		});
		btnNewButton_3.setBounds(564, 25, 119, 31);
		contentPane.add(btnNewButton_3);
	}
}
