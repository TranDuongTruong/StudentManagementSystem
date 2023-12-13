package view.Student;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.util.Random;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;

import controller.Student.ScheduleCtrl;
import controller.Teacher.ClassesController;
import model.ClassesManager;
import model.Classroom;

import view.Teacher.DetalInformationofStudentView;
import controller.DatabaseConnection;
import controller.Admin.LoginController;
import controller.Student.CourseCtrl;
import java.awt.Font;
import javax.swing.JSeparator;
public class CourseView extends JPanel {

	private static final long serialVersionUID = 1L;
	public ClassesManager currentRegisteredClass;
	public ClassesManager model;
	private JTextField textField_MaDangki;
	private JTable tableDSDK;
	public JTable table_Timlop;
	private Object confirmationCode;
	private JTable table_dangky;
	JButton btn_Tim;
	private JTextField textField_FindCourse;
	

	/**
	 * Create the panel.
	 */
	
	public CourseView() {
//		LoginController.studentId=9;
		 DatabaseConnection db = new DatabaseConnection();
	     String [] classCode=db.getRegisteredClassCodes(LoginController.studentId);
	     
	     currentRegisteredClass=db.retrieveClassesFromDatabase(classCode);
	     
		setLayout(null);
		this.setBounds(162, 0, 835, 640);
		JButton btnngK = new JButton("Enroll");
		btnngK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnngK.setBounds(444, 64, 103, 40);
		add(btnngK);
		
		JLabel lblNewLabel = new JLabel("Course code enroll");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(57, 64, 168, 40);
		add(lblNewLabel);
		
		textField_MaDangki = new JTextField();
		textField_MaDangki.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_MaDangki.setBounds(247, 69, 132, 31);
		add(textField_MaDangki);
		textField_MaDangki.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("List of course registered");
		lblNewLabel_2.setBounds(297, 113, 130, 16);
		add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 140, 819, 200);
		add(scrollPane);
		
