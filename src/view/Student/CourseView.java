package view.Student;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.util.Random;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

import controller.Student.ScheduleCtrl;
import model.ClassesManager;
import view.Teacher.DetalInformationofStudentView;

import controller.Student.CourseCtrl;
public class CourseView extends JPanel {

	private static final long serialVersionUID = 1L;
	public ClassesManager currentRegisteredClass;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable tableDSDK;
	private JTable table_Timlop;
	private Object confirmationCode;
	private JTextField textField_3;
	private JTable table_dangky;
	private 	JComboBox comboBoxTenMon;
	
	/**
	 * Create the panel.
	 */
	public CourseView() {
		
		setLayout(null);
		this.setBounds(162, 0, 835, 640);
		JButton btnngK = new JButton("Đăng ký");
		btnngK.setBounds(297, 79, 85, 21);
		add(btnngK);
		
		JLabel lblNewLabel = new JLabel("Mã Đăng ký");
		lblNewLabel.setBounds(70, 42, 61, 16);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã xác nhận");
		lblNewLabel_1.setBounds(451, 42, 61, 16);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(154, 37, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(524, 37, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
        textField_1.setForeground(Color.GRAY);
		
		JButton btnNewButton = new JButton("tìm kiếm");
		btnNewButton.setBounds(586, 365, 117, 29);
		add(btnNewButton);
		
		textField_2 = new JTextField();
		textField_2.setBounds(655, 37, 48, 26);
		add(textField_2);
		textField_2.setColumns(10);
		
		Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000);
        textField_2.setText(String.valueOf(randomNumber));
		
		JLabel lblNewLabel_2 = new JLabel("Danh sách đã đăng ký thành công");
		lblNewLabel_2.setBounds(266, 112, 221, 16);
		add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 140, 823, 213);
		add(scrollPane);
		
		table_dangky = new JTable();
		table_dangky.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, "", null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 l\u1EDBp", "T\u00EAn l\u1EDBp", "L\u1ECBch h\u1ECDc", "\u0110\u1ECBa \u0111i\u1EC3m", "S\u1ED1 t\u00EDn ch\u1EC9", "Hu\u1EF7"
			}
		));
		scrollPane.setViewportView(table_dangky);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 418, 829, 216);
		add(scrollPane_1);
		
		table_Timlop = new JTable();
		table_Timlop.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, "", null, null},
				{null, null, null, "", null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"T\u00EAn l\u1EDBp", "M\u00E3 \u0111\u0103ng k\u00ED", "S\u1ED1 t\u00EDn ch\u1EC9", "\u0110\u1ECBa \u0111i\u1EC3m", "L\u1ECBch h\u1ECDc", "Ch\u1ECDn"
			}
		));
		scrollPane_1.setViewportView(table_Timlop);
		table_Timlop.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if (e.getClickCount() == 2) {
                	
                    int row = table_Timlop.getSelectedRow();
//                    DetalinformationofCourseView cv=new DetalinformationofCourseView(ClassesManager.)
//                    cv.setVisible(true);
                    
//			    	 DetalInformationofStudentView stu=new DetalInformationofStudentView(classRoom.getStudentList().get(row));
//			    	 stu.setVisible(true);
			
                    	                   
                }
		    }
		});
			
			 comboBoxTenMon = new JComboBox();
			comboBoxTenMon.setBounds(338, 365, 40, 28);
			add(comboBoxTenMon);
			
			textField_3 = new JTextField();
			textField_3.setText("chọn cn");
			textField_3.setColumns(10);
			textField_3.setBounds(235, 358, 104, 41);
			add(textField_3);
			
		btnngK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String confirmationCode = textField_1.getText();
                
                // Kiểm tra nếu confirmationCode trùng với số ngẫu nhiên đã tạo
                if (confirmationCode.equals(String.valueOf(randomNumber))) {
                    // Thực hiện hành động khi confirmationCode trùng
                	
                	 JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
                	 
                } else {
                    // Thực hiện hành động khi confirmationCode không trùng
                	JOptionPane.showMessageDialog(null, "Mã xác nhận không đúng. Vui lòng kiểm tra lại.");
                	
                }
            }
            
        });
		CourseCtrl courseCtrl = new CourseCtrl(this);
        //ScheduleCtrl sechualeCtrl=new ScheduleCtrl(this);
        
        setVisible(true);
        
	    }
		// Phương thức để hiển thị danh sách đã đăng ký thành công
	    private void displayRegisteredCourses() {
	        // Thực hiện logic để hiển thị danh sách đã đăng ký thành công
	        // ...
	    }
	
	    // Phương thức để tìm kiếm lớp học
	    private void searchForCourses() {
	        // Thực hiện logic để tìm kiếm lớp học
	        // ...
	    }
	
	    // Phương thức để cập nhật bảng danh sách đã đăng ký
	    private void updateRegisteredCoursesTable() {
	        // Thực hiện logic để cập nhật bảng danh sách đã đăng ký
	        // ...
	    }// Phương thức để cập nhật bảng tìm kiếm lớp học
	    private void updateSearchCoursesTable() {
	        // Thực hiện logic để cập nhật bảng tìm kiếm lớp học
	        // ...
	    }
	    public void setDataToCombobox(String[] data) {
	    	DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(data);

	        // Tạo JComboBox sử dụng DefaultComboBoxModel
	        
	    	comboBoxTenMon.setBounds(50, 50, 150, 30);

	        // Thêm ActionListener để xử lý sự kiện khi một phần tử được chọn
	    	comboBoxTenMon.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Xử lý sự kiện ở đây...
	                System.out.println("Selected Item: " + comboBoxTenMon.getSelectedItem());
	            }
	        });
	    }
	   
	
}