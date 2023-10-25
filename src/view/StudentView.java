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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class StudentView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentView frame = new StudentView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public StudentView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 718);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
			        panel.setBounds(0, 0, 116, 679);
			        contentPane.add(panel);
			        JLabel label = new JLabel();
			        panel.add(label);
			        label.setIcon(scaledImageIcon);
			        
			        Box verticalBox = Box.createVerticalBox();

			        verticalBox.setAlignmentX(Box.LEFT_ALIGNMENT);
			        JButton button_3 = new JButton("Blog");
			        button_3.setBackground(new Color(192, 192, 192));

			        // Đặt độ rộng cho các button
			        int buttonWidth = verticalBox.getWidth();
			        Dimension buttonDimension = new Dimension(100, 20);
			        button_3.setMaximumSize(buttonDimension);
			        
			        JButton button_1 = new JButton("Dashboard");
			        button_1.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent e) {
			        	}
			        });
			        button_1.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			                // Tạo một instance của MainView
			                MainView mainView = new MainView();

			                // Hiển thị MainView	
			                mainView.setVisible(true);

			                // Đóng StudentView (nếu bạn muốn đóng StudentView sau khi chuyển đến MainView)
			                dispose();
			            }
			        });
			        button_1.setMaximumSize(new Dimension(100, 20));
			        button_1.setBackground(Color.LIGHT_GRAY);
			        verticalBox.add(button_1);
			        verticalBox.add(Box.createVerticalStrut(10));
			        
			        JButton button_2_1 = new JButton("Classes");
			        button_2_1.setMaximumSize(new Dimension(100, 20));
			        button_2_1.setBackground(Color.LIGHT_GRAY);
			        verticalBox.add(button_2_1);
			        verticalBox.add(Box.createVerticalStrut(10));
			        
			        JButton btnStudent = new JButton("Student");
			        btnStudent.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent e) {
			        	}
			        });
			        btnStudent.setMaximumSize(new Dimension(100, 20));
			        btnStudent.setBorder(null);
			        btnStudent.setBackground(Color.WHITE);
			        verticalBox.add(btnStudent);
			        verticalBox.add(Box.createVerticalStrut(10));
			        verticalBox.add(button_3);
			        
			        panel.add(verticalBox);
			        
			        JTextPane textPane = new JTextPane();
			        verticalBox.add(Box.createVerticalStrut(30));
			        textPane.setText("<- Logout");
			        panel.add(textPane);
			        
			        JPanel contentPane_1 = new JPanel();
			        contentPane_1.setLayout(null);
			        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
			        contentPane_1.setBounds(117, 0, 770, 679);
			        contentPane.add(contentPane_1);
			        
			        Box verticalBox_1 = Box.createVerticalBox();
			        verticalBox_1.setBounds(44, 144, -28, -35);
			        contentPane_1.add(verticalBox_1);
			        
			        JLabel label_Address = new JLabel("Address");
			        label_Address.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        label_Address.setBounds(31, 11, 92, 54);
			        contentPane_1.add(label_Address);
			        
			        JLabel label_Student_ID = new JLabel("Student ID");
			        label_Student_ID.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        label_Student_ID.setBounds(285, 11, 155, 54);
			        contentPane_1.add(label_Student_ID);
			        
			        textField = new JTextField();
			        textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField.setColumns(10);
			        textField.setBounds(393, 12, 123, 54);
			        contentPane_1.add(textField);
			        
			        JButton btnTim = new JButton("Search");
			        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnTim.setBounds(526, 12, 89, 54);
			        contentPane_1.add(btnTim);
			        
			        JComboBox comboBox_queQuan_timKiem = new JComboBox();
			        comboBox_queQuan_timKiem.setBounds(120, 11, 155, 54);
			        contentPane_1.add(comboBox_queQuan_timKiem);
			        
			        JSeparator separator_1 = new JSeparator();
			        separator_1.setBounds(10, 92, 738, 2);
			        contentPane_1.add(separator_1);
			        
			        JLabel lblListOfStudents = new JLabel("List of students");
			        lblListOfStudents.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblListOfStudents.setBounds(10, 97, 251, 54);
			        contentPane_1.add(lblListOfStudents);
			        
			        JScrollPane scrollPane = new JScrollPane((Component) null);
			        scrollPane.setBounds(10, 144, 738, 214);
			        contentPane_1.add(scrollPane);
			        
			        table = new JTable();
			        table.setModel(new DefaultTableModel(
			        	new Object[][] {
			        		{null, null, null, null, null, null, null, null},
			        		{null, null, null, null, null, null, null, null},
			        		{null, null, null, null, null, null, null, null},
			        		{null, null, null, null, null, null, null, null},
			        		{null, null, null, null, null, null, null, null},
			        		{null, null, null, null, null, null, null, null},
			        		{null, null, null, null, null, null, null, null},
			        		{null, null, null, null, null, null, null, null},
			        	},
			        	new String[] {
			        			"Student ID", "Name", "Day of Birth", "Address", "Gender", "Phone number", "Credits Completed", "Credits Owed"
			        	}
			        ));
			        scrollPane.setViewportView(table);
			        
			        JSeparator separator_1_1 = new JSeparator();
			        separator_1_1.setBounds(10, 369, 738, 2);
			        contentPane_1.add(separator_1_1);
			        
			        JLabel lblStudentInformation = new JLabel("Student information");
			        lblStudentInformation.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblStudentInformation.setBounds(10, 369, 233, 54);
			        contentPane_1.add(lblStudentInformation);
			        
			        JLabel lblStudentId = new JLabel("Student ID");
			        lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblStudentId.setBounds(10, 412, 102, 54);
			        contentPane_1.add(lblStudentId);
			        
			        textField_1 = new JTextField();
			        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_1.setColumns(10);
			        textField_1.setBounds(127, 425, 166, 29);
			        contentPane_1.add(textField_1);
			        
			        JLabel lblName = new JLabel("Name");
			        lblName.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblName.setBounds(10, 457, 102, 54);
			        contentPane_1.add(lblName);
			        
			        textField_2 = new JTextField();
			        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_2.setColumns(10);
			        textField_2.setBounds(127, 470, 166, 29);
			        contentPane_1.add(textField_2);
			        
			        JLabel lblAddress = new JLabel("Address");
			        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblAddress.setBounds(10, 510, 102, 54);
			        contentPane_1.add(lblAddress);
			        
			        JComboBox comboBox_queQuan = new JComboBox();
			        comboBox_queQuan.setBounds(127, 522, 166, 35);
			        contentPane_1.add(comboBox_queQuan);
			        
			        JLabel lblDayOfBirth = new JLabel("Day Of Birth");
			        lblDayOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblDayOfBirth.setBounds(10, 563, 155, 54);
			        contentPane_1.add(lblDayOfBirth);
			        
			        textField_3 = new JTextField();
			        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_3.setColumns(10);
			        textField_3.setBounds(127, 576, 166, 29);
			        contentPane_1.add(textField_3);
			        
			        JLabel lblGender = new JLabel("Gender");
			        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblGender.setBounds(324, 412, 102, 54);
			        contentPane_1.add(lblGender);
			        
			        JRadioButton rdbtnMale = new JRadioButton("Male");
			        rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        rdbtnMale.setBounds(405, 429, 61, 23);
			        contentPane_1.add(rdbtnMale);
			        
			        JRadioButton rdbtnFemale = new JRadioButton("Female");
			        rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        rdbtnFemale.setBounds(489, 429, 102, 23);
			        contentPane_1.add(rdbtnFemale);
			        
			        JLabel lblPhoneNumber = new JLabel("Phone Number");
			        lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblPhoneNumber.setBounds(324, 473, 140, 23);
			        contentPane_1.add(lblPhoneNumber);
			        
			        textField_4 = new JTextField();
			        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_4.setColumns(10);
			        textField_4.setBounds(526, 470, 166, 29);
			        contentPane_1.add(textField_4);
			        
			        JLabel lblCreditsComple = new JLabel("Credits Completed");
			        lblCreditsComple.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblCreditsComple.setBounds(324, 513, 160, 23);
			        contentPane_1.add(lblCreditsComple);
			        
			        textField_5 = new JTextField();
			        textField_5.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_5.setColumns(10);
			        textField_5.setBounds(526, 510, 166, 29);
			        contentPane_1.add(textField_5);
			        
			        JLabel lblCreditsOwed = new JLabel("Credits Owed");
			        lblCreditsOwed.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblCreditsOwed.setBounds(324, 557, 160, 23);
			        contentPane_1.add(lblCreditsOwed);
			        
			        textField_6 = new JTextField();
			        textField_6.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_6.setColumns(10);
			        textField_6.setBounds(526, 554, 166, 29);
			        contentPane_1.add(textField_6);
			        
			        JButton btnThem = new JButton("Add");
			        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnThem.setBounds(31, 628, 89, 42);
			        contentPane_1.add(btnThem);
			        
			        JButton btnXoa = new JButton("Delete");
			        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnXoa.setBounds(151, 628, 89, 42);
			        contentPane_1.add(btnXoa);
			        
			        JButton btnCapNhat = new JButton("Update");
			        btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnCapNhat.setBounds(264, 628, 135, 42);
			        contentPane_1.add(btnCapNhat);
			        
			        JButton btnLuu = new JButton("Save");
			        btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnLuu.setBounds(421, 628, 135, 42);
			        contentPane_1.add(btnLuu);
			        
			        JButton btnHuyBo = new JButton("Cancel");
			        btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnHuyBo.setBounds(585, 628, 135, 42);
			        contentPane_1.add(btnHuyBo);
			        
			        JSeparator separator_2 = new JSeparator();
			        separator_2.setForeground(Color.BLACK);
			        separator_2.setBounds(15, 628, 733, -22);
			        contentPane_1.add(separator_2);
			        
			        JSeparator separator_1_1_1 = new JSeparator();
			        separator_1_1_1.setBounds(10, 616, 738, 2);
			        contentPane_1.add(separator_1_1_1);
			        
			        JButton btnHuyTim = new JButton("Undo");
			        btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnHuyTim.setBounds(631, 11, 117, 54);
			        contentPane_1.add(btnHuyTim);
			      
		
	}
}
