package view.Student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DatabaseConnection;
import model.Classroom;
import model.Student;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DetalinformationofCourseView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDescription;
	private JLabel lblNameClass;
	private JLabel lblNameTeacher;
	private JLabel lbl_Description;
	private JLabel lblName_info;
	private JLabel lblName_teacher;
	private JLabel lbl_Duration;
	private JLabel lbl_Location;
	 String teacherName="";
	String      decription="This is basic class";
	 JLabel lblCourseInformation;
	/**
	 * Create the frame.
	 */
	public DetalinformationofCourseView(String ClassCode, String className, String location) {
		getData(ClassCode);
		System.out.println("CLA:"+ClassCode);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 lblNameClass = new JLabel("Name Class:");
		lblNameClass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNameClass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNameClass.setBounds(31, 55, 102, 29);
		contentPane.add(lblNameClass);
		
		 lblNameTeacher = new JLabel("Name Teacher:");
		lblNameTeacher.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNameTeacher.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNameTeacher.setBounds(16, 94, 117, 29);
		contentPane.add(lblNameTeacher);
		
		 lblDescription = new JLabel("Description:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescription.setBounds(31, 135, 102, 29);
		contentPane.add(lblDescription);
		
		 lbl_Description = new JLabel("Location");
		lbl_Description.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Description.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Description.setBounds(31, 178, 102, 29);
		contentPane.add(lbl_Description);
		
		 lblName_info = new JLabel(className);
		lblName_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblName_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName_info.setBounds(143, 55, 253, 29);
		contentPane.add(lblName_info);
		lblName_info = new JLabel();
		
		 lblName_teacher = new JLabel(teacherName) ;
		lblName_teacher.setHorizontalAlignment(SwingConstants.LEFT);
		lblName_teacher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName_teacher.setBounds(145, 94, 251, 29);
		contentPane.add(lblName_teacher);
		
		 lbl_Duration = new JLabel(decription);
		lbl_Duration.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Duration.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_Duration.setBounds(145, 135, 251, 29);
		contentPane.add(lbl_Duration);
		
		 lbl_Location = new JLabel(location);
		lbl_Location.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Location.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_Location.setBounds(145, 178, 251, 29);
		contentPane.add(lbl_Location);
		
		 lblCourseInformation = new JLabel("COURSE INFORMATION");
		lblCourseInformation.setForeground(new Color(255, 38, 11));
		lblCourseInformation.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblCourseInformation.setBounds(6, 6, 264, 37);
		contentPane.add(lblCourseInformation);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-135, 35, 623, 24);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(16, 224, 117, 29);
		contentPane.add(btnNewButton);
		
		 // Gọi hàm để lấy dữ liệu từ cơ sở dữ liệu
	
		
        setVisible(true);
	}
	public void getData(String classCode) {
	    DatabaseConnection db = new DatabaseConnection();
	    Connection con = db.connectToBB();

	    if (con != null) {
	        try {
	            String sql = "SELECT tc.teacherID, t.name, t.dob, t.address, t.gender, t.phoneNumber " +
	                         "FROM teacherClassroom tc " +
	                         "JOIN teacher t ON tc.teacherID = t.teacherID " +
	                         "JOIN classroom c ON tc.classCode = c.classCode " +
	                         "WHERE tc.classCode = ?";

	            PreparedStatement preparedStatement = con.prepareStatement(sql);
	            preparedStatement.setString(1, classCode);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            while (resultSet.next()) {
	                
	                 teacherName = resultSet.getString("name");
	              
	               

	                // Do something with the retrieved information
	                // For example, create a Teacher object or display the data

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

    // Hàm lấy tên giáo viên dựa vào mã lớp
   

}
