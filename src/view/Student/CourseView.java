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
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

import controller.Student.ScheduleCtrl;
import controller.Teacher.ClassesController;
import model.ClassesManager;
import model.Classroom;
import view.Teacher.ClassesView;
import view.Teacher.DetalInformationofStudentView;

import controller.Student.CourseCtrl;
import java.awt.Font;
public class CourseView extends JPanel {

	private static final long serialVersionUID = 1L;
	public ClassesManager currentRegisteredClass;
	public ClassesManager model;
	private JTextField textField_MaDangki;
	private JTextField textField_MaXacNhan;
	private JTextField textField_2;
	private JTable tableDSDK;
	public JTable table_Timlop;
	private Object confirmationCode;
	private JTable table_dangky;
	public 	JComboBox comboBoxTenMon;
	JButton btn_Tim;
	

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
		
		textField_MaDangki = new JTextField();
		textField_MaDangki.setBounds(154, 37, 130, 26);
		add(textField_MaDangki);
		textField_MaDangki.setColumns(10);
		
		textField_MaXacNhan = new JTextField();
		textField_MaXacNhan.setBounds(524, 37, 130, 26);
		add(textField_MaXacNhan);
		textField_MaXacNhan.setColumns(10);
        textField_MaXacNhan.setForeground(Color.GRAY);
		
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
				{null, null, null, null, "", null, null},
				{null, null, null, null, "", null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 l\u1EDBp", "T\u00EAn l\u1EDBp", "M\u00E3 \u0111\u0103ng k\u00ED", "S\u1ED1 t\u00EDn ch\u1EC9", "\u0110\u1ECBa \u0111i\u1EC3m", "L\u1ECBch h\u1ECDc", "Ch\u1ECDn"
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
			comboBoxTenMon.setBounds(344, 364, 120, 33);
			add(comboBoxTenMon);
			
		btnngK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String confirmationCode = textField_MaXacNhan.getText();
                
                // Kiểm tra nếu confirmationCode trùng với số ngẫu nhiên đã tạo
                if (confirmationCode.equals(String.valueOf(randomNumber))) {
                	displayRegisteredClasses(currentRegisteredClass);
                	JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
                	 
                } else {
                    // Thực hiện hành động khi confirmationCode không trùng
                	JOptionPane.showMessageDialog(null, "Mã xác nhận không đúng. Vui lòng kiểm tra lại.");
                	
                }
            }
            
        });
		CourseCtrl courseCtrl = new CourseCtrl(this);
		
		JLabel lblNewLabel_3 = new JLabel("Chọn cn");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(252, 370, 80, 21);
		add(lblNewLabel_3);
		
		
		btn_Tim = new JButton("Find");
		btn_Tim.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		String selectedCourse = (String) comboBoxTenMon.getSelectedItem();
				String classCode=selectedCourse;
	        	ClassesManager findClassroomList=new ClassesManager();
	        	Classroom classroom;
	        	classroom=model.findClassroomByCode(classCode);
	        	findClassroomList.addClassroom(classroom);
	        	displayAvailableClasses(findClassroomList);   
		 	}
		 });
		btn_Tim.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btn_Tim.setBounds(544, 364, 103, 39);
		add(btn_Tim);
		
		
        //ScheduleCtrl sechualeCtrl=new ScheduleCtrl(this);
        
        setVisible(true);
        
	    }
	    
	    
	    public void displayRegisteredClasses(ClassesManager classes) {		
			if(table_Timlop==null) return;
		    DefaultTableModel tableModel = (DefaultTableModel) table_Timlop.getModel();
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
		    table_Timlop.setModel(tableModel);
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
	    public void setDataToCombobox(String[] data) {
	        comboBoxTenMon.setModel(new DefaultComboBoxModel<>(data));
	    }
	    
	    
}


