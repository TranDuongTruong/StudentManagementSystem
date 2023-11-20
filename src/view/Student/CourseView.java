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

import view.Teacher.DetalInformationofStudentView;

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
	public 	JComboBox comboBoxTenMon;
	JButton btn_Tim;
	

	/**
	 * Create the panel.
	 */
	
	public CourseView() {
		currentRegisteredClass=new ClassesManager();
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
		scrollPane.setBounds(6, 140, 819, 141);
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
		scrollPane.setViewportView(table_dangky);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 418, 829, 216);
		add(scrollPane_1);
		
		table_Timlop = new JTable();
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
			        String registrationCode = textField_MaDangki.getText();
			        
			        // Tìm lớp học tương ứng với mã đăng ký trong biến "model"
			        Classroom registeredClass = model.findClassroomByCodeRegister(registrationCode);
			        
			        if (registeredClass != null) {
			            // Thêm lớp học vào biến "currentRegisteredClass"
			            currentRegisteredClass.addClassroom(registeredClass);
			            
			            // Xoá lớp học khỏi biến "model"
			            model.getClassroomList().remove(registeredClass);
			            
			            // Hiển thị danh sách "currentRegisteredClass" trên table_dangky
			            displayRegisteredClasses(currentRegisteredClass);
			            displayAvailableClasses(model);
			            setDataToCombobox();
			            
			            JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
			        } else {
			            JOptionPane.showMessageDialog(null, "Mã đăng ký không hợp lệ. Vui lòng kiểm tra lại.");
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
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(102, 351, 601, 2);
		add(separator_1);
		
		JButton btn_delete = new JButton("Delete");
		btn_delete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btn_delete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Lấy chỉ số của các hàng đã chọn trong table_dangky
		        int[] selectedRows = table_dangky.getSelectedRows();
			        // Xoá các hàng đã chọn trong table_dangky và currentRegisteredClass
			        for (int i = selectedRows.length - 1; i >= 0; i--) {
			            int selectedRow = selectedRows[i];
			            String classCode = (String) table_dangky.getValueAt(selectedRow, 0);
			            if(classCode!=null) {
				            // Thêm vào model
				            model.addClassroom(currentRegisteredClass.findClassroomByCode(classCode));
				            // Xoá khỏi currentRegisteredClass
				            currentRegisteredClass.remove(currentRegisteredClass.findClassroomByCode(classCode));
				            
				            displayRegisteredClasses(currentRegisteredClass);
				            displayAvailableClasses(model);
			                setDataToCombobox();
			                JOptionPane.showMessageDialog(null, "Huỷ đăng ký môn thành công!");
			            }
			            else 
			            	JOptionPane.showMessageDialog(null, "Vui lòng chọn các lớp muốn huỷ ở bảng trên!");
			        }
		    }
		});
		btn_delete.setBounds(279, 292, 258, 39);
		add(btn_delete);
		
		
        //ScheduleCtrl sechualeCtrl=new ScheduleCtrl(this);
        
        setVisible(true);
        
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
	    public void setDataToCombobox(String[] data) {
	        comboBoxTenMon.setModel(new DefaultComboBoxModel<>(data));
	    }
	    public void setDataToCombobox() {
		    if (model != null) {
		        String[] classCodes = new String[model.getClassroomList().size()];
		        for (int i = 0; i < model.getClassroomList().size(); i++) {
		            classCodes[i] = model.getClassroomList().get(i).getClassCode();
		        }
		        setDataToCombobox(classCodes);
		    }
		}
}


