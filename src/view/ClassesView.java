package view;

import java.awt.EventQueue;

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
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ClassesController;
import model.Classroom;
import model.Student;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class ClassesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_FindMaLop;
	private JTable table;
	private JTextField textField_MaLop;
	private JTextField textField_TenLop;
	private JTextField textField_SoHSHT;
	private JTextField textField_SoHSTD;

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
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"STT", "M\u00E3 L\u1EDBp", "T\u00EAn L\u1EDBp", "S\u1ED1 h\u1ECDc sinh hi\u1EC7n t\u1EA1i", "S\u1ED1 h\u1ECDc sinh t\u1ED1i \u0111a"
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
		btn_Them.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Them.setBounds(124, 539, 97, 33);
		contentPane.add(btn_Them);
		
		JButton btn_Xoa = new JButton("Xoá");
		btn_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Xoa.setBounds(242, 539, 97, 33);
		contentPane.add(btn_Xoa);
		
		JButton btn_CapNhat = new JButton("Cập Nhật");
		btn_CapNhat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_CapNhat.setBounds(358, 539, 148, 33);
		contentPane.add(btn_CapNhat);
		
		JButton btn_Lưu = new JButton("Lưu");
		btn_Lưu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Lưu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Lưu.setBounds(516, 539, 97, 33);
		contentPane.add(btn_Lưu);
		
		JButton btn_Huy = new JButton("Huỷ bỏ");
		btn_Huy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Huy.setBounds(638, 539, 110, 33);
		contentPane.add(btn_Huy);
		
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		
		});
		
		
	}
	
	public void displayClassList(List<Classroom> classes) {		 
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    // Xóa tất cả các dòng hiện tại trong model
	    model.setRowCount(0);
	    int i=1;
	    for (Classroom classroom : classes) {
	        Object[] rowData = new Object[5];
	        rowData[0] = i++;
	        rowData[1] = classroom.getClassCode();
	        rowData[2] = classroom.getClassName();
	        rowData[3] = classroom.getNumberOfStudents();
	        rowData[4] = classroom.getMaximumNumOfStudents();
	        model.addRow(rowData);
	    }
	    
	    // Cập nhật model của JTable
	    table.setModel(model);
	}
	
}
