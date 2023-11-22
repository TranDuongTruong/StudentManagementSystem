package view.Teacher;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Student;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class DetalInformationofStudentView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblCreditsCompleted_info;
	JLabel lblPhoneNumber_info;
	JLabel lblGender_info;
	
	JLabel lblDayOfBirth_info;
	JLabel lblAddress_info;
	JLabel lblName_info;
	JLabel lblID_info;
	JLabel lblCreditsOwed_info;
	JLabel avatar;
	static Student student;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalInformationofStudentView frame = new DetalInformationofStudentView(student);
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
	public DetalInformationofStudentView(Student stu) {
		setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentId.setBounds(62, 59, 102, 29);
		contentPane.add(lblStudentId);
		
		 avatar = new JLabel("hinh");
		avatar.setBounds(316, 47, 252, 268);
		ImageIcon imageIcon = new ImageIcon();
		 URL imageUrl=TeacherAccountMainView.class.getClassLoader().getResource("Assert/student/Examination/avvatar.png");
	            File file = new File(imageUrl.getPath());
	              imageIcon = new ImageIcon(file.getAbsolutePath());
	              Image image = imageIcon.getImage();
	              
	              // Tính toán kích thước mới cho hình ảnh
	              int labelWidth = avatar.getWidth();
	              int labelHeight = avatar.getHeight();
	              Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
	              
	              ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
	              avatar.setIcon(scaledImageIcon);
		contentPane.add(avatar);
		
		JLabel lblStudentInformation = new JLabel("STUDENT INFORMATION");
		lblStudentInformation.setForeground(new Color(255, 38, 11));
		lblStudentInformation.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblStudentInformation.setBounds(39, 10, 264, 37);
		contentPane.add(lblStudentInformation);
		
		JLabel lblStudentId_1 = new JLabel("Name:");
		lblStudentId_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentId_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentId_1.setBounds(62, 98, 102, 29);
		contentPane.add(lblStudentId_1);
		
		JLabel lblStudentId_2 = new JLabel("Address:");
		lblStudentId_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentId_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentId_2.setBounds(62, 139, 102, 29);
		contentPane.add(lblStudentId_2);
		
		JLabel lblDayOfBirth = new JLabel("Day Of Birth:");
		lblDayOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDayOfBirth.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDayOfBirth.setBounds(62, 180, 102, 29);
		contentPane.add(lblDayOfBirth);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGender.setBounds(62, 221, 102, 29);
		contentPane.add(lblGender);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhoneNumber.setBounds(39, 262, 126, 29);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblCreditsCompleted = new JLabel("Credits Completed:");
		lblCreditsCompleted.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreditsCompleted.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCreditsCompleted.setBounds(10, 303, 155, 29);
		contentPane.add(lblCreditsCompleted);
		
		JLabel lblCreditsOwed = new JLabel("Credits Owed:");
		lblCreditsOwed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreditsOwed.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCreditsOwed.setBounds(39, 344, 126, 29);
		contentPane.add(lblCreditsOwed);
		
		 lblID_info = new JLabel("Student ID");
		lblID_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblID_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID_info.setBounds(190, 59, 102, 29);
		contentPane.add(lblID_info);
		
		 lblName_info = new JLabel("Student ID");
		lblName_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblName_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName_info.setBounds(190, 98, 102, 29);
		contentPane.add(lblName_info);
		
		 lblAddress_info = new JLabel("Student ID");
		lblAddress_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress_info.setBounds(190, 139, 102, 29);
		contentPane.add(lblAddress_info);
		
		 lblDayOfBirth_info = new JLabel("Student ID");
		lblDayOfBirth_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblDayOfBirth_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDayOfBirth_info.setBounds(190, 180, 102, 29);
		contentPane.add(lblDayOfBirth_info);
		
		 lblGender_info = new JLabel("Student ID");
		lblGender_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender_info.setBounds(190, 221, 102, 29);
		contentPane.add(lblGender_info);
		
		 lblPhoneNumber_info = new JLabel("Student ID");
		lblPhoneNumber_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNumber_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNumber_info.setBounds(190, 262, 102, 29);
		contentPane.add(lblPhoneNumber_info);
		
		 lblCreditsCompleted_info = new JLabel("Student ID");
		lblCreditsCompleted_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreditsCompleted_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCreditsCompleted_info.setBounds(190, 303, 102, 29);
		contentPane.add(lblCreditsCompleted_info);
		
		 lblCreditsOwed_info = new JLabel("Student ID");
		lblCreditsOwed_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreditsOwed_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCreditsOwed_info.setBounds(190, 344, 102, 29);
		contentPane.add(lblCreditsOwed_info);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(28, 47, 623, 24);
		contentPane.add(separator);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(483, 350, 85, 21);
		
		btnBack.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {	
		    	setVisible(false);
		    	
		    	  
		    }
		});
		contentPane.add(btnBack);
		SetStudentInfo(stu);
		setVisible(true);
	}
	public void SetStudentInfo(Student student) {
		lblID_info.setText(""+student.getStudentID());
		lblName_info.setText(student.getName());
		lblAddress_info.setText(student.getAddress());
		lblGender_info.setText((student.isGender() ? "Male" : "Female"));
		lblPhoneNumber_info.setText(student.getPhoneNumber());
		lblDayOfBirth_info.setText(""+student.getDob());
		lblCreditsCompleted_info.setText(""+student.getCreditsCompleted());
		lblCreditsOwed_info.setText(""+student.getCreditsOwed());
		
//		ImageIcon imageIcon = new ImageIcon();
//		 URL imageUrl=MainView.class.getClassLoader().getResource("Assert/AvatarOfStudents/avatarForStudent.png");
//	            File file = new File(imageUrl.getPath());
//	              imageIcon = new ImageIcon(file.getAbsolutePath());
//	              
//	     avatar.setIcon(imageIcon);
	}
}
