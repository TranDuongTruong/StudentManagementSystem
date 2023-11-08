package view.Teacher;

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
import java.io.File;
import java.net.URL;
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

import controller.DatabaseConnection;
import controller.Admin.LoginController;
import controller.Teacher.ClassesController;
import model.Classroom;
import model.Student;
import view.Admin.LoginView;
import model.ClassesManager;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class ClassesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel classesPane;
	public JPanel getClassesPane() {
		return classesPane;
	}

	public void setClassesPane(JPanel classesPane) {
		this.classesPane = classesPane;
	}
	public ClassesManager model=new ClassesManager();
	public JTextField textField_FindMaLop;
	private JTable table;
	public JTextField textField_MaLop;
	public JTextField textField_TenLop;
	public JTextField textField_SoHSHT;
	public JTextField textField_SoHSTD;
	JButton btn_Tim;
	JButton btnHuyTim;
	JButton btn_Them;
	JButton btn_Huy;
	JButton btn_Luu;
	JButton btn_CapNhat;
	JButton btn_Xoa;
	public Classroom currentClassroom;
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
		classesPane = new JPanel();
		classesPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(classesPane);
		classesPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 97, 622);
		classesPane.add(panel);

		ImageIcon imageIcon = new ImageIcon();
		URL imageUrl = ClassesView.class.getClassLoader().getResource("Assert/teacher/DTULogo.png");
		File file = new File(imageUrl.getPath());
		imageIcon = new ImageIcon(file.getAbsolutePath());

		// Lấy hình ảnh từ ImageIcon
		Image image = imageIcon.getImage();
		Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(image.getScaledInstance(60, 60, Image.SCALE_SMOOTH));

		// Thêm hình ảnh vào label
		JLabel label = new JLabel();
		label.setIcon(scaledImageIcon);
		panel.add(label);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setAlignmentX(Box.LEFT_ALIGNMENT);

		// Tạo các button
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		btnDashboard.setBackground(Color.LIGHT_GRAY);

		JButton btnClasses = new JButton("Classes");
		btnClasses.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		btnClasses.setBackground(Color.WHITE);

		JButton btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    }
		});
		btnStudent.setBackground(new Color(192, 192, 192));

		JButton btnBlog = new JButton("Blog");
		btnBlog.setBackground(new Color(192, 192, 192));

		// Đặt độ rộng cho các button
		int buttonWidth = verticalBox.getWidth();
		Dimension buttonDimension = new Dimension(100, 20);

		btnDashboard.setMaximumSize(buttonDimension);
		btnClasses.setMaximumSize(buttonDimension);
		btnStudent.setMaximumSize(buttonDimension);
		btnBlog.setMaximumSize(buttonDimension);

		// Thêm các button vào VerticalBox
		verticalBox.add(btnDashboard);
		verticalBox.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các button
		verticalBox.add(btnClasses);
		verticalBox.add(Box.createVerticalStrut(10));
		verticalBox.add(btnStudent);
		verticalBox.add(Box.createVerticalStrut(10));
		verticalBox.add(btnBlog);

		panel.add(verticalBox);
		verticalBox.add(Box.createVerticalStrut(30));
		
		JButton btn_Classes_1 = new JButton("<- Logout");
		btn_Classes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController loginController = new LoginController(new LoginView());
                loginController.displayLoginView();
                dispose();
			}
		});
		btn_Classes_1.setMaximumSize(new Dimension(100, 20));
		btn_Classes_1.setBackground(Color.WHITE);
		panel.add(btn_Classes_1);

		
		JLabel lb_FindMaLop = new JLabel("Class Code");
		lb_FindMaLop.setHorizontalAlignment(SwingConstants.CENTER);
		lb_FindMaLop.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lb_FindMaLop.setBounds(124, 29, 140, 48);
		classesPane.add(lb_FindMaLop);
		
		textField_FindMaLop = new JTextField();
		textField_FindMaLop.setHorizontalAlignment(SwingConstants.CENTER);
		textField_FindMaLop.setFont(new Font("Tahoma", Font.PLAIN, 23));
		textField_FindMaLop.setBounds(274, 29, 228, 48);
		classesPane.add(textField_FindMaLop);
		textField_FindMaLop.setColumns(10);
		
		 btn_Tim = new JButton("Find");
		 btn_Tim.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	}
		 });
		btn_Tim.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn_Tim.setBounds(526, 29, 97, 48);
		classesPane.add(btn_Tim);
		
		JLabel lblDanhSchCc = new JLabel("List of classes");
		lblDanhSchCc.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanhSchCc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDanhSchCc.setBounds(124, 110, 177, 41);
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
	                        "Mã Lớp", "Tên Lớp", "Số học sinh hiện tại", "Số học sinh tối đa"
	                }
	        ) {
	            // Ghi đè phương thức isCellEditable để không cho phép chỉnh sửa
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false;
	            }
	        };
	        
	        // Tạo JTable với DefaultTableModel tùy chỉnh
	        table = new JTable(model);
	        
	        // Thiết lập font cho bảng
	        table.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	if (e.getClickCount() == 2) {
                	System.out.println("aaaaaa");
                    int row = table.getSelectedRow();
                    DatabaseConnection a = new DatabaseConnection();
			    	 ClassesManager classes = new ClassesManager();
			    	 classes=a.retrieveClassesFromDatabase();
			    	 Classroom classRoom=classes.getClassroomList().get(row);
			    	 StudentView stu=new StudentView(classRoom);
			    	 
                    	                   
                }
		    }
		});
		//acessToListOfStudents();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(134, 152, 635, 218);
		classesPane.add(scrollPane);
		
		JLabel lb_Malop = new JLabel("Class Code");
		lb_Malop.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Malop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_Malop.setBounds(103, 409, 97, 41);
		classesPane.add(lb_Malop);
		
		textField_MaLop = new JTextField();
		textField_MaLop.setBounds(220, 413, 123, 41);
		classesPane.add(textField_MaLop);
		textField_MaLop.setColumns(10);
		
		JLabel lb_TenLop = new JLabel("Class name");
		lb_TenLop.setHorizontalAlignment(SwingConstants.CENTER);
		lb_TenLop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_TenLop.setBounds(103, 466, 118, 41);
		classesPane.add(lb_TenLop);
		
		textField_TenLop = new JTextField();
		textField_TenLop.setColumns(10);
		textField_TenLop.setBounds(220, 465, 123, 41);
		classesPane.add(textField_TenLop);
		
		JLabel lb_SoHSHT = new JLabel("Number of current student");
		lb_SoHSHT.setHorizontalAlignment(SwingConstants.CENTER);
		lb_SoHSHT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_SoHSHT.setBounds(358, 413, 244, 41);
		classesPane.add(lb_SoHSHT);
		
		textField_SoHSHT = new JTextField();
		textField_SoHSHT.setColumns(10);
		textField_SoHSHT.setBounds(602, 413, 164, 41);
		classesPane.add(textField_SoHSHT);
		
		JLabel lb_SoHSTD = new JLabel("Number of max student");
		lb_SoHSTD.setHorizontalAlignment(SwingConstants.CENTER);
		lb_SoHSTD.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_SoHSTD.setBounds(368, 466, 221, 41);
		classesPane.add(lb_SoHSTD);
		
		textField_SoHSTD = new JTextField();
		textField_SoHSTD.setColumns(10);
		textField_SoHSTD.setBounds(602, 470, 164, 41);
		classesPane.add(textField_SoHSTD);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(134, 97, 601, 2);
		classesPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(134, 394, 601, 2);
		classesPane.add(separator_1);
		
		 btn_Them = new JButton("Add");
	
		
		btn_Them.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Them.setBounds(124, 539, 97, 33);
		classesPane.add(btn_Them);
		
		 btn_Xoa = new JButton("Delete");
		
		btn_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Xoa.setBounds(242, 539, 97, 33);
		classesPane.add(btn_Xoa);
		
		 btn_CapNhat = new JButton("Update");
	
		btn_CapNhat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_CapNhat.setBounds(358, 539, 148, 33);
		classesPane.add(btn_CapNhat);
		
		 btn_Luu = new JButton("Save");
	
		btn_Luu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Luu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Luu.setBounds(516, 539, 97, 33);
		classesPane.add(btn_Luu);
		
		 btn_Huy = new JButton("Cancel");
		
		btn_Huy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_Huy.setBounds(638, 539, 110, 33);
		classesPane.add(btn_Huy);
		
		 btnHuyTim = new JButton("Undo");
		btnHuyTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHuyTim.setBounds(633, 29, 110, 48);
		classesPane.add(btnHuyTim);
		
		System.out.println("Aaaaaaaaa");
		ClassesController control= new ClassesController(this);
		setVisible(true);
		
	}
	
	public void displayClassList(ClassesManager classes) {		
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
		textField_MaLop.setText("");
		textField_SoHSHT.setText("");
		textField_SoHSTD.setText("");
		textField_TenLop.setText("");
	}
	public void hideUI() {
	    setVisible(false);
	}
	public String getClassName() {
	    return textField_TenLop.getText();
	}

	public int getNumOfCurrentStudents() {
	    return Integer.parseInt(textField_SoHSHT.getText());
	}

	public int getMaximumNumOfStudents() {
	    return Integer.parseInt(textField_SoHSTD.getText());
	}

	public void setClassInfo(Classroom classroom) {
		textField_MaLop.setText(classroom.getClassCode());
	    textField_TenLop.setText(classroom.getClassName());
	    textField_SoHSHT.setText(String.valueOf(classroom.getNumberOfStudents()));
	    textField_SoHSTD.setText(String.valueOf(classroom.getMaximumNumOfStudents()));
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
	public Classroom getNewClass() {
		String classCode = this.textField_MaLop.getText();
        String className = this.textField_TenLop.getText();
        int numOfCurentStudents = Integer.valueOf(this.textField_SoHSHT.getText());
        int maximumNumOfStudents =Integer.valueOf(this.textField_SoHSTD.getText());;
        
        Classroom lop= new Classroom(classCode, className, numOfCurentStudents, maximumNumOfStudents);
        return lop;
	}
	public void addClassListener(ActionListener listener) {
		if(btn_Them==null)return;
		btn_Them.addActionListener(listener);
    }
	public void deleteClassListener(ActionListener listener) {
		if(btn_Xoa==null)return;
		btn_Xoa.addActionListener(listener);
    }
	
	public int getIndexofClassToDelete() {
			 int index=table.getSelectedRow();;
			 return index;
	}
	public void updateClassListener(ActionListener listener) {
		  btn_CapNhat.addActionListener(listener);
	 }
	public void saveClassListener(ActionListener listener) {
		btn_Luu.addActionListener(listener);
	 }
	public void CancelButtonListener(ActionListener listener) {
	    btn_Huy.addActionListener(listener);
	}
	public void acessToListOfStudents() {
		 table.addMouseListener(new MouseAdapter() {
			
	            public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount() == 2) {
	                	System.out.println("aaaaaa");
	                    int row = table.getSelectedRow();
	                    DatabaseConnection a = new DatabaseConnection();
				    	 ClassesManager classes = new ClassesManager();
				    	 classes=a.retrieveClassesFromDatabase();
				    	 Classroom classRoom=classes.getClassroomList().get(row);
				    	 StudentView stu=new StudentView(classRoom);
				    	 
	                    	                   
	                }
	            }
	        });
	}



}