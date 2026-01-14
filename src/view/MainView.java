package view;

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

import controller.MainViewCtrl_Teacher;

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
public class MainView extends JFrame {

	 JButton btn_Classes;
	 JButton btn_Student;
	  JButton btn_classes ;
	  JButton btn_DashBoard;
	private static final long serialVersionUID = 1L;
	private JPanel main_Panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					MainViewCtrl_Teacher mainView=new MainViewCtrl_Teacher(frame);
					frame.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 453);
		main_Panel = new JPanel();
		main_Panel.setBackground(new Color(255, 255, 255));
		main_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main_Panel);
		main_Panel.setLayout(null);
		
		ImageIcon imageIcon = new ImageIcon();
		 URL imageUrl=MainView.class.getClassLoader().getResource("Assert/DTULogo.png");
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
			        Panel panel = new Panel();
			        panel.setBackground(new Color(192, 192, 192));
			        panel.setBounds(0, 0, 97, 414);
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
			         btn_classes = new JButton("Classes");
			        btn_classes.setBackground(new Color(192, 192, 192));
			         btn_Student = new JButton("Student");
			        btn_Student.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent e) {
			        	}
			        });
			        btn_Student.setBackground(new Color(192, 192, 192));
			        btn_Classes = new JButton("Blog");
			        btn_Classes.setBackground(new Color(192, 192, 192));

			        // Đặt độ rộng cho các button
			        int buttonWidth = verticalBox.getWidth();
			        Dimension buttonDimension = new Dimension(100, 20);
			        
			         btn_DashBoard.setMaximumSize(buttonDimension);
			        btn_classes.setMaximumSize(buttonDimension);
			        btn_Student.setMaximumSize(buttonDimension);
			        btn_Classes.setMaximumSize(buttonDimension);

			        // Thêm các button vào VerticalBox
			        verticalBox.add(btn_DashBoard);
			        verticalBox.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các button
			        verticalBox.add(btn_classes);
			        verticalBox.add(Box.createVerticalStrut(10));
			        verticalBox.add(btn_Student);
			        verticalBox.add(Box.createVerticalStrut(10));
			        verticalBox.add(btn_Classes);
			        btn_DashBoard.setBorder(null);
			        
			        panel.add(verticalBox);
			        
			        JTextPane Btn_Logout = new JTextPane();
			        verticalBox.add(Box.createVerticalStrut(30));
			        Btn_Logout.setText("<- Logout");
			        panel.add(Btn_Logout);
			        
			        JPanel content_panel = new JPanel();
			        content_panel.setBounds(103, 0, 537, 414);

			        
			        main_Panel.add(content_panel);

			        MainViewCtrl_Teacher mainView=new MainViewCtrl_Teacher(this);
			        setVisible(true);
		
	}
	 public void classesListener(ActionListener listener) {
		 btn_classes.addActionListener(listener);
     }
	 public void studentListener(ActionListener listener) {
		 btn_Student.addActionListener(listener);
	 }
	 
}
