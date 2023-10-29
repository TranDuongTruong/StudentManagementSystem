package view;

import java.awt.EventQueue;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JMenu;
import javax.swing.ButtonGroup;

import com.mysql.cj.exceptions.CJOperationNotSupportedException;
import com.mysql.cj.protocol.Message;

import controller.ClassesController;
import controller.DatabaseConnection;
import model.Classroom;
import model.Student;
import model.StudentManager;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class ClassesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public StudentManager model=new StudentManager();
	public JTextField textField_FindMaLop;
	private JTable table;
	public JTextField textField_MaLop;
	public JTextField textField_TenLop;
	public JTextField textField_SoHSHT;
	public JTextField textField_SoHSTD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassesView frame = new ClassesView();
					frame.setVisible(true);
					ClassesController control= new ClassesController(frame);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClassesView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		ActionListener action= new ClassesController(this);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 97, 583);
		contentPane.add(panel);
		
		JLabel label = new JLabel();
		panel.add(label);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setAlignmentX(0.0f);
		panel.add(verticalBox);
		
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDashboard.setMaximumSize(new Dimension(100, 20));
		btnDashboard.setBackground(Color.LIGHT_GRAY);
		verticalBox.add(btnDashboard);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);
		
		JButton button_1 = new JButton("Classes");
		button_1.setMaximumSize(new Dimension(100, 20));
		button_1.setBorder(null);
		button_1.setBackground(Color.WHITE);
		verticalBox.add(button_1);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);
		
		JButton button_2 = new JButton("Student");
		button_2.setMaximumSize(new Dimension(100, 20));
		button_2.setBackground(Color.LIGHT_GRAY);
		verticalBox.add(button_2);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);
		
		JButton button_3 = new JButton("Blog");
		button_3.setMaximumSize(new Dimension(100, 20));
		button_3.setBackground(Color.LIGHT_GRAY);
		verticalBox.add(button_3);
		
		Component verticalStrut_3 = Box.createVerticalStrut(40);
		verticalBox.add(verticalStrut_3);
		
		JButton btnNewButton = new JButton("<- Log Out");
		verticalBox.add(btnNewButton);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		
		JLabel lb_FindMaLop = new JLabel("Mã Lớp");
		lb_FindMaLop.setHorizontalAlignment(SwingConstants.CENTER);
		lb_FindMaLop.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lb_FindMaLop.setBounds(124, 29, 121, 48);
		contentPane.add(lb_FindMaLop);
		
		textField_FindMaLop = new JTextField();
		textField_FindMaLop.setHorizontalAlignment(SwingConstants.CENTER);
		textField_FindMaLop.setFont(new Font("Tahoma", Font.PLAIN, 23));
		textField_FindMaLop.setBounds(274, 29, 339, 48);
		contentPane.add(textField_FindMaLop);
		textField_FindMaLop.setColumns(10);
		
		JButton btn_Tim = new JButton("Tìm");
		btn_Tim.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn_Tim.setBounds(638, 29, 97, 48);
		contentPane.add(btn_Tim);
		
		JLabel lblDanhSchCc = new JLabel("Danh sách các lớp");
		lblDanhSchCc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSchCc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDanhSchCc.setBounds(124, 110, 177, 41);
		contentPane.add(lblDanhSchCc);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"M\u00E3 L\u1EDBp", "T\u00EAn L\u1EDBp", "S\u1ED1 h\u1ECDc sinh hi\u1EC7n t\u1EA1i", "S\u1ED1 h\u1ECDc sinh t\u1ED1i \u0111a"
			}
		));
		
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int row = table.rowAtPoint(e.getPoint());
		        int column = table.columnAtPoint(e.getPoint());
		        
		        // Xử lý khi nhấn chuột vào hàng và cột cụ thể
		        if (row >= 0 && column >= 0) {
		            
		        }
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(134, 152, 635, 218);
		contentPane.add(scrollPane);
		
		JLabel lb_Malop = new JLabel("Mã Lớp");
		lb_Malop.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Malop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_Malop.setBounds(124, 409, 97, 41);
		contentPane.add(lb_Malop);
		
		textField_MaLop = new JTextField();
		textField_MaLop.setBounds(231, 409, 164, 41);
		contentPane.add(textField_MaLop);
		textField_MaLop.setColumns(10);
		
		JLabel lb_TenLop = new JLabel("Tên Lớp");
		lb_TenLop.setHorizontalAlignment(SwingConstants.CENTER);
		lb_TenLop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_TenLop.setBounds(124, 470, 97, 41);
		contentPane.add(lb_TenLop);
		
		textField_TenLop = new JTextField();
		textField_TenLop.setColumns(10);
		textField_TenLop.setBounds(231, 470, 164, 41);
		contentPane.add(textField_TenLop);
		
		JLabel lb_SoHSHT = new JLabel("Số HSHT");
		lb_SoHSHT.setHorizontalAlignment(SwingConstants.CENTER);
		lb_SoHSHT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_SoHSHT.setBounds(431, 409, 97, 41);
		contentPane.add(lb_SoHSHT);
		
		textField_SoHSHT = new JTextField();
		textField_SoHSHT.setColumns(10);
		textField_SoHSHT.setBounds(538, 413, 164, 41);
		contentPane.add(textField_SoHSHT);
		
		JLabel lb_SoHSTD = new JLabel("Số HSTD");
		lb_SoHSTD.setHorizontalAlignment(SwingConstants.CENTER);
		lb_SoHSTD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_SoHSTD.setBounds(431, 470, 97, 41);
		contentPane.add(lb_SoHSTD);
		
		textField_SoHSTD = new JTextField();
		textField_SoHSTD.setColumns(10);
		textField_SoHSTD.setBounds(538, 474, 164, 41);
		contentPane.add(textField_SoHSTD);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(134, 97, 601, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(134, 394, 601, 2);
		contentPane.add(separator_1);
		
		JButton btn_Them = new JButton("Thêm");
		btn_Them.addActionListener(action);
		btn_Them.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Them.setBounds(124, 539, 97, 33);
		contentPane.add(btn_Them);
		
		JButton btn_Xoa = new JButton("Xoá");
		btn_Xoa.addActionListener(action);
		btn_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Xoa.setBounds(242, 539, 97, 33);
		contentPane.add(btn_Xoa);
		
		JButton btn_CapNhat = new JButton("Cập Nhật");
		btn_CapNhat.addActionListener(action);
		btn_CapNhat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_CapNhat.setBounds(358, 539, 148, 33);
		contentPane.add(btn_CapNhat);
		
		JButton btn_Luu = new JButton("Lưu");
		btn_Luu.addActionListener(action);
		btn_Luu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Luu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Luu.setBounds(516, 539, 97, 33);
		contentPane.add(btn_Luu);
		
		JButton btn_Huy = new JButton("Huỷ bỏ");
		btn_Huy.addActionListener(action);
		btn_Huy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Huy.setBounds(638, 539, 110, 33);
		contentPane.add(btn_Huy);
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		
		});
		
		
	}
	
	public void displayClassList(StudentManager classes) {		
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
	
	public void xoaForm() {
		textField_FindMaLop.setText("");
		textField_MaLop.setText("");
		textField_SoHSHT.setText("");
		textField_SoHSTD.setText("");
		textField_TenLop.setText("");
	}
	
	

	public void ThemHoacCapNhatLop() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		//Get dữ liệu
        String classCode = this.textField_MaLop.getText();
        String className = this.textField_TenLop.getText();
        int numOfCurentStudents = Integer.valueOf(this.textField_SoHSHT.getText());
        int maximumNumOfStudents =Integer.valueOf(this.textField_SoHSTD.getText());;
        
        Classroom lop= new Classroom(classCode, className, numOfCurentStudents, maximumNumOfStudents);
        
		if(this.model.kiemTraTonTai(lop)==true) {
			this.model.update(lop);
			for (int i = 0; i < tableModel.getRowCount(); i++) {
				String id=tableModel.getValueAt(i, 0)+"";
				if(id.equals(lop.getClassCode()+"")) {
					tableModel.setValueAt(lop.getClassCode(), i, 0);
					tableModel.setValueAt(lop.getClassName(), i, 1);
					tableModel.setValueAt(lop.getNumberOfStudents()+"", i, 2);
					tableModel.setValueAt(lop.getMaximumNumOfStudents()+"", i, 3);
				}
			}
		}else {
			this.model.addClassroom(lop);
			tableModel.addRow(new Object[] {lop.getClassCode(),lop.getClassName(),lop.getNumberOfStudents(),lop.getMaximumNumOfStudents()});
		}
}

	public Classroom getLopDaChon() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		int i_row= table.getSelectedRow();
		String classCode = tableModel.getValueAt(i_row,0)+"";
        String className = tableModel.getValueAt(i_row,1)+"";
        int numOfCurentStudents = Integer.valueOf(tableModel.getValueAt(i_row,2)+"");
        int maximumNumOfStudents =Integer.valueOf(tableModel.getValueAt(i_row,3)+"");
        Classroom classes = new Classroom(classCode, className, numOfCurentStudents, maximumNumOfStudents);
		return classes;
	}
	public void hienthiThongTinLopDaChon() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		int i_row= table.getSelectedRow();
		String classCode = tableModel.getValueAt(i_row,0)+"";this.textField_MaLop.setText(classCode);
        String className = tableModel.getValueAt(i_row,1)+"";this.textField_TenLop.setText(className);
        int numOfCurentStudents = Integer.valueOf(tableModel.getValueAt(i_row,2)+"");this.textField_SoHSHT.setText(numOfCurentStudents+"");
        int maximumNumOfStudents =Integer.valueOf(tableModel.getValueAt(i_row,3)+"");this.textField_SoHSTD.setText(maximumNumOfStudents+"");
		
        
        
	}
	
	public void ThucHienXoa() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		int i_row= table.getSelectedRow();
		int luachon= JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xoá lớp đã chọn?");
		if(luachon== JOptionPane.YES_OPTION) {
//			this.model.remove(getLopDaChon());
			tableModel.removeRow(i_row);
		}
	}

	public void ThucHienTim() {
		String malop= this.textField_FindMaLop.getText();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		int soLuongDong = tableModel.getRowCount();
		for(int i=0;i<soLuongDong;i++) {
			String maLopTrongTable = tableModel.getValueAt(i, 0) + "";
			if(!malop.equals(tableModel))
				tableModel.removeRow(i);
		}
		
	}
	
	public void huyTim() {
		textField_FindMaLop.setText("");
	}
	
}