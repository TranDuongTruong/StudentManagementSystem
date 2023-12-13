package view.Student;

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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import controller.DatabaseConnection;
import controller.Admin.LoginController;

public class DashboardStudentView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblAvatar;
	private JPanel contentPane;
	private JTextComponent lblTeacherName_info;
	private JLabel lblName_info;
	private JLabel lblStudentName;
	private JLabel lblStudentId;
	private JLabel lblDayofBirth_info;
	private JLabel lblEmail;
	private JLabel lblDayofBirth;
	private Component lblGender;
	/**
	 * Create the panel.
	 */
	 String studentName="";
	 private String studentIDFromDB;
	 private String gender;
	 private String email;
	 private LocalDate dayOfBirth;
	 private String phone;
	 private String address;
	private JLabel lblID_info;
	private JLabel lblGender_info;
	private JLabel lblEmail_info;
	private Date date;
	private JTextField txtCurrentAddress;
	private JLabel lblAddress_info;
	private JLabel lblPhone_info;
	public DashboardStudentView() {
	
	        setBounds(162, 0, 835, 640);
	        setLayout(null);
	        
	         lblStudentName = new JLabel("Student name:");
	        lblStudentName.setBounds(38, 76, 113, 17);
	        lblStudentName.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblStudentName.setFont(new Font("Tahoma", Font.BOLD, 14));
	        add(lblStudentName);
	        
	         lblStudentId = new JLabel("Student ID:");
	        lblStudentId.setBounds(49, 110, 102, 17);
	        lblStudentId.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 14));
	        add(lblStudentId);
	        
	         lblGender = new JLabel(" Gender:");
	        lblGender.setBounds(93, 139, 59, 17);
	       // ((JTextField) lblGender).setHorizontalAlignment(SwingConstants.RIGHT);
	        lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
	        add(lblGender);
	        
	         lblDayofBirth = new JLabel("Day of birth:");
	        lblDayofBirth.setBounds(38, 168, 113, 17);
	        lblDayofBirth.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblDayofBirth.setFont(new Font("Tahoma", Font.BOLD, 14));
	        add(lblDayofBirth);
	        
	         lblEmail = new JLabel("Email:");
	        lblEmail.setBounds(41, 199, 110, 17);
	        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
	        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
	        add(lblEmail);
	        
	         lblDayofBirth_info = new JLabel();
	        lblDayofBirth_info.setBounds(168, 168, 156, 17);
	        lblDayofBirth_info.setHorizontalAlignment(SwingConstants.LEFT);
	        lblDayofBirth_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        add(lblDayofBirth_info);
	        
	        lblName_info = new JLabel(studentName);
            lblName_info.setBounds(163, 76, 156, 17);
           lblName_info.setHorizontalAlignment(SwingConstants.LEFT);
           lblName_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
           add(lblName_info);
           
	         lblAvatar = new JLabel("");
	         lblAvatar.setBounds(470, 50, 156, 180);
	      
//			ImageIcon imageIcon = new ImageIcon();
//			// URL imageUrl=TeacherAccountMainView.class.getClassLoader().getResource("Assert/student/Examination/avvatar.png");
//		            File file = new File(imageUrl.getPath());
//		              imageIcon = new ImageIcon(file.getAbsolutePath());
//		              Image image = imageIcon.getImage();
		              
		              // Tính toán kích thước mới cho hình ảnh
//		              int labelWidth = lblAvatar.getWidth();
//		              int labelHeight = lblAvatar.getHeight();
//		              Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
		              
