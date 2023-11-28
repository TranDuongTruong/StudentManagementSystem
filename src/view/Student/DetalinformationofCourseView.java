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
	private JLabel lbl_Descriptionn;

	

	/**
	 * Create the frame.
	 */
	public DetalinformationofCourseView(String ClassCode) {
		
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
		
		 lblDescription = new JLabel("Duration:");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescription.setBounds(31, 135, 102, 29);
		contentPane.add(lblDescription);
		
		 lbl_Description = new JLabel("Andress:");
		lbl_Description.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Description.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Description.setBounds(31, 178, 102, 29);
		contentPane.add(lbl_Description);
		
		JLabel lblName_info = new JLabel((String) null);
		lblName_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblName_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName_info.setBounds(145, 55, 102, 29);
		contentPane.add(lblName_info);
		lblName_info = new JLabel();
		
		JLabel lblName_teacher = new JLabel((String) null);
		lblName_teacher.setHorizontalAlignment(SwingConstants.LEFT);
		lblName_teacher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName_teacher.setBounds(145, 94, 102, 29);
		contentPane.add(lblName_teacher);
		
		JLabel lbl_Duration = new JLabel((String) null);
		lbl_Duration.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Duration.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_Duration.setBounds(145, 135, 102, 29);
		contentPane.add(lbl_Duration);
		
		JLabel lbl_Descriptionn = new JLabel((String) null);
		lbl_Descriptionn.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Descriptionn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_Descriptionn.setBounds(145, 178, 102, 29);
		contentPane.add(lbl_Descriptionn);
		
		JLabel lblCourseInformation = new JLabel("COURSE INFORMATION");
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
		
		getdata(ClassCode); // Gọi hàm để lấy dữ liệu từ cơ sở dữ liệu
        SetcourseInfo(new Classroom(ClassCode, ClassCode, getDefaultCloseOperation(), getDefaultCloseOperation())); // Truyền một đối tượng Classroom tùy ý, bạn có thể thay thế bằng dữ liệu thực tế từ cơ sở dữ liệu.
        setVisible(true);
	}
	public void getdata(String classcode) {
        DatabaseConnection db = new DatabaseConnection();
        Connection con = db.connectToBB();

        if (con != null) {
            try {
                String sql = "SELECT tc.teacherID, t.name, t.dob, t.address, t.gender, t.phoneNumber, " +
                             "c.duration, c.location " +
                             "FROM teacherClassroom tc " +
                             "JOIN teacher t ON tc.teacherID = t.teacherID " +
                             "JOIN classroom c ON tc.classCode = c.classCode " +
                             "WHERE tc.classCode = ?";
                
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, classcode);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int teacherID = resultSet.getInt("teacherID");
                    String name = resultSet.getString("name");
                    // ... Lấy các trường khác tương tự

                    String duration = resultSet.getString("duration");
                    String location = resultSet.getString("location");

                    System.out.println("Teacher ID: " + teacherID);
                    System.out.println("Name: " + name);
                    // ... In các trường khác tương tự
                    System.out.println("Duration: " + duration);
                    System.out.println("Location: " + location);
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
    
	public void SetcourseInfo(Classroom clas) {
        // Cập nhật thông tin lên giao diện
        lblName_info.setText(clas.getClassName());
        lblName_teacher.setText(getTeacherName(clas.getClassCode()));
        lbl_Duration.setText(clas.getLichhoc());  // Giả sử thời gian học được lưu ở trường Lichhoc
        lbl_Descriptionn.setText(clas.getDiadiem());  // Giả sử địa điểm được lưu ở trường Diadiem
    }

    // Hàm lấy tên giáo viên dựa vào mã lớp
    private String getTeacherName(String classCode) {
        String teacherName = null;
        DatabaseConnection db = new DatabaseConnection();
        Connection con = db.connectToBB();

        if (con != null) {
            try {
                String sql = "SELECT t.name " +
                             "FROM teacherClassroom tc " +
                             "JOIN teacher t ON tc.teacherID = t.teacherID " +
                             "WHERE tc.classCode = ?";
                
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, classCode);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
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

        return teacherName;
    }

}
