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

public class manageScroresViewP extends JPanel {

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
	/**
	 * Create the panel.
	 */
	public manageScroresViewP(final Classroom classRoom) {
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
	           
	            table.setModel(model);

	            

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
	        		
	        		
	        	}
	        });
	        submitBtn.setBounds(723, 604, 85, 21);
	        contentPane_1.add(submitBtn);
	      
	        
	   
	        
	        StudentController stu=new StudentController(this,classRoom);
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


