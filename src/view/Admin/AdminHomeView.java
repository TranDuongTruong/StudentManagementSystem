package view.Admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Admin.DisplayUserAccountsController;
import controller.Admin.LoginController;
import view.Teacher.PerformanceView_Admin;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomeView extends JFrame {

	private JPanel contentPane;
<<<<<<< HEAD
	JPanel MainContent;
	JButton btnCreateUser;
	JButton btnOperation;
	JButton btnShowUser ;
	JButton btnPerformance;
	JButton btnClasses;
	
	final AdminOperationViewP operation=new AdminOperationViewP();
	
	final CreateUserAccoutViewP createUser=new CreateUserAccoutViewP();
	final ShowUserAccountP showUser=new ShowUserAccountP();
	final ClassesAdminViewP classes=new ClassesAdminViewP();
	final AccademicPerfromanceP performace=new AccademicPerfromanceP();
	boolean hasMainContent = false;
	boolean hasCreateUser = false;
	 boolean hasOperation = false;
	 boolean hasShowUser = false;
	 boolean hasPerformance = false;
	 boolean hasClasses = false;
	
	 public void setButtonListener() {

		 btnOperation.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	

			    	contentPane.remove(MainContent);removeContent();
			    	
			    	contentPane.add(operation);
			    	contentPane.invalidate();
			    	contentPane.repaint();
			    	hasOperation=true;
			    }
			});
		 
		 btnCreateUser.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	

			    	contentPane.remove(MainContent);removeContent();
			    	
			    	contentPane.add(createUser);
			    	contentPane.invalidate();
			    	contentPane.repaint();
			    	hasCreateUser=true;
			    }
			});
		 btnShowUser.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	

			    	contentPane.remove(MainContent);removeContent();
			    	DisplayUserAccountsController controller = new DisplayUserAccountsController(showUser);
				       controller.displayUserView();
			    	contentPane.add(showUser);
			    	contentPane.invalidate();
			    	contentPane.repaint();
			    	hasShowUser=true;
			    }
			});
		 btnClasses.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	

			    	contentPane.remove(MainContent);removeContent();			    	
			    	contentPane.add(classes);
			    	contentPane.invalidate();
			    	contentPane.repaint();
			    	hasClasses=true;
			    }
			});
		 btnPerformance.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	

			    	contentPane.remove(MainContent);removeContent();			    	
			    	contentPane.add(performace);
			    	contentPane.invalidate();
			    	contentPane.repaint();
			    	hasPerformance=true;
			    }
			});
		 
	 }


public void removeContent() {
	if (hasOperation) {
		  contentPane.remove(operation);
		  hasOperation=false;
		}
	if (hasCreateUser) {
		  contentPane.remove(createUser);
		  hasCreateUser=false;
		}
	
	if (hasShowUser) {
		  contentPane.remove(showUser);
		  hasShowUser=false;
		}
	if (hasClasses) {
		  contentPane.remove(classes);
		  hasClasses=false;
		}
	if (hasPerformance) {
		  contentPane.remove(performace);
		  hasPerformance=false;
		}
}
=======

>>>>>>> 83b9dcdeccf9026f4c82dc69010ae11fa0c9c57b
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
		setBounds(100, 100, 899, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHome.setBounds(0, 0, 322, 30);
		contentPane.add(lblHome);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 25, 769, 13);
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
		lblNewLabel_1.setBounds(193, 219, 178, 128);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		ImageIcon showAdminIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/showAdmin.png"));
		lblNewLabel_2.setIcon(showAdminIcon);
		lblNewLabel_2.setBounds(363, 219, 128, 128);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Create User");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateUserAccountsView createUserAccountsView = new CreateUserAccountsView();
				createUserAccountsView.setVisible(true);
                dispose();
			}
		});
		btnNewButton.setBounds(20, 392, 119, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Operation");
		btnNewButton_1.setBounds(193, 392, 119, 23);
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
		btnNewButton_2.setBounds(363, 392, 119, 23);
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
		btnNewButton_3.setBounds(766, 23, 119, 31);
		contentPane.add(btnNewButton_3);
		
<<<<<<< HEAD
		 btnPerformance = new JButton("Academic Performance");
		btnPerformance.setBounds(428, 0, 119, 30);
		/*
		 * btnPerformance.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent e) { PerformanceView_Admin frame = new
		 * PerformanceView_Admin(); frame.setVisible(true); dispose();
		 * 
		 * } });
		 */
		contentPane.add(btnPerformance);
=======
		JLabel accdemic = new JLabel("");
		accdemic.setBounds(514, 219, 143, 140);

		ImageIcon accdemicIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/accademicPerformance.png"));
		Image image = accdemicIcon.getImage().getScaledInstance(accdemic.getWidth(), accdemic.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(image);
		accdemic.setIcon(scaledIcon);

		contentPane.add(accdemic);
		
		JButton btnAccade = new JButton("Academic Performance");
		btnAccade.setBounds(514, 392, 178, 23);
		btnAccade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PerformanceView_Admin frame = new PerformanceView_Admin();
                frame.setVisible(true);
                dispose();

			}
		});
		contentPane.add(btnAccade);
>>>>>>> 83b9dcdeccf9026f4c82dc69010ae11fa0c9c57b
		
		JButton btnClasses = new JButton("Classes");
		btnClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassesAdminView frame = new ClassesAdminView();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnClasses.setBounds(714, 392, 119, 22);
		contentPane.add(btnClasses);
		
		JLabel accdemic_1 = new JLabel("");
		accdemic_1.setBounds(702, 219, 143, 140);
		accdemic_1.setIcon(scaledIcon);
		contentPane.add(accdemic_1);
		
		
	}
}
