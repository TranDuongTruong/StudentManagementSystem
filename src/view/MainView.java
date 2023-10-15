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
import java.awt.Color;
import javax.swing.JMenu;
import java.awt.Panel;
import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
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
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 ImageIcon imageIcon = new ImageIcon("C:\\Users\\ADmin\\Downloads\\DTULogo.png");
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
			        panel.setBounds(0, 0, 97, 263);
			        contentPane.add(panel);
			        JLabel label = new JLabel();
			        panel.add(label);
			        label.setIcon(scaledImageIcon);
			        
			        Box verticalBox = Box.createVerticalBox();
//			        verticalBox.setBorder(new EmptyBorder(0, 0, 0, 0));
//			        verticalBox.setBounds(0, 0, 101, 263);
//			         
//			        panel.add(verticalBox);
//			        
//			        
//			        JButton button = new JButton("Dashboard");
//			        verticalBox.add(button);
//			        
//			        JButton button_1 = new JButton("Classes");
//			        verticalBox.add(button_1);
//			        
//			        JButton button_2 = new JButton("Student");
//			        verticalBox.add(button_2);
//			        
//			        JButton button_3 = new JButton("Blog");
//			        verticalBox.add(button_3);
			        verticalBox.setAlignmentX(Box.LEFT_ALIGNMENT);

			        // Tạo các button
			        JButton btnDashboard = new JButton("Dashboard");
			        btnDashboard.setBackground(new Color(255, 255, 255));
			        JButton button_1 = new JButton("Classes");
			        button_1.setBackground(new Color(192, 192, 192));
			        JButton button_2 = new JButton("Student");
			        button_2.setBackground(new Color(192, 192, 192));
			        JButton button_3 = new JButton("Blog");
			        button_3.setBackground(new Color(192, 192, 192));

			        // Đặt độ rộng cho các button
			        int buttonWidth = verticalBox.getWidth();
			        Dimension buttonDimension = new Dimension(100, 20);
			        btnDashboard.setMaximumSize(buttonDimension);
			        button_1.setMaximumSize(buttonDimension);
			        button_2.setMaximumSize(buttonDimension);
			        button_3.setMaximumSize(buttonDimension);

			        // Thêm các button vào VerticalBox
			        verticalBox.add(btnDashboard);
			        verticalBox.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các button
			        verticalBox.add(button_1);
			        verticalBox.add(Box.createVerticalStrut(10));
			        verticalBox.add(button_2);
			        verticalBox.add(Box.createVerticalStrut(10));
			        verticalBox.add(button_3);
			        btnDashboard.setBorder(null);
			        
			        panel.add(verticalBox);
			        
			        JTextPane textPane = new JTextPane();
			        verticalBox.add(Box.createVerticalStrut(30));
			        textPane.setText("<- Logout");
			        panel.add(textPane);
			      
			        
		
	}
}