		table_dangky = new JTable();
		table_dangky.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, "", null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Class code", "Class name", "Schedule", "Location", "Credit hours"
			}
		));
		// Thêm cột mới vào mô hình dữ liệu
		DefaultTableModel tableModel = (DefaultTableModel) table_dangky.getModel();
		tableModel.addColumn("Unenroll");

		// Đặt ButtonRenderer cho cột "Unenroll"
		TableColumn unenrollColumn = table_dangky.getColumnModel().getColumn(tableModel.getColumnCount() - 1);
		unenrollColumn.setCellRenderer(new ButtonRenderer());

		// Đặt ButtonEditor cho cột "Unenroll"
		unenrollColumn.setCellEditor(new ButtonEditor(new JTextField()));
		
		scrollPane.setViewportView(table_dangky);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 418, 829, 216);
		add(scrollPane_1);
		
		table_Timlop = new JTable() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; // Không cho phép chỉnh sửa
		    }
		};
		table_Timlop.setModel(new DefaultTableModel(
		    new Object[][] {
		        {null, null, null, null, "", null},
		        {null, null, null, null, "", null},
		        {null, null, null, null, null, null},
		        {null, null, null, null, null, null},
		    },
		    new String[] {
		        "Class code", "Class name", "Enroll code ", "Credit hours", "Location", "Schedule"
		    }
		));
		scrollPane_1.setViewportView(table_Timlop);

		table_Timlop.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {  
		        if (e.getClickCount() == 2) {
		        	
		            int row = table_Timlop.getSelectedRow();
		            
		           //System.out.println("aaaaaaaaaaaaaaaaaaaaadkjaksaas"+row);
		            DetalinformationofCourseView cou = new DetalinformationofCourseView(model.getClassroom(row).getClassCode(),
		            		model.getClassroom(row).getClassName(),model.getClassroom(row).getDiadiem(),
		            		model.getClassroom(row).getClass_registration_code(),textField_MaDangki);
		            cou.SetcourseInfo();
		            cou.requestFocus();
		            
		            cou.setVisible(true);
		        }
		    }
		});

			
			
			DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
			comboBoxModel.addElement("");
			
			btnngK.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        String registrationCode = textField_MaDangki.getText();
			        Boolean check = true;
			        // Find the corresponding class in the "model" variable based on the registration code
			        Classroom registeredClass = model.findClassroomByCodeRegister(registrationCode);
			        if (registeredClass != null) {
			            for (int i = 0; i < currentRegisteredClass.getClassroomList().size(); i++)
			                if (registrationCode.equals(currentRegisteredClass.getClassroom(i).getClass_registration_code())) {
			                    check = false;
			                    JOptionPane.showMessageDialog(null, "Class already enrolled, enrollment failed");
			                }
			            if (check == true) {
			                // Display the confirmation dialog
			                int response = JOptionPane.showConfirmDialog(
			                        null,
			                        "Are you sure you want to enroll in this class?",
			                        "Confirmation",
			                        JOptionPane.YES_NO_OPTION);

			                if (response == JOptionPane.YES_OPTION) {
			                    // User confirmed, proceed with enrollment
			                    currentRegisteredClass.addClassroom(registeredClass);
			                    // Display the "currentRegisteredClass" list on table_dangky
			                    displayRegisteredClasses(currentRegisteredClass);
//			                    insertStudentClassroom(LoginController.studentId, registeredClass.getClassCode());
			                    JOptionPane.showMessageDialog(null, "Enroll successful!");
			                    textField_MaDangki.setText("");
			                }
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Invalid registration code. Please check again.");
			        }
			    }
			});

		
			
		CourseCtrl courseCtrl = new CourseCtrl(this);
		
		JLabel lblNewLabel_3 = new JLabel("Course");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(252, 370, 82, 27);
		add(lblNewLabel_3);
		
		
		btn_Tim = new JButton("Find");
		btn_Tim.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedCourse = textField_FindCourse.getText().trim();
		        if (selectedCourse.equals("")) {
		            JOptionPane.showMessageDialog(null, "Không có lớp để tìm");
		            displayAvailableClasses(model);
		        } else {
		            ClassesManager findClassroomList = new ClassesManager();
		            for (Classroom classroom : model.getClassroomList()) {
		                if (classroom.getClassCode().contains(selectedCourse)) {
		                    findClassroomList.addClassroom(classroom);
		                }
		            }
		            if (findClassroomList.getClassroomList().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Không tìm thấy lớp học có mã chứa: " + selectedCourse);
		            } else {
		                displayAvailableClasses(findClassroomList);
		            }
		        }
		    }
		});
		btn_Tim.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn_Tim.setBounds(470, 362, 103, 39);
		add(btn_Tim);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(102, 351, 601, 2);
		add(separator_1);
		
		textField_FindCourse = new JTextField();
		textField_FindCourse.setBounds(330, 371, 130, 26);
		add(textField_FindCourse);
		textField_FindCourse.setColumns(10);
		
		JButton btn_Undo = new JButton("Undo");
		btn_Undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAvailableClasses(model);
		        textField_FindCourse.setText("");
			}
		});
		btn_Undo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn_Undo.setBounds(585, 362, 103, 39);
		add(btn_Undo);
		
		
        //ScheduleCtrl sechualeCtrl=new ScheduleCtrl(this);
        
	    }
		
		class ButtonRenderer extends JButton implements TableCellRenderer {
		    public ButtonRenderer() {
		        setOpaque(true);
		    }
	
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        if (isSelected) {
		            setForeground(table.getSelectionForeground());
		            setBackground(table.getSelectionBackground());
		        } else {
		            setForeground(table.getForeground());
		            setBackground(UIManager.getColor("Button.background"));
		        }
		        setText("X");
		        return this;
		    }
		}
		
		class ButtonEditor extends DefaultCellEditor {
		    protected JButton button;
		    private String label;
		    private boolean isPushed;
		    private int rowIndex;

		    public ButtonEditor(JTextField textField) {
		        super(textField);
		        button = new JButton();
		        button.setOpaque(true);
		        button.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                fireEditingStopped();
		                // Xử lý sự kiện khi nút "Unenroll" được nhấp
		                // Lấy chỉ số hàng của nút được nhấp
		                int selectedRow = rowIndex;
		                // Thực hiện unenroll cho hàng tương ứng
		                // ...
		            }
		        });
		    }

		    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		        if (isSelected) {
		            button.setForeground(table.getSelectionForeground());
		            button.setBackground(table.getSelectionBackground());
		        } else {
		            button.setForeground(table.getForeground());
		            button.setBackground(table.getBackground());
		        }
		        label = (value == null) ? "" : value.toString();
		        button.setText(label);
		        isPushed = true;
		        rowIndex = row;
		        return button;
		    }

		    public Object getCellEditorValue() {
		        if (isPushed) {
		        	int[] selectedRows = table_dangky.getSelectedRows();

			        if (selectedRows.length > 0) {
			            // Display the confirmation dialog
			            int response = JOptionPane.showConfirmDialog(
			                    null,
			                    "Are you sure you want to cancel registration for selected classes?",
			                    "Confirmation",
			                    JOptionPane.YES_NO_OPTION);

			            if (response == JOptionPane.YES_OPTION) {
			                // User confirmed, proceed with deletion
			                for (int i = selectedRows.length - 1; i >= 0; i--) {
			                    int selectedRow = selectedRows[i];
			                    String classCode = (String) table_dangky.getValueAt(selectedRow, 0);
			                    if (classCode != null) {

			                        // Remove from currentRegisteredClass
			                        currentRegisteredClass.remove(currentRegisteredClass.findClassroomByCode(classCode));

			                        displayRegisteredClasses(currentRegisteredClass);
			                        //displayAvailableClasses(model);
			                        //setDataToTextField();
//			                        deleteStudentClassroom(LoginController.studentId, classCode);
			                        JOptionPane.showMessageDialog(null, "Cancellation of class registration successful!");
			                    } else {
			                        JOptionPane.showMessageDialog(null, "Please select the classes you want to cancel in the table!");
			                    }
			                }
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Please select at least one class to unenroll registration!");
			        }
		        }
		        isPushed = false;
		        return label;
		    }

		    public boolean stopCellEditing() {
		        isPushed = false;
		        return super.stopCellEditing();
		    }

		    protected void fireEditingStopped() {
		        super.fireEditingStopped();
		    }
		}
	    public void displayRegisteredClasses(ClassesManager classes) {		
			if(table_dangky==null) return;
		    DefaultTableModel tableModel = (DefaultTableModel) table_dangky.getModel();
		    // Xóa tất cả các dòng hiện tại trong model
		    tableModel.setRowCount(0);
		    for (Classroom classroom : classes.getClassroomList()){
		        Object[] rowData = new Object[5];
		        rowData[0] = classroom.getClassCode();
		        rowData[1] = classroom.getClassName();
		        rowData[2] = classroom.getLichhoc();
		        rowData[3] = classroom.getDiadiem();
		        rowData[4] = classroom.getSoTinchi();
		        tableModel.addRow(rowData);
		    }
		    // Cập nhật model của JTable
		    table_dangky.setModel(tableModel);
		}
	    public void displayAvailableClasses(ClassesManager classes) {		
			if(table_Timlop==null) return;
		    DefaultTableModel tableModel = (DefaultTableModel) table_Timlop.getModel();
		    // Xóa tất cả các dòng hiện tại trong model
		   
		    tableModel.setRowCount(0);
		    for (Classroom classroom : classes.getClassroomList()){
		        Object[] rowData = new Object[6];
		        rowData[0] = classroom.getClassCode();
		        rowData[1] = classroom.getClassName();
		        rowData[2] = classroom.getClass_registration_code();
		        rowData[3] = classroom.getSoTinchi();
		        rowData[4] = classroom.getDiadiem();
		        rowData[5] = classroom.getLichhoc();
		        tableModel.addRow(rowData);
		    }
		    // Cập nhật model của JTable
		    table_Timlop.setModel(tableModel);
		}
