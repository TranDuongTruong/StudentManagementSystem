package view.Teacher;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseConnection;
import controller.Teacher.ClassesController;
import model.ClassesManager;
import model.Classroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class ClassesViewP extends JPanel {
	public JTextField textField_FindMaLop;
	private JTable table;
	JButton btn_Tim;
	JButton btnHuyTim;public ClassesManager model=new ClassesManager();JPanel classesPane;
	public Classroom currentClassroom;
	private static final long serialVersionUID = 1L;
	TeacherAccountMainView mainView;JButton attendanceBtn ;
	public ClassesManager classes;
	/**
	 * Create the panel.
	 */
	public ClassesViewP(TeacherAccountMainView mainView) {
		this.mainView=mainView;
		 setLayout(null);
	        setBounds(162, 0, 835, 640);
	        
	         classesPane = new JPanel();
	         classesPane.setBorder(new LineBorder(new Color(255, 0, 0), 2));
	        classesPane.setBackground(new Color(255, 255, 255));
	        classesPane.setBounds(0, 0, 835, 640);
	        add(classesPane);
	   
	        
	        classesPane.setLayout(null);
	        JLabel lb_FindMaLop = new JLabel("Class Code");
			lb_FindMaLop.setHorizontalAlignment(SwingConstants.CENTER);
			lb_FindMaLop.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lb_FindMaLop.setBounds(63, 30, 140, 48);
			classesPane.add(lb_FindMaLop);
			
			textField_FindMaLop = new JTextField();
			textField_FindMaLop.setHorizontalAlignment(SwingConstants.CENTER);
			textField_FindMaLop.setFont(new Font("Tahoma", Font.PLAIN, 23));
			textField_FindMaLop.setBounds(213, 30, 228, 48);
			classesPane.add(textField_FindMaLop);
			textField_FindMaLop.setColumns(10);
			
			 btn_Tim = new JButton("Find");
			 btn_Tim.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 	}
			 });
			btn_Tim.setFont(new Font("Tahoma", Font.PLAIN, 25));
			btn_Tim.setBounds(465, 30, 97, 48);
			classesPane.add(btn_Tim);
			
			JLabel lblDanhSchCc = new JLabel("List of classes");
			lblDanhSchCc.setHorizontalAlignment(SwingConstants.CENTER);
			lblDanhSchCc.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblDanhSchCc.setBounds(63, 111, 177, 41);
			classesPane.add(lblDanhSchCc);

			 DefaultTableModel model = new DefaultTableModel(
		                new Object[][] {
		                        {null, null, null, null},
		                        {null, null, null, null},
		                        {null, null, null, null},
		                        {null, null, null, null},
		                        {null, null, null, null},
		                        {null, null, null, null},
		                        {null, null, null, null},
		                        {null, null, null, null}
		                },
		                new String[] {
		                		"Class Code", "Class Name", "Current Number of Students", "Maximum Number of Students"
		                }
		        ) {
		            // Ghi đè phương thức isCellEditable để không cho phép chỉnh sửa
		            public boolean isCellEditable(int row, int column) {
		                return false;
		            }
		        };
		        
		        // Tạo JTable với DefaultTableModel tùy chỉnh
		        table = new JTable(model);
		        table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 0, 0), new Color(255, 0, 0), new Color(255, 0, 0), new Color(255, 0, 0)));
		        
		        // Thiết lập font cho bảng
		        table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		        
			table.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	if (e.getClickCount() == 2) {
	                	System.out.println("aaaaaa");
	                    int row = table.getSelectedRow();
	                   
				    
				    	
				    	Classroom classRoom=classes.getClassroomList().get(row);
				    	
				    	
				    //	System.err.println("aaaaaaaaa"+classRoom.getClassCode());	
				    	
				    	 mainView.loadStudentView(classRoom);
	                }
			    }
			});
			//acessToListOfStudents();
			
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setViewportBorder(new LineBorder(new Color(255, 0, 0)));
			scrollPane.setBounds(96, 150, 635, 426);
			classesPane.add(scrollPane);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(73, 98, 601, 2);
			classesPane.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(63, 628, 645, 2);
			classesPane.add(separator_1);
			
			 btnHuyTim = new JButton("Undo");
			btnHuyTim.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnHuyTim.setBounds(572, 30, 110, 48);
			classesPane.add(btnHuyTim);
			
			 attendanceBtn = new JButton("Attendance");
			attendanceBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Classroom classRoom=classes.getClassroomList().get(getSelectedRowIndex());
			    	
			    	
				    //	System.err.println("aaaaaaaaa"+classRoom.getClassCode());	
				    	
				    	 mainView.loadAttendanceView(classRoom);
				}
			});
			
			attendanceBtn.setBounds(96, 581, 155, 37);
			classesPane.add(attendanceBtn);
			
			JButton btnManageScore = new JButton("Manage Score");

			//Start: Vo Van Minh
			btnManageScore.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        int selectedRowIndex = getSelectedRowIndex();

			        if (selectedRowIndex != -1) { // Check if any row is selected
			            String classCode = (String) table.getValueAt(selectedRowIndex, 0);
			            // Now you have the classCode, you can use it as needed
			            Classroom classRoom = classes.getClassroomList().get(selectedRowIndex);
			            mainView.loadManageScoreView(classRoom, classCode);
			        } else {
			            // Inform the user to select a row before clicking the button
			            // You can show a JOptionPane or update a JLabel to display a message
			            // For example:
			            // JOptionPane.showMessageDialog(ClassesViewP.this, "Please select a row.", "Information", JOptionPane.INFORMATION_MESSAGE);
			            // Or update a JLabel
			            // infoLabel.setText("Please select a row.");
			        }
			    }
			});
			//End: Vo Van Minh


			btnManageScore.setBounds(272, 581, 155, 37);
			classesPane.add(btnManageScore);
			
			ClassesController control= new ClassesController(this);
	}
	
	
	public void displayClassList(ClassesManager classes) {	
		this.classes=classes;
		if(table==null) return;
	    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	    // Xóa tất cả các dòng hiện tại trong model
	    tableModel.setRowCount(0);
	    for (Classroom classroom : classes.getClassroomList()){
	        Object[] rowData = new Object[4];
	        rowData[0] = classroom.getClassCode();
	        rowData[1] = classroom.getClassName();
	        rowData[2] = classroom.getNumberOfStudents();
	        rowData[3] = classroom.getMaximumNumOfStudents();
	        tableModel.addRow(rowData);
	    }
	    // Cập nhật model của JTable
	    table.setModel(tableModel);
	}
//Nguyen Hoang Viet	
	public void deleteForm() {
		textField_FindMaLop.setText("");
		
	}
	public void hideUI() {
	    setVisible(false);
	}
	
	public int getSelectedRowIndex() {
	    return table.getSelectedRow();
	}
//Tran duong truong
	
	public String getClassCode() {
		return textField_FindMaLop.getText();
	}
	public void searchClassListener(ActionListener listener) {
		if(btn_Tim==null)return;
		 btn_Tim.addActionListener(listener);
    }
	public void undoClassListener(ActionListener listener) {
		if(btnHuyTim==null)return;
		btnHuyTim.addActionListener(listener);
    }
	public void attendanceListener(ActionListener listener) {
		if(attendanceBtn==null)return;
		attendanceBtn.addActionListener(listener);
    }
	public int getIndexofClassToDelete() {
			 int index=table.getSelectedRow();;
			 return index;
	}
}

