package view.Teacher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.DatabaseConnection;
import controller.Teacher.StudentController;
import model.Classroom;
import model.Student;

public class ManageScroresViewP extends JPanel {
	  private JTable table;
	  private static final long serialVersionUID = 1L;
	   Classroom classroom;
	   String classCode;
	/**
	 * Create the panel.
	 */
	public ManageScroresViewP(Classroom classroom, String classCode) {	
		this.classroom=classroom;
		this.classCode = classCode;
		setLayout(null);
        setBounds(162, 0, 835, 640);

        JLabel lblListOfScores = new JLabel("List of scores");
        lblListOfScores.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblListOfScores.setBounds(28, 124, 251, 54);
        add(lblListOfScores);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(28, 212, 738, 214);
        add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"Student ID", "Name", "Attendance Score", "Regular Score", "Miderm Score", "Final Score", "Total Score"
        	}
        ));

        scrollPane.setViewportView(table);
        
        // Fetch data from the database based on the classCode
        fetchDataFromClassroom(classCode);
	              
	}
	 private void fetchDataFromClassroom(String classCode) {
	        try {
	            // Establish database connection (update DatabaseConnection class if needed)
	            Connection connection = DatabaseConnection.connectToBB();

	            // Prepare SQL query
	            String query = "SELECT * FROM studentclassroom WHERE classCode = ?";
	            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	                pstmt.setString(1, classCode);

	                // Execute query and populate the JTable
	                ResultSet resultSet = pstmt.executeQuery();
	                populateTable(resultSet);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle exceptions as needed
	        }
	    }

	 private void populateTable(ResultSet resultSet) throws SQLException {
		    DefaultTableModel model = new DefaultTableModel() {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		            // Make all cells non-editable except for the "Attendance Score," "Regular Score," "Midterm Score," and "Final Score" columns
		            return column >= 2 && column <= 5;
		        }
		    };
		    model.setColumnIdentifiers(new Object[]{"Student ID", "Name", "Attendance Score", "Regular Score", "Midterm Score", "Final Score", "Total Score"});

		    while (resultSet.next()) {
		        model.addRow(new Object[]{
		                resultSet.getInt("studentID"),
		                getStudentNameFromDatabase(resultSet.getInt("studentID")),
		                resultSet.getFloat("attendanceScore"),
		                resultSet.getFloat("regularScore"),
		                resultSet.getFloat("midtermScore"),
		                resultSet.getFloat("finalScore"),
		                resultSet.getString("totalScore")
		        });
		    }

		    table.setModel(model);
		}


	    public static String getStudentNameFromDatabase(int studentID) {
	        String studentName = "";

	        try {
	            // Establish database connection
	            Connection connection = DatabaseConnection.connectToBB();

	            // Prepare SQL query
	            String query = "SELECT name FROM student WHERE studentID = ?";
	            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	                pstmt.setInt(1, studentID);

	                // Execute query
	                ResultSet resultSet = pstmt.executeQuery();

	                // Check if a result is returned
	                if (resultSet.next()) {
	                    studentName = resultSet.getString("name");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle exceptions as needed
	        }

	        return studentName;
	    }
	
}
	
	
	