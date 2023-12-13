package view.Student;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseConnection;
import controller.Admin.LoginController;

import javax.swing.JScrollPane;

public class TranscriptDetailView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public TranscriptDetailView(String classCode) {
		setBackground(new Color(255, 255, 255));
		setBorder(new LineBorder(new Color(255, 0, 0)));
		 setLayout(null);
	        setBounds(162, 0, 835, 640);	  
	        
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(255, 0, 0));
	        panel.setBounds(10, 10, 815, 36);
	        add(panel);
	        panel.setLayout(null);
	        
	        JTextPane txtpnCourseGrades = new JTextPane();
	        txtpnCourseGrades.setBackground(new Color(225, 0, 0));
	        txtpnCourseGrades.setForeground(new Color(255, 255, 255));
	        txtpnCourseGrades.setFont(new Font("Tahoma", Font.BOLD, 13));
	        txtpnCourseGrades.setText("Course Grades");
	        txtpnCourseGrades.setBounds(0, 0, 191, 36);
	        panel.add(txtpnCourseGrades);
	        
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setViewportBorder(new LineBorder(new Color(225, 0, 0)));
	        scrollPane.setBounds(10, 102, 815, 198);
	        add(scrollPane);
	        table = new JTable();
	        table.setModel(new DefaultTableModel(
	            new Object[][] {
	                {"1", "Attendance Score", null, "10", null, "15%"},
	                {"2", "Regular Score", null, "10", null, "10%"},
	                {"3", "Midterm Score", null, "10", null, "20%"},
	                {"4", "Final Score", null, "10", null, "55%"},
	                {"5", "Total", null, null, null, null},
	            },
	            new String[] {
	                "", "Course work", "Original Grade", "Grade Scale", "Grade %", "Max Grade %"
	            }
	        ));

	        table.getColumnModel().getColumn(0).setPreferredWidth(71);
	        table.setBounds(10, 92, 815, 406);
	        
	        scrollPane.setViewportView(table);
	        
			
			  JTextPane txtpnClassMath = new JTextPane(); 
			  txtpnClassMath.setFont(new Font("Tahoma", Font.PLAIN, 14));txtpnClassMath.setForeground(new
			  Color(225, 0, 0)); txtpnClassMath.setText("CLASS:"+getClassName(classCode));
			  txtpnClassMath.setBounds(10, 56, 815, 36);
			  txtpnClassMath.setAlignmentX(Component.CENTER_ALIGNMENT);
			  
			  add(txtpnClassMath);
			 
	        
	        
	        
	        getScores(String.valueOf(LoginController.studentId),classCode);
	        
	}
	private void setTableData(float attendanceScore, float regularScore, float midtermScore, float finalScore, Object total) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    
	    float per=0;
	    model.setValueAt(attendanceScore, 0, 2);
	    
	   per=(float) (attendanceScore*1.5);
	   
	   model.setValueAt(per+"%", 0, 4);
	   
	    model.setValueAt(regularScore, 1, 2);per=(float) (regularScore*1);	
	    model.setValueAt(per+"%", 1, 4);
	    
	    model.setValueAt(midtermScore, 2, 2);per=(float) (midtermScore*2);	
	    model.setValueAt(per+"%", 2, 4);
	    
	    
	    model.setValueAt(finalScore, 3, 2);per=(float) (finalScore*5.5);	
	    if(String.valueOf(finalScore)=="") model.setValueAt("", 3, 2);
	    System.out.println(finalScore+"\tadjhssssssssssssss");
	    model.setValueAt(per+"%", 3, 4);
	    
	    model.setValueAt(total, 4, 4);
	}
	
	public void getScores(String studentID, String classCode) {
		System.err.println("\t"+studentID+"\t"+classCode);
		 DatabaseConnection db;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Lấy kết nối đến cơ sở dữ liệu từ đối tượng DatabaseConnection
            connection = DatabaseConnection.connectToBB();
            
            // Chuẩn bị truy vấn SQL
            String query = "SELECT attendanceScore, regularScore, midtermScore, finalScore, totalScore FROM scores WHERE studentID = ? AND classCode = ?";
            statement = connection.prepareStatement(query);
            
            // Thiết lập giá trị cho các tham số truy vấn
            statement.setString(1, studentID);
            statement.setString(2, classCode);
            
            // Thực thi truy vấn
            resultSet = statement.executeQuery();
            
            // Kiểm tra kết quả truy vấn
            if (resultSet.next()) {
            	
            	
                float attendanceScore = resultSet.getFloat("attendanceScore");
              
                
                
                float regularScore = resultSet.getFloat("regularScore");
                float midtermScore = resultSet.getFloat("midtermScore");
                float finalScore = resultSet.getFloat("finalScore");
                String totalScore = resultSet.getString("totalScore");
                
                setTableData(attendanceScore,regularScore,midtermScore,finalScore,totalScore);
            } else {
                System.out.println("Không tìm thấy điểm số cho sinh viên và lớp học này.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đảm bảo đóng kết nối, câu lệnh và tập kết quả
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	 public String getClassName(String classCode) {
		 	
		 	Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;
	        
	        try {
	            // Lấy kết nối đến cơ sở dữ liệu từ đối tượng DatabaseConnection
	            connection = DatabaseConnection.connectToBB();
	            
	            // Chuẩn bị truy vấn SQL
	            String query = "SELECT className FROM classroom WHERE classCode = ?";
	            statement = connection.prepareStatement(query);
	            
	            // Thiết lập giá trị cho tham số truy vấn
	            statement.setString(1, classCode);
	            
	            // Thực thi truy vấn
	            resultSet = statement.executeQuery();
	            
	            // Kiểm tra kết quả truy vấn
	            if (resultSet.next()) {
	                String className = resultSet.getString("className");
	                return className;
	            } else {
	                return "Không tìm thấy lớp học với mã lớp: " + classCode;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return "Lỗi khi truy vấn cơ sở dữ liệu.";
	        } finally {
	            // Đảm bảo đóng kết nối, câu lệnh và tập kết quả
	            try {
	                if (resultSet != null) {
	                    resultSet.close();
	                }
	                if (statement != null) {
	                    statement.close();
	                }
	                if (connection != null) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	 }
}