//	    public void setDataToCombobox(String[] data) {
//	        comboBoxTenMon.setModel(new DefaultComboBoxModel<>(data));
//	    }
	    public void setDataToTextField() {
	        if (model != null) {
	            String[] classCodes = new String[model.getClassroomList().size()];
	            for (int i = 0; i < model.getClassroomList().size(); i++) {
	                classCodes[i] = model.getClassroomList().get(i).getClassCode();
	            }

	            // Chuyển đổi mảng thành chuỗi, sử dụng dấu phân cách nếu cần
	            String classCodeString = String.join(", ", classCodes);

	            // Thiết lập dữ liệu cho` textField_FindCourse
	            textField_FindCourse.setText(classCodeString);
	        }
	    }

	    public void insertStudentClassroom(int studentID, String classCode) {
	        DatabaseConnection db = new DatabaseConnection();
	        Connection con = db.connectToBB();

	        try {
	            String query = "INSERT INTO studentclassroom (studentID, classCode) VALUES (?, ?)";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setInt(1, studentID);
	            stmt.setString(2, classCode);

	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Row inserted successfully.");
	            } else {
	                System.out.println("Failed to insert row.");
	            }
	        } catch (Exception e) {
	            System.err.println("Exception: " + e.getMessage());
	        } finally {
	            try {
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public void deleteStudentClassroom(int studentID, String classCode) {
	        DatabaseConnection db = new DatabaseConnection();
	        Connection con =  db.connectToBB();

	        try {
	            String query = "DELETE FROM studentclassroom WHERE studentID = ? AND classCode = ?";
	            PreparedStatement stmt = con.prepareStatement(query);
	            stmt.setInt(1, studentID);
	            stmt.setString(2, classCode);

	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Row deleted successfully.");
	            } else {
	                System.out.println("Failed to delete row.");
	            }
	        } catch (Exception e) {
	            System.err.println("Exception: " + e.getMessage());
	        } finally {
	            try {
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}


