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
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomeView extends JFrame {

	private JPanel contentPane;
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
		setBounds(100, 100, 1021, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHome.setBounds(10, 0, 77, 26);
		contentPane.add(lblHome);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 36, 720, 13);
		contentPane.add(separator);
		 // Logo Image
        ImageIcon logoIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/logo.png"));
		ImageIcon addAdminIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/addAdmin.png"));
		ImageIcon configurationIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/configuration1.png"));
		ImageIcon showAdminIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/showAdmin.png"));
		
		 btnCreateUser = new JButton("Create User");
//		btnCreateUser.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				CreateUserAccountsView createUserAccountsView = new CreateUserAccountsView();
//				createUserAccountsView.setVisible(true);
//                dispose();
//			}
//		});
		btnCreateUser.setBounds(73, 0, 119, 30);
		contentPane.add(btnCreateUser);
		
		 btnOperation = new JButton("Operation");
		btnOperation.setBounds(187, 0, 119, 30);
//		btnOperation.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				AdminOperationView adminOperationView = new AdminOperationView();
//				adminOperationView.setVisible(true);
//                dispose();
//			}
//
//		});
		
		contentPane.add(btnOperation);
		
		 btnShowUser = new JButton("Show User");
//		btnShowUser.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				   DisplayUserAccountsView displayUserAccountsView = new DisplayUserAccountsView();
//			       DisplayUserAccountsController controller = new DisplayUserAccountsController(displayUserAccountsView);
//			       controller.displayUserView();
//			       dispose(); 
//			}
//
//
//
//		});
		btnShowUser.setBounds(309, 0, 119, 30);
		contentPane.add(btnShowUser);
		
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
		btnNewButton_3.setBounds(888, 0, 119, 30);
		contentPane.add(btnNewButton_3);
		ImageIcon accdemicIcon = new ImageIcon(AdminHomeView.class.getResource("/Assert/admin/showAdmin.png"));
		
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
		
		 btnClasses = new JButton("Classes");
//		btnClasses.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				ClassesAdminView frame = new ClassesAdminView();
//				frame.setVisible(true);
//				setVisible(false);
//			}
//		});
		btnClasses.setBounds(546, 0, 119, 30);
		contentPane.add(btnClasses);
		
		 MainContent = new JPanel();
		MainContent.setBounds(10, 40, 997, 590);
		contentPane.add(MainContent);
		MainContent.setLayout(null);
		
		JLabel logoLabel = new JLabel("logoLable");
		logoLabel.setBounds(350, 63, 305, 282);
		MainContent.add(logoLabel);
		logoLabel.setIcon(logoIcon);
		setButtonListener();
		
	}
}
