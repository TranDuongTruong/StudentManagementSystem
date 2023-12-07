package view.Teacher;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import controller.DatabaseConnection;

public class DashboardTeacherView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtInformation;
	private JLabel lblAvatar;
	private JPanel contentPane;
	private JTextComponent lblTeacherName_info;
	private JLabel lblName_info;
	private JLabel lblTeacherName;
	private JLabel lblTeacherId;
	private JLabel lblDayofBirth_info;
	private Component lblGender_info;
	private JLabel lblTeacherID_info;
	private JLabel lblEmail_info;
	private JLabel lblEmail;
	private JLabel lblDayofBirth;
	private Component lblGender;
	/**
	 * Create the panel.
	 */
	 String teacherName="";
	public DashboardTeacherView() {
	
	        setBounds(162, 0, 835, 640);
	        
//	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			setBounds(100, 100, 450, 300);
//			contentPane = new JPanel();
//			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//			setContentPane(contentPane);
//			contentPane.setLayout(null);
//	        setLayout(null);
	        txtInformation = new JTextField();
	        txtInformation.setBounds(22, 6, 253, 41);
	        txtInformation.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
	        txtInformation.setForeground(new Color(255, 5, 19));
	        txtInformation.setText("TEACHER INFORMATION");
	        add(txtInformation);
	        txtInformation.setColumns(10);
	        
	        JSeparator separator = new JSeparator();
	        separator.setBounds(6, 47, 694, 24);
	        add(separator);
	        
	         lblTeacherName = new JLabel("Teacher name:");
	        lblTeacherName.setBounds(68, 60, 102, 29);
	        lblTeacherName.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblTeacherName.setFont(new Font("Tahoma", Font.BOLD, 14));
	        add(lblTeacherName);
	        
	         lblTeacherId = new JLabel("Teacher ID:");
	        lblTeacherId.setBounds(68, 85, 102, 29);
	        lblTeacherId.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblTeacherId.setFont(new Font("Tahoma", Font.BOLD, 14));
	        add(lblTeacherId);
	        
	         lblGender = new JLabel(" Gender:");
	        lblGender.setBounds(68, 110, 102, 29);
	        //lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
	        add(lblGender);
	        
	         lblDayofBirth = new JLabel("Day of birth:");
	        lblDayofBirth.setBounds(68, 135, 102, 29);
	        lblDayofBirth.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblDayofBirth.setFont(new Font("Tahoma", Font.BOLD, 14));
	        add(lblDayofBirth);
	        
	         lblEmail = new JLabel("Email:");
	        lblEmail.setBounds(68, 160, 102, 29);
	        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
	        add(lblEmail);
	        
	         lblEmail_info = new JLabel((String) null);
	        lblEmail_info.setBounds(182, 160, 102, 29);
	        lblEmail_info.setHorizontalAlignment(SwingConstants.LEFT);
	        lblEmail_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        add(lblEmail_info);
	        
	         lblTeacherID_info = new JLabel();
	        lblTeacherID_info.setBounds(182, 85, 102, 29);
	        lblTeacherID_info.setHorizontalAlignment(SwingConstants.LEFT);
	        lblTeacherID_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        add(lblTeacherID_info);
	        
	         lblGender_info = new JLabel((String) null);
	        lblGender_info.setBounds(182, 110, 102, 29);
	        //lblGender_info.setHorizontalAlignment(SwingConstants.LEFT);
	        lblGender_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        add(lblGender_info);
	        
	         lblDayofBirth_info = new JLabel((String) null);
	        lblDayofBirth_info.setBounds(182, 135, 102, 29);
	        lblDayofBirth_info.setHorizontalAlignment(SwingConstants.LEFT);
	        lblDayofBirth_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        add(lblDayofBirth_info);
	        
	        lblName_info = new JLabel(teacherName);
            lblName_info.setBounds(182, 59, 253, 29);
           lblName_info.setHorizontalAlignment(SwingConstants.LEFT);
           lblName_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
           add(lblName_info);
           
	         lblAvatar = new JLabel("New label");
	         lblAvatar.setBounds(483, 83, 142, 197);
	      
			ImageIcon imageIcon = new ImageIcon();
			 URL imageUrl=TeacherAccountMainView.class.getClassLoader().getResource("Assert/student/Examination/avvatar.png");
		            File file = new File(imageUrl.getPath());
		              imageIcon = new ImageIcon(file.getAbsolutePath());
		              Image image = imageIcon.getImage();
		              
		              // Tính toán kích thước mới cho hình ảnh
		              int labelWidth = lblAvatar.getWidth();
		              int labelHeight = lblAvatar.getHeight();
		              Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
		              
		              ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		              lblAvatar.setIcon(scaledImageIcon);
		              add(lblAvatar);
		              
		              setVisible(true);
		              //getData(teacherID);

	}
	public void getData(String teacherID) {
	    DatabaseConnection db = new DatabaseConnection();
	    Connection con = db.connectToBB();

	    if (con != null) {
	        try {
	        	String sql = "SELECT tc.teacherID, t.name, t.dob, t.address, t.gender, t.phoneNumber, c.description " +
                        "FROM teacherClassroom tc " +
                        "JOIN teacher t ON tc.teacherID = t.teacherID " +
                        "JOIN classroom c ON tc.classCode = c.classCode " +
                        "WHERE tc.teacherID = ?";


	            PreparedStatement preparedStatement = con.prepareStatement(sql);
	            preparedStatement.setString(1, teacherID);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                
	                 teacherName = resultSet.getString("name");
	        
	               
	               

	            }

	            resultSet.close();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	}
	public void SetcourseInfo() {
        // Cập nhật thông tin lên giao diện
        lblName_info.setText(teacherName);
       
    }
}
