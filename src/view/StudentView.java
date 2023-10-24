package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentView extends JFrame {

	private JPanel contentPane;
	public JTextField textField_MaThiSinh_TimKiem;
	public JTable table;
	public JTextField textField_ID;
	public JTextField textField_HoVaTen;
	public JTextField textField_NgaySinh;
	public JTextField textField_Mon1;
	public JTextField textField_Mon2;
	public JTextField textField_Mon3;
	public ButtonGroup btn_gioiTinh;
	public JComboBox comboBox_queQuan;
	public JRadioButton radioButton_nam;
	public JRadioButton radioButton_nu;
	public JComboBox comboBox_queQuan_timKiem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentView frame = new StudentView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 751);


		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuFile);
		
		JMenuItem menuHome = new JMenuItem("Home");
		menuHome.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuHome);
		  // ActionListener cho menuHome
        menuHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Tạo một instance của MainView
                MainView mainView = new MainView();

                // Hiển thị MainView
                mainView.setVisible(true);

                // Đóng StudentView (nếu bạn muốn đóng StudentView khi chuyển đến MainView)
                dispose();
            }
        });

		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuOpen);

		JMenuItem menuSave = new JMenuItem("Save");
		menuSave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuSave);

		JSeparator separator = new JSeparator();
		menuFile.add(separator);

		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuExit);

		JMenu menuAbout = new JMenu("About");
		menuAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuAbout);

		JMenuItem menuAboutMe = new JMenuItem("About Me");
		menuAboutMe.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuAbout.add(menuAboutMe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBounds(44, 144, -28, -35);
		contentPane.add(verticalBox_1);

		JLabel label_Address = new JLabel("Address");
		label_Address.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_Address.setBounds(31, 11, 92, 54);
		contentPane.add(label_Address);

		JLabel label_Student_ID = new JLabel("Student ID");
		label_Student_ID.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_Student_ID.setBounds(285, 11, 155, 54);
		contentPane.add(label_Student_ID);

		textField_MaThiSinh_TimKiem = new JTextField();
		textField_MaThiSinh_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_MaThiSinh_TimKiem.setColumns(10);
		textField_MaThiSinh_TimKiem.setBounds(393, 12, 123, 54);
		contentPane.add(textField_MaThiSinh_TimKiem);

		JButton btnTim = new JButton("Search");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTim.setBounds(526, 12, 89, 54);
		contentPane.add(btnTim);

		comboBox_queQuan_timKiem = new JComboBox();
		
		comboBox_queQuan_timKiem.setBounds(120, 11, 155, 54);
		contentPane.add(comboBox_queQuan_timKiem);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 92, 738, 2);
		contentPane.add(separator_1);

		JLabel lblListOfStudents = new JLabel("List of students");
		lblListOfStudents.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblListOfStudents.setBounds(10, 97, 251, 54);
		contentPane.add(lblListOfStudents);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
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
				"Student ID", "Name", "Day of Birth", "Address", "Gender", "Phone number", "Credits Completed", "Credits Owed"
			}
		));

		table.setRowHeight(25);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 144, 738, 214);
		contentPane.add(scrollPane);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 369, 738, 2);
		contentPane.add(separator_1_1);

		JLabel lblStudentInformation = new JLabel("Student information");
		lblStudentInformation.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblStudentInformation.setBounds(10, 369, 233, 54);
		contentPane.add(lblStudentInformation);

		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblStudentId.setBounds(10, 412, 102, 54);
		contentPane.add(lblStudentId);

		textField_ID = new JTextField();
		textField_ID.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_ID.setColumns(10);
		textField_ID.setBounds(127, 425, 166, 29);
		contentPane.add(textField_ID);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblName.setBounds(10, 457, 102, 54);
		contentPane.add(lblName);

		textField_HoVaTen = new JTextField();
		textField_HoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_HoVaTen.setColumns(10);
		textField_HoVaTen.setBounds(127, 470, 166, 29);
		contentPane.add(textField_HoVaTen);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblAddress.setBounds(10, 510, 102, 54);
		contentPane.add(lblAddress);

		comboBox_queQuan = new JComboBox();
		comboBox_queQuan.addItem("");
		

		comboBox_queQuan.setBounds(127, 522, 166, 35);
		contentPane.add(comboBox_queQuan);

		JLabel lblDayOfBirth = new JLabel("Day Of Birth");
		lblDayOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblDayOfBirth.setBounds(10, 563, 155, 54);
		contentPane.add(lblDayOfBirth);

		textField_NgaySinh = new JTextField();
		textField_NgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_NgaySinh.setColumns(10);
		textField_NgaySinh.setBounds(127, 576, 166, 29);
		contentPane.add(textField_NgaySinh);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblGender.setBounds(324, 412, 102, 54);
		contentPane.add(lblGender);

		radioButton_nam = new JRadioButton("Nam");
		radioButton_nam.setFont(new Font("Tahoma", Font.PLAIN, 18));
		radioButton_nam.setBounds(405, 429, 61, 23);
		contentPane.add(radioButton_nam);

		radioButton_nu = new JRadioButton("Nữ");
		radioButton_nu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		radioButton_nu.setBounds(489, 429, 61, 23);
		contentPane.add(radioButton_nu);

		btn_gioiTinh = new ButtonGroup();
		btn_gioiTinh.add(radioButton_nam);
		btn_gioiTinh.add(radioButton_nu);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPhoneNumber.setBounds(324, 473, 140, 23);
		contentPane.add(lblPhoneNumber);

		textField_Mon1 = new JTextField();
		textField_Mon1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_Mon1.setColumns(10);
		textField_Mon1.setBounds(526, 470, 166, 29);
		contentPane.add(textField_Mon1);

		JLabel lblCreditsComple = new JLabel("Credits Completed");
		lblCreditsComple.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblCreditsComple.setBounds(324, 513, 160, 23);
		contentPane.add(lblCreditsComple);

		textField_Mon2 = new JTextField();
		textField_Mon2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_Mon2.setColumns(10);
		textField_Mon2.setBounds(526, 510, 166, 29);
		contentPane.add(textField_Mon2);

		JLabel lblCreditsOwed = new JLabel("Credits Owed");
		lblCreditsOwed.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblCreditsOwed.setBounds(324, 557, 160, 23);
		contentPane.add(lblCreditsOwed);

		textField_Mon3 = new JTextField();
		textField_Mon3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_Mon3.setColumns(10);
		textField_Mon3.setBounds(526, 554, 166, 29);
		contentPane.add(textField_Mon3);

		JButton btnThem = new JButton("Add");
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnThem.setBounds(31, 628, 89, 42);
		contentPane.add(btnThem);

		JButton btnXoa = new JButton("Delete");
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnXoa.setBounds(151, 628, 89, 42);
		contentPane.add(btnXoa);

		JButton btnCapNhat = new JButton("Update");
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCapNhat.setBounds(264, 628, 135, 42);
		contentPane.add(btnCapNhat);

		JButton btnLuu = new JButton("Save");
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLuu.setBounds(421, 628, 135, 42);
		contentPane.add(btnLuu);

		JButton btnHuyBo = new JButton("Cancel");
		btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHuyBo.setBounds(585, 628, 135, 42);
		contentPane.add(btnHuyBo);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(15, 628, 733, -22);
		contentPane.add(separator_2);

		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(10, 616, 738, 2);
		contentPane.add(separator_1_1_1);

		JButton btnHuyTim = new JButton("Undo");
		btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHuyTim.setBounds(631, 11, 117, 54);
		contentPane.add(btnHuyTim);

		this.setVisible(true);
	}

	public void xoaForm() {
		textField_ID.setText("");
		textField_HoVaTen.setText("");
		textField_MaThiSinh_TimKiem.setText("");
		textField_NgaySinh.setText("");
		textField_Mon1.setText("");
		textField_Mon2.setText("");
		textField_Mon3.setText("");
		comboBox_queQuan.setSelectedIndex(-1);
		btn_gioiTinh.clearSelection();
	}
	
	
	public void hienThiAbout() {
		JOptionPane.showMessageDialog(this, "Phần mềm quản lý thí sinh 1.0!");
	}

	public void thoatKhoiChuongTrinh() {
		int luaChon = JOptionPane.showConfirmDialog(
			    this,
			    "Bạn có muốn thoải khỏi chương trình?",
			    "Exit",
			    JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}