//		              ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
//		              lblAvatar.setIcon(scaledImageIcon);
//		              add(lblAvatar);
		              
		               lblID_info = new JLabel();
		              lblID_info.setHorizontalAlignment(SwingConstants.LEFT);
		              lblID_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		              lblID_info.setBounds(163, 110, 156, 17);
		              add(lblID_info);
		              
		               lblGender_info = new JLabel();
		              lblGender_info.setHorizontalAlignment(SwingConstants.LEFT);
		              lblGender_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		              lblGender_info.setBounds(164, 140, 156, 17);
		              add(lblGender_info);
		              
		               lblEmail_info = new JLabel();
		              lblEmail_info.setHorizontalAlignment(SwingConstants.LEFT);
		              lblEmail_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		              lblEmail_info.setBounds(163, 199, 156, 17);
		              add(lblEmail_info);
		              
		              JSeparator separator = new JSeparator();
		              separator.setBounds(6, 40, 823, 24);
		              add(separator);
		              
		              JSeparator separator_1 = new JSeparator();
		              separator_1.setBounds(6, 242, 823, 24);
		              add(separator_1);
		              
		              txtCurrentAddress = new JTextField();
		              txtCurrentAddress.setForeground(new Color(255, 28, 17));
		              txtCurrentAddress.setText("Current Address");
		              txtCurrentAddress.setBounds(6, 253, 130, 26);
		              add(txtCurrentAddress);
		              txtCurrentAddress.setColumns(10);
		              
		              JLabel lbladdress = new JLabel("Address:");
		              lbladdress.setHorizontalAlignment(SwingConstants.RIGHT);
		              lbladdress.setFont(new Font("Tahoma", Font.BOLD, 14));
		              lbladdress.setBounds(38, 316, 110, 17);
		              add(lbladdress);
		              
		              JLabel lblPhone = new JLabel("Phone:");
		              lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		              lblPhone.setFont(new Font("Tahoma", Font.BOLD, 14));
		              lblPhone.setBounds(38, 345, 110, 17);
		              add(lblPhone);
		              
		               lblAddress_info = new JLabel();
		              lblAddress_info.setText((String) null);
		              lblAddress_info.setHorizontalAlignment(SwingConstants.LEFT);
		              lblAddress_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		              lblAddress_info.setBounds(163, 316, 156, 17);
		              add(lblAddress_info);
		              
		               lblPhone_info = new JLabel();
		              lblPhone_info.setText((String) null);
		              lblPhone_info.setHorizontalAlignment(SwingConstants.LEFT);
		              lblPhone_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		              lblPhone_info.setBounds(163, 345, 156, 17);
		              add(lblPhone_info);
		              
		              JLabel lblNewLabel = new JLabel("STUDENT INFORMATION");
		              lblNewLabel.setIcon(new ImageIcon(DashboardStudentView.class.getResource("/Assert/teacher/icDab.png")));
		              lblNewLabel.setForeground(new Color(255, 28, 17));
		              lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		              lblNewLabel.setBounds(6, 6, 295, 32);
		              add(lblNewLabel);
		              
		              setVisible(true);
		              getData(String.valueOf(LoginController.studentId));
		              SetcourseInfo();
		           

	}
	public void getData(String studentID) {
	    DatabaseConnection db = new DatabaseConnection();
	    Connection con = db.connectToBB();

	    if (con != null) {
	        try {
	        	String sql = "SELECT st.studentID, st.name, st.dob, st.address, st.gender, st.phoneNumber, a.email " +
                        "FROM student st " +
                        "LEFT JOIN accounts a ON st.studentID = a.studentID "+
                        "WHERE st.studentID = ?";


	            PreparedStatement preparedStatement = con.prepareStatement(sql);
	            preparedStatement.setString(1, studentID);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                
	                 studentName = resultSet.getString("name");
	                 studentIDFromDB = resultSet.getString("studentID");
	                date = resultSet.getDate("dob");
	                 if (date != null) {
	                     dayOfBirth = date.toLocalDate();
	                 }
	                 gender = resultSet.getString("gender");
	                 email = resultSet.getString("email");
	                 phone = resultSet.getString("phoneNumber");
	                 address = resultSet.getString("address");
	               
	               

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
        lblName_info.setText(studentName);
        lblID_info.setText(studentIDFromDB);
        if (dayOfBirth != null) {
            lblDayofBirth_info.setText(dayOfBirth.toString());
        } else {
            lblDayofBirth_info.setText("N/A"); // Hoặc bất kỳ thông điệp nào bạn muốn hiển thị cho trường hợp này
        }
        lblGender_info.setText(gender);
        lblEmail_info.setText(email);
        lblPhone_info.setText(phone);
        lblAddress_info.setText(address);
    }
}
