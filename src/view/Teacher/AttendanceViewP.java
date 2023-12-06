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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.DatabaseConnection;
import controller.Teacher.StudentController;
import model.Classroom;
import model.Student;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class AttendanceViewP extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel contentPane_1;
	public Student selectedStu;
	public JTextField searchInp;
	public JTable table;
	public ButtonGroup bt;
	private JButton btnTim;
	private JButton btnHuyTim;
	public JComboBox comboBox_queQuan;
	static Classroom classRoom;
	 LocalDate currentDate ;
	    DateTimeFormatter formatter ;
	    String dateString;
	 public boolean isUpdating=false;
	public AttendanceViewP(final Classroom classRoom,TeacherAccountMainView mainView ) {
		  currentDate = LocalDate.now();
		     formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
		     dateString = currentDate.format(formatter);
		this.classRoom=classRoom;
		 DatabaseConnection a= new DatabaseConnection();
		 a.connectToBB();
		 this.classRoom.setStudentList(a.retrieveStudentsFromClassroom(classRoom.getClassCode()));
		
		 setLayout(null);
	        setBounds(162, 0, 835, 640);
	        ActionListener action=new StudentController(this);
	        contentPane_1 = new JPanel();
	         contentPane_1.setBackground(new Color(255, 255, 255));
	        contentPane_1.setBounds(0, 0, 835, 640);
	        add(contentPane_1);
	        contentPane_1.setLayout(null);
	       
	        
	        Box verticalBox_1 = Box.createVerticalBox();
	        verticalBox_1.setBounds(44, 144, -28, -35);
	        contentPane_1.add(verticalBox_1);
	        
	        JLabel label_Student_ID = new JLabel("Student ID");
	        label_Student_ID.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        label_Student_ID.setBounds(10, 39, 135, 42);
	        contentPane_1.add(label_Student_ID);
	        
	        searchInp = new JTextField();
	        searchInp.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        searchInp.setColumns(10);
	        searchInp.setBounds(122, 40, 272, 35);
	        contentPane_1.add(searchInp);
	        
	         btnTim = new JButton("Search");
	        btnTim.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnTim.setBounds(416, 40, 117, 41);
	        contentPane_1.add(btnTim);
	        
	        JSeparator separator_1 = new JSeparator();
	        separator_1.setBounds(10, 91, 738, 2);
	        contentPane_1.add(separator_1);
	        
	        JLabel lblListOfStudents = new JLabel("List of students");
	        lblListOfStudents.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblListOfStudents.setBounds(10, 80, 251, 54);
	        contentPane_1.add(lblListOfStudents);
	        
	        JScrollPane scrollPane = new JScrollPane((Component) null);
	        scrollPane.setBounds(10, 130, 798, 464);
	        contentPane_1.add(scrollPane);
	        
	        table = new JTable() {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                if(column==8)return true;
	            	return false; // Không cho phép chỉnh sửa
	                
	            }
	        };

	        DefaultTableModel model = new DefaultTableModel(
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
	                    "Student ID", "Name", "Day of Birth", "Address", "Gender", "Phone number", "Credits Completed", "Credits Owed", "Select"
	                }
	            );

	            // Đặt giá trị checkbox mặc định là false cho tất cả các dòng
	            for (int i = 0; i < model.getRowCount(); i++) {
	                model.setValueAt(false, i, model.getColumnCount() - 1);
	            }
	            table.setModel(model);

	            int selectColumnIndex = table.getColumnCount() - 1;
	            table.getColumnModel().getColumn(selectColumnIndex).setCellRenderer(new CheckboxRenderer());
	            table.getColumnModel().getColumn(selectColumnIndex).setCellEditor(new DefaultCellEditor(new JCheckBox()));

	            // Xử lý sự kiện kích chuột để hiển thị JCheckBox khi nhấp vào ô
	            table.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    int column = table.getColumnModel().getColumnIndexAtX(e.getX());
	                    int row = e.getY() / table.getRowHeight();

	                    if (row < table.getRowCount() && column == selectColumnIndex) {
	                        table.editCellAt(row, column); 
	                        classRoom.getStudentList().get(row).setAttendance(!classRoom.getStudentList().get(row).isAttendance());

	                    }
	                  
	                    
	                }
	            });

	        table.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	if (e.getClickCount() == 2) {
	                	
	                    int row = table.getSelectedRow();
	                   
				    	 
				    	 DetalInformationofStudentView stu=new DetalInformationofStudentView(classRoom.getStudentList().get(row));				    				    	
				    
				    	stu.requestFocus();
				        stu.setVisible(true);
				        stu.setEnabled(true);
				    	    	
				          			          
	                    	                   
	                }
			    }
			   
			});
	        scrollPane.setViewportView(table);
	        
	        bt=new ButtonGroup();
	        
	        JSeparator separator_2 = new JSeparator();
	        separator_2.setForeground(Color.BLACK);
	        separator_2.setBounds(15, 628, 733, -22);
	        contentPane_1.add(separator_2);
	        
	        JSeparator separator_1_1_1 = new JSeparator();
	        separator_1_1_1.setBounds(15, 628, 738, 2);
	        contentPane_1.add(separator_1_1_1);
	        
	         btnHuyTim = new JButton("Undo");
	        btnHuyTim.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnHuyTim.setBounds(580, 40, 117, 41);
	        contentPane_1.add(btnHuyTim);
	        
	        JTextPane txtpnAttendance = new JTextPane();
	        txtpnAttendance.setText("Attendance on "+ dateString);
	        txtpnAttendance.setBounds(10, 10, 161, 35);
	        contentPane_1.add(txtpnAttendance);
	        
	        JButton submitBtn = new JButton("Submit");
	        submitBtn.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		submitAttendance(classRoom, mainView);
	        		
	        	}
	        });
	        submitBtn.setBounds(723, 604, 85, 21);
	        contentPane_1.add(submitBtn);
	        attendanceTables = new HashMap<>();
	        
	   
	        
	        StudentController stu=new StudentController(this,classRoom);
	}
	private Map<String, String> attendanceTables;
	public void fetchAttendanceTables() {
	    DatabaseConnection db = new DatabaseConnection();
	    Connection con = db.connectToBB();

	    try {
	        // Create SQL query to fetch attendance table names
	        String fetchTablesQuery = "SELECT classCode, tableName FROM attendance_tables";

	        // Execute the query to fetch table names
	        Statement statement = con.createStatement();
	        ResultSet resultSet = statement.executeQuery(fetchTablesQuery);

	        while (resultSet.next()) {
	            String classCode = resultSet.getString("classCode");
	            String tableName = resultSet.getString("tableName");

	            attendanceTables.put(classCode, tableName);
	        }

	        System.out.println("Fetched attendance table names");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // After fetching the table names, close the database connection
	        try {
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public void insertIntoAttendanceTables(String classCode, String tableName) {
	  
	    DatabaseConnection db = new DatabaseConnection();
	    Connection con = db.connectToBB();
	   
	    try {
	        // Prepare the SQL query to insert data into attendance_tables
	        String insertQuery = "INSERT INTO attendance_tables (classCode, tableName) VALUES (?, ?)";

	        // Use PreparedStatement to execute the query
	        PreparedStatement statement = con.prepareStatement(insertQuery);
	        statement.setString(1, classCode);
	        statement.setString(2, tableName);

	        // Execute the insert statement
	        statement.executeUpdate();

	        System.out.println("Inserted data into attendance_tables");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // After inserting the data, close the database connection
	        try {
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public void submitAttendance(Classroom classroom, TeacherAccountMainView mainView) {  
		fetchAttendanceTables();
	   

	    DatabaseConnection db = new DatabaseConnection();
	    Connection con = db.connectToBB();
	    String classCode = classroom.getClassCode();

	    // Check if the attendance table for the class exists in the database
	    if (!attendanceTables.containsKey(classCode)) {
	        // Create SQL query to create a new attendance table
	        String createTableQuery = "CREATE TABLE attendance_" + classCode + " ("
	                + "id INT PRIMARY KEY AUTO_INCREMENT,"
	                + "studentID INT,"
	                + "studentName CHAR(255),"
	                + "FOREIGN KEY (studentID) REFERENCES student(studentID)"
	                + ") ENGINE=InnoDB;";

	        try {
	            // Execute the query to create a new attendance table
	            Statement statement = con.createStatement();
	            statement.executeUpdate(createTableQuery);

	            // Store the name of the new attendance table in attendanceTables
	            String attendanceTable = "attendance_" + classCode;
	            

	            System.out.println("Created a new attendance table for class " + classCode);
	            insertIntoAttendanceTables(classCode, "attendance_" + classCode);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    fetchAttendanceTables();
	    List<Student> students1 = classroom.getStudentList();
	    String attendanceTable1 = attendanceTables.get(classCode);
	    try {
	        // Check if the attendance table name is valid
	        if (attendanceTable1 != null && !attendanceTable1.isEmpty()) {
	            // Prepare the SQL query to insert attendance data
	            String insertQuery = "INSERT INTO " + attendanceTable1 + " (studentID, studentName) VALUES (?, ?)";

	            // Use PreparedStatement to execute the query
	            PreparedStatement statement2 = con.prepareStatement(insertQuery);

	            // Iterate over the student list and insert attendance information for each student
	            for (Student student : students1) {
	                int studentID = student.getStudentID();
	                String studentName = student.getName();	         
	                statement2.setInt(1, studentID);
	                statement2.setString(2, studentName);	          	            
	                statement2.executeUpdate();
	            }

	            System.out.println("Inserted attendance information for class " + classCode);
	        } else {
	            System.out.println("Invalid attendance table name");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    }
	    
	    
	    
	    
	    
	    
	    // Check if the dateString column exists in the attendance table
	    String attendanceTable = attendanceTables.get(classCode);
	    try {
	        DatabaseMetaData metaData = con.getMetaData();
	        ResultSet rs = metaData.getColumns(null, null, attendanceTable, dateString);

	        if (!rs.next()) {
	            // If the dateString column doesn't exist, add the column to the attendance table
	            String addColumnQuery = "ALTER TABLE " + attendanceTable + " ADD COLUMN " + dateString + " TINYINT(1)";
	            Statement statement = con.createStatement();
	            statement.executeUpdate(addColumnQuery);

	            System.out.println("Added the column " + dateString + " to the attendance table");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    List<Student> students = classroom.getStudentList();
	    try {
	        // Prepare the SQL query to update attendance data
	        String updateQuery = "UPDATE " + attendanceTable + " SET " + dateString + " = ? WHERE studentID = ?";

	        // Use PreparedStatement to execute the query
	        PreparedStatement statement2 = con.prepareStatement(updateQuery);

	        // Iterate over the student list and update attendance information for each student
	        for (Student student : students) {
	            int studentID = student.getStudentID();
	            boolean attendanceStatus = student.isAttendance();

	            statement2.setBoolean(1, attendanceStatus);
	            statement2.setInt(2, studentID);

	            // Execute the update statement
	            statement2.executeUpdate();
	        }

	        System.out.println("Updated attendance information for class " + classCode);
	        
	       
	        JOptionPane.showMessageDialog(null, "Successful class attendance!", "Attendance", JOptionPane.INFORMATION_MESSAGE);
	        mainView.completeAttendance();
	    
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    // After inserting the data, close the database connection
	    try {
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	 public String getSearchInp() {
	        String text = searchInp.getText();
	        
	        if (text == null || text.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sinh viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return "";
	        }

	        // Kiểm tra xem text có chứa từ hoặc ký tự đặc biệt hay không
	        if (text.matches(".*\\W+.*")) {
	            JOptionPane.showMessageDialog(null, "Mã sinh viên không được chứa từ hoặc ký tự đặc biệt!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return "";
	        }
	        if (!text.matches("\\d+")) {
	            JOptionPane.showMessageDialog(null, "Mã sinh viên phải là số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            return "";
	        }
	        // Tiếp tục xử lý logic của bạn nếu text hợp lệ
	        // ...
	        return (text);
	    }
	    public void notFindStudent(String id) {
	    	JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên có MSV: "+id, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	    }
		 public void searchStudentListener(ActionListener listener) {
			 btnTim.addActionListener(listener);
	     }
		 public void huyTimListener(ActionListener listener) {
			 btnHuyTim.addActionListener(listener);
	     }
		 public void displayStudentList(List<Student> studentList) {		 
			    DefaultTableModel model = (DefaultTableModel) table.getModel();
			    // Xóa tất cả các dòng hiện tại trong model
			    model.setRowCount(0);
			    System.out.println("\tfffffffffff"+studentList.size());
			    for (Student student : studentList) {
			        Object[] rowData = new Object[8];
			        rowData[0] = student.getStudentID();
			        rowData[1] = student.getName();
			        rowData[2] = student.getDob();
			        rowData[3] = student.getAddress();
			        rowData[4] = student.isGender() ? "Male" : "Female";
			        rowData[5] = student.getPhoneNumber();
			        rowData[6] = student.getCreditsCompleted();
			        rowData[7] = student.getCreditsOwed();
			        System.out.println("\tfffffffffff"+rowData[1]);
			        model.addRow(rowData);
			    }
			    
			    // Cập nhật model của JTable
			    table.setModel(model);
			}
		 
		 

		 public int getIndexofSelectedRow() {
			
			 int index=table.getSelectedRow(); 
			 selectedStu=classRoom.getStudentList().get(index);
			 return index;
		 }
	}


class CheckboxRenderer extends JCheckBox implements TableCellRenderer {

    private static final long serialVersionUID = 1L;

    public CheckboxRenderer() {
        setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setSelected(value != null && (Boolean) value);
        return this;
    }
}
