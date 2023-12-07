package view.Teacher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import controller.Admin.LoginController;

import model.ClassesManager;
import model.Classroom;
import view.Admin.LoginView;
import view.style.GradientButton;
import view.style.GradientPanel;

import java.awt.Color;
import javax.swing.JMenu;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
public class TeacherAccountMainView extends JFrame {
	JPanel content_panel;
	 JButton btn_blog;
	 JButton btn_Schedule;
	 JButton btn_classes ;
	  JButton btn_DashBoard;
	private static final long serialVersionUID = 1L;
	private JPanel main_Panel;
	final ClassesViewP classes=new ClassesViewP(this);
	final DashboardView dashboard=new DashboardView();
	 ManageScroresViewP manageScoreView;
	ScheduleTeacherView scheduleView =new ScheduleTeacherView();
	DashboardTeacherView dashboardTeacherView=new DashboardTeacherView();
	StudentViewP student;
	AttendanceViewP attendance;
	boolean hasAttendance=false;
	boolean hasClassesView = false;
	boolean hasDashboardView=true;
	boolean hasStudentView = false;
	 boolean hasBlogView = false;
	 boolean hasManageScore=false;
	 boolean hasScheduleView=false;
	 boolean hasDashboardTeacherView=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					LoginController.teacherId=9;
					TeacherAccountMainView frame = new TeacherAccountMainView();
				//	MainViewCtrl_Teacher mainView=new MainViewCtrl_Teacher(frame);
					frame.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void removeContent() {
		if (hasDashboardView) {
			main_Panel.remove(dashboard);
			btn_DashBoard.setBackground(new Color(192, 192, 192));;
			hasDashboardView=false;
			}
		if (hasClassesView) {
			main_Panel.remove(classes);
			btn_classes.setBackground(new Color(192, 192, 192));;
			hasClassesView=false;
			}
		  if (hasStudentView) {
		    main_Panel.remove(student);
		    hasStudentView = false;
		  }
		  if (hasAttendance) {
			    main_Panel.remove(attendance);
			    hasAttendance = false;
			  }
		  if (hasManageScore) {
			    main_Panel.remove(manageScoreView);
			    hasManageScore = false;
			  }
		 
		  if (hasScheduleView) {
			    main_Panel.remove(scheduleView);
			    hasScheduleView = false;
			  }
		  
		  if (hasDashboardTeacherView) {
			    main_Panel.remove(dashboardTeacherView);
			    hasDashboardTeacherView = false;
			  }
	}

	public void setButtonListener() {

		btn_classes.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {	

		    	main_Panel.remove(content_panel);
		    	removeContent();
		    	//btn_classes.setBackground(new Color(255, 255, 255));
		    	main_Panel.add(classes);
		    	main_Panel.invalidate();
		    	main_Panel.repaint();hasClassesView=true;
		    }
		});
		btn_Schedule.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {	

		    	main_Panel.remove(content_panel);
		    	removeContent();
		    	//btn_classes.setBackground(new Color(255, 255, 255));
		    	main_Panel.add(scheduleView);
		    	main_Panel.invalidate();
		    	main_Panel.repaint();hasScheduleView=true;
		    }
		});
		btn_DashBoard.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {	

		    	main_Panel.remove(content_panel);
		    	removeContent();
		    	//btn_classes.setBackground(new Color(255, 255, 255));
		    	main_Panel.add(dashboardTeacherView);
		    	main_Panel.invalidate();
		    	main_Panel.repaint();hasDashboardTeacherView=true;
		    }
		});
		
		
	}
	public void completeAttendance() {
		main_Panel.remove(content_panel);
		btn_classes.setBackground(new Color(255, 255, 255));
		removeContent();
    	main_Panel.add(classes);
    	main_Panel.invalidate();
    	main_Panel.repaint();hasClassesView=true;
	}
	public void loadStudentView(Classroom classRoom) {
		
		
		student=new StudentViewP(classRoom);
		
		
		main_Panel.remove(content_panel);
    	removeContent();
    	
    	main_Panel.add(student);
    	main_Panel.invalidate();
    	main_Panel.repaint();hasStudentView=true;
		
	}
	public void loadManageScoreView(Classroom classRoom, String classCode) {
		
		
		manageScoreView=new ManageScroresViewP(classRoom, classCode);
		
		
		main_Panel.remove(content_panel);
    	removeContent();
    	
    	main_Panel.add(manageScoreView);
    	main_Panel.invalidate();
    	main_Panel.repaint();hasManageScore=true;
		
	}
	public void loadAttendanceView(Classroom classRoom) {
		
		
		attendance=new AttendanceViewP(classRoom,this);
		
		
		main_Panel.remove(content_panel);
    	removeContent();
    	
    	main_Panel.add(attendance);
    	main_Panel.invalidate();
    	main_Panel.repaint();hasAttendance=true;
		
	}
	public TeacherAccountMainView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1021, 677);
		main_Panel = new JPanel();
		main_Panel.setBackground(new Color(255, 255, 255));
		main_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main_Panel);
		main_Panel.setLayout(null);
		
		ImageIcon imageIcon = new ImageIcon();
		 URL imageUrl=TeacherAccountMainView.class.getClassLoader().getResource("Assert/teacher/DTULogo.png");
	            File file = new File(imageUrl.getPath());
	              imageIcon = new ImageIcon(file.getAbsolutePath());
	              System.out.println(file.getAbsolutePath());
	            


	        // Lấy hình ảnh từ ImageIcon
	        Image image = imageIcon.getImage();
	        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);		
	        int imageWidth = image.getWidth(null);
	        int imageHeight = image.getHeight(null);

	        
	        ImageIcon scaledImageIcon = new ImageIcon(
	         image.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
			        System.out.println(imageWidth+" "+ imageHeight);
			        GradientPanel panel = new GradientPanel(Color.red,Color.white);
			        panel.setBackground(new Color(245, 89, 37));
			        panel.setBounds(0, 0, 109, 640);
			        main_Panel.add(panel);
			        JLabel label = new JLabel();
			        panel.add(label);
			        label.setIcon(scaledImageIcon);
			        
			        Box verticalBox = Box.createVerticalBox();

			        verticalBox.setAlignmentX(Box.LEFT_ALIGNMENT);

			        // Tạo các button
			        btn_DashBoard = new JButton("Dashboard");
			        btn_DashBoard.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent e) {
			        	}
			        });
			        btn_DashBoard.setBackground(new Color(255, 255, 255));
			         btn_classes = new JButton("Classes" );
			         btn_classes.setForeground(new Color(255, 255, 255));
			      
			         
			        btn_classes.setBackground(new Color(255, 0, 0));
			         btn_Schedule = new JButton("Schedule");
			         btn_Schedule.setForeground(new Color(255, 255, 255));
			        btn_Schedule.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent e) {
			        	
			        	
			        	}
			        });
			        btn_Schedule.setBackground(new Color(255, 0, 0));
			        btn_blog = new JButton("Blog");
			        btn_blog.setForeground(new Color(255, 255, 255));
			        btn_blog.setBackground(new Color(255, 0, 0));

			        // Đặt độ rộng cho các button
			        int buttonWidth = verticalBox.getWidth();
			        Dimension buttonDimension = new Dimension(100, 20);
			        
			         btn_DashBoard.setMaximumSize(buttonDimension);
			        btn_classes.setMaximumSize(buttonDimension);
			        btn_Schedule.setMaximumSize(buttonDimension);
			        btn_blog.setMaximumSize(buttonDimension);

			        // Thêm các button vào VerticalBox
			        verticalBox.add(btn_DashBoard);
			        verticalBox.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các button
			        verticalBox.add(btn_classes);
			        verticalBox.add(Box.createVerticalStrut(10));
			        
			        verticalBox.add(btn_Schedule);
			        verticalBox.add(Box.createVerticalStrut(10));
			        verticalBox.add(btn_blog);
			        btn_DashBoard.setBorder(null);
			        
			        panel.add(verticalBox);
			        verticalBox.add(Box.createVerticalStrut(30));
			        
			        JButton btn_Classes_1 = new JButton("<- Logout");
			        btn_Classes_1.setForeground(new Color(255, 255, 255));
			        btn_Classes_1.setBackground(new Color(255, 0, 0));
			        btn_Classes_1.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent e) {
			        		LoginController loginController = new LoginController(new LoginView());
			                loginController.displayLoginView();
			                dispose();
			        	}
			        });
			        btn_Classes_1.setMaximumSize(new Dimension(100, 20));
			        panel.add(btn_Classes_1);
			        
			         content_panel = new JPanel();
			         content_panel.setBorder(new LineBorder(new Color(255, 0, 0)));
			        content_panel.setBounds(115, 0, 892, 640);

			        
			        main_Panel.add(content_panel);
			        content_panel.setLayout(null);

			      // MainViewCtrl_Teacher mainView=new MainViewCtrl_Teacher(this);
			        setVisible(true);setButtonListener();
		
	}
	
	
	
	 
}
