
package view.Teacher;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseConnection;
import controller.Admin.LoginController;
import controller.Teacher.StudentController;
import model.Classroom;
import model.Student;
import model.ClassesManager;
import view.Admin.LoginView;
public class StudentView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Student selectedStu;
	public JTextField searchInp;
	public JTextField textField_ID;
	public JTextField textField_name;
	public JTextField textField_dob_day;
	public JTextField textField_phone;
	public JTextField textField_completed;
	public JTextField textField_owed;
	public JTable table;
	public ButtonGroup bt;
	private JButton btnTim;
	private JButton btnHuyTim;
	public JComboBox comboBox_queQuan;
	static Classroom classRoom;
	
	private JTextField textField_Add;
	JRadioButton rdbtnFemale;
	JRadioButton rdbtnMale;
	 JButton btnThem;
	 JButton btnXoa;
	 JButton btnCapNhat;
	 JButton btnLuu ;
	 JButton btnHuyBo;
	 public boolean isUpdating=false;
	 private JTextField textField_dob_month;
	 private JTextField textField_dob_year;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					DatabaseConnection a = new DatabaseConnection();
			    	 ClassesManager classes = new ClassesManager();
			    	 classes=a.retrieveClassesFromDatabase();
			    	 classRoom=classes.getClassroomList().get(0);
					StudentView frame = new StudentView(classRoom);
					frame.setVisible(true);
					
					
			    	 //-------------------------------------------------------------------------------------------------
			    	 
			    	 StudentController stu=new StudentController(frame,classRoom);
			    	 			     					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public StudentView(final Classroom classRoom) {
		this.classRoom=classRoom;		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 903, 718);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ActionListener action=new StudentController(this);
		ImageIcon imageIcon = new ImageIcon();
		 URL imageUrl=MainView.class.getClassLoader().getResource("Assert/DTULogo.png");
	            File file = new File(imageUrl.getPath());
	              imageIcon = new ImageIcon(file.getAbsolutePath());
	              System.out.println(file.getAbsolutePath());
	            


	        // Lấy hình ảnh từ ImageIcon
	        Image image = imageIcon.getImage();
	        Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);		
	        int imageWidth = image.getWidth(null);
	        int imageHeight = image.getHeight(null);

	        
	        ImageIcon scaledImageIcon = new ImageIcon(
	         image.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
			        System.out.println(imageWidth+" "+ imageHeight);
			        Panel panel = new Panel();
			        panel.setBackground(new Color(192, 192, 192));
			        panel.setBounds(0, 0, 116, 679);
			        contentPane.add(panel);
			        JLabel label = new JLabel();
			        panel.add(label);
			        label.setIcon(scaledImageIcon);
			        
			        Box verticalBox = Box.createVerticalBox();

			        verticalBox.setAlignmentX(Box.LEFT_ALIGNMENT);
			        JButton button_3 = new JButton("Blog");
			        button_3.setBackground(new Color(192, 192, 192));

			        // Đặt độ rộng cho các button
			        int buttonWidth = verticalBox.getWidth();
			        Dimension buttonDimension = new Dimension(100, 20);
			        button_3.setMaximumSize(buttonDimension);
			        
			        JButton button_1 = new JButton("Dashboard");
			        button_1.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent e) {
			        	}
			        });
			        button_1.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			                // Tạo một instance của MainView
			                MainView mainView = new MainView();

			                // Hiển thị MainView	
			                mainView.setVisible(true);

			                // Đóng StudentView (nếu bạn muốn đóng StudentView sau khi chuyển đến MainView)
			                dispose();
			            }
			        });
			        button_1.setMaximumSize(new Dimension(100, 20));
			        button_1.setBackground(Color.LIGHT_GRAY);
			        verticalBox.add(button_1);
			        verticalBox.add(Box.createVerticalStrut(10));
			        
			        JButton button_2_1 = new JButton("Classes");
			        button_2_1.setMaximumSize(new Dimension(100, 20));
			        button_2_1.setBackground(Color.LIGHT_GRAY);
			        verticalBox.add(button_2_1);
			        verticalBox.add(Box.createVerticalStrut(10));
			        
			        JButton btnStudent = new JButton("Student");
			        btnStudent.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent e) {
			        	}
			        });
			        btnStudent.setMaximumSize(new Dimension(100, 20));
			        btnStudent.setBorder(null);
			        btnStudent.setBackground(Color.WHITE);
			        verticalBox.add(btnStudent);
			        verticalBox.add(Box.createVerticalStrut(10));
			        verticalBox.add(button_3);
			        
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
			        
			        JPanel contentPane_1 = new JPanel();
			        contentPane_1.setLayout(null);
			        contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
			        contentPane_1.setBounds(119, 0, 760, 679);
			        contentPane.add(contentPane_1);
			        
			        Box verticalBox_1 = Box.createVerticalBox();
			        verticalBox_1.setBounds(44, 144, -28, -35);
			        contentPane_1.add(verticalBox_1);
			        
			        JLabel label_Student_ID = new JLabel("Student ID");
			        label_Student_ID.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        label_Student_ID.setBounds(15, 11, 155, 54);
			        contentPane_1.add(label_Student_ID);
			        
			        searchInp = new JTextField();
			        searchInp.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        searchInp.setColumns(10);
			        searchInp.setBounds(127, 12, 272, 54);
			        contentPane_1.add(searchInp);
			        
			         btnTim = new JButton("Search");
			        btnTim.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent e) {
			        	}
			        });
			        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnTim.setBounds(421, 12, 117, 54);
			        contentPane_1.add(btnTim);
			        
			        JSeparator separator_1 = new JSeparator();
			        separator_1.setBounds(10, 92, 738, 2);
			        contentPane_1.add(separator_1);
			        
			        JLabel lblListOfStudents = new JLabel("List of students");
			        lblListOfStudents.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblListOfStudents.setBounds(10, 97, 251, 54);
			        contentPane_1.add(lblListOfStudents);
			        
			        JScrollPane scrollPane = new JScrollPane((Component) null);
			        scrollPane.setBounds(10, 144, 738, 214);
			        contentPane_1.add(scrollPane);
			        
//			        table = new JTable();
//			        table.setModel(new DefaultTableModel(
//			        	new Object[][] {
//			        		{null, null, null, null, null, null, null, null},
//			        		{null, null, null, null, null, null, null, null},
//			        		{null, null, null, null, null, null, null, null},
//			        		{null, null, null, null, null, null, null, null},
//			        		{null, null, null, null, null, null, null, null},
//			        		{null, null, null, null, null, null, null, null},
//			        		{null, null, null, null, null, null, null, null},
//			        		{null, null, null, null, null, null, null, null},
//			        	},
//			        	new String[] {
//			        			"Student ID", "Name", "Day of Birth", "Address", "Gender", "Phone number", "Credits Completed", "Credits Owed"
//			        	}
//			        ));
			        table = new JTable() {
			            @Override
			            public boolean isCellEditable(int row, int column) {
			                return false; // Không cho phép chỉnh sửa
			            }
			        };

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
			        table.addMouseListener(new MouseAdapter() {
					    @Override
					    public void mouseClicked(MouseEvent e) {
					    	if (e.getClickCount() == 2) {
			                	
			                    int row = table.getSelectedRow();
			                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
						    	 
						    	 DetalInformationofStudentView stu=new DetalInformationofStudentView(classRoom.getStudentList().get(row));
						    	 stu.setVisible(true);
						    	 System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
			                    	                   
			                }
					    }
					});
			        scrollPane.setViewportView(table);
			        
			        JSeparator separator_1_1 = new JSeparator();
			        separator_1_1.setBounds(10, 369, 738, 2);
			        contentPane_1.add(separator_1_1);
			        
			        JLabel lblStudentInformation = new JLabel("Student information");
			        lblStudentInformation.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblStudentInformation.setBounds(10, 369, 233, 54);
			        contentPane_1.add(lblStudentInformation);
			        
			        JLabel lblStudentId = new JLabel("Student ID");
			        lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblStudentId.setBounds(10, 412, 102, 54);
			        contentPane_1.add(lblStudentId);
			        
			        textField_ID = new JTextField();
			        textField_ID.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_ID.setColumns(10);
			        textField_ID.setBounds(127, 425, 166, 29);
			        contentPane_1.add(textField_ID);
			        
			        JLabel lblName = new JLabel("Name");
			        lblName.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblName.setBounds(10, 457, 102, 54);
			        contentPane_1.add(lblName);
			        
			        textField_name = new JTextField();
			        textField_name.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_name.setColumns(10);
			        textField_name.setBounds(127, 470, 166, 29);
			        contentPane_1.add(textField_name);
			        
			        JLabel lblAddress = new JLabel("Address");
			        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblAddress.setBounds(10, 510, 102, 54);
			        contentPane_1.add(lblAddress);
			        
			        JLabel lblDayOfBirth = new JLabel("Day Of Birth");
			        lblDayOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblDayOfBirth.setBounds(10, 563, 155, 54);
			        contentPane_1.add(lblDayOfBirth);
			        
			        textField_dob_day = new JTextField();
			        textField_dob_day.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_dob_day.setColumns(10);
			        textField_dob_day.setBounds(127, 576, 43, 29);
			        contentPane_1.add(textField_dob_day);
			        
			        JLabel lblGender = new JLabel("Gender");
			        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblGender.setBounds(324, 412, 102, 54);
			        contentPane_1.add(lblGender);
			        
			         rdbtnMale = new JRadioButton("Male");
			        rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        rdbtnMale.setBounds(405, 429, 61, 23);
			        contentPane_1.add(rdbtnMale);
			        
			         rdbtnFemale = new JRadioButton("Female");
			        rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        rdbtnFemale.setBounds(489, 429, 102, 23);
			        contentPane_1.add(rdbtnFemale);
			        
			        bt=new ButtonGroup();
			        bt.add(rdbtnMale);
			        bt.add(rdbtnFemale); 
			        
			        JLabel lblPhoneNumber = new JLabel("Phone Number");
			        lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblPhoneNumber.setBounds(324, 473, 140, 23);
			        contentPane_1.add(lblPhoneNumber);
			        
			        textField_phone = new JTextField();
			        textField_phone.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_phone.setColumns(10);
			        textField_phone.setBounds(526, 470, 166, 29);
			        contentPane_1.add(textField_phone);
			        
			        JLabel lblCreditsComple = new JLabel("Credits Completed");
			        lblCreditsComple.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblCreditsComple.setBounds(324, 513, 160, 23);
			        contentPane_1.add(lblCreditsComple);
			        
			        textField_completed = new JTextField();
			        textField_completed.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_completed.setColumns(10);
			        textField_completed.setBounds(526, 510, 166, 29);
			        contentPane_1.add(textField_completed);
			        
			        JLabel lblCreditsOwed = new JLabel("Credits Owed");
			        lblCreditsOwed.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        lblCreditsOwed.setBounds(324, 557, 160, 23);
			        contentPane_1.add(lblCreditsOwed);
			        
			        textField_owed = new JTextField();
			        textField_owed.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_owed.setColumns(10);
			        textField_owed.setBounds(526, 554, 166, 29);
			        contentPane_1.add(textField_owed);
			        
//			        JButton btnThem = new JButton("Add");
//			        btnThem.addActionListener(new ActionListener() {
//			        	public void actionPerformed(ActionEvent e) {
//			        	}
//			        });
//			        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
//			        btnThem.setBounds(31, 628, 89, 42);
//			        contentPane_1.add(btnThem);

			         btnThem = new JButton("Add");
			      
			      
			        btnThem.addActionListener(action);
			        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnThem.setBounds(52, 628, 89, 42);
			        contentPane_1.add(btnThem);
			        
			        btnXoa = new JButton("Delete");
			        btnXoa.addActionListener(action);
			        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnXoa.setBounds(151, 628, 89, 42);
			        contentPane_1.add(btnXoa);
			        
			         btnCapNhat = new JButton("Update");
			        btnCapNhat.addActionListener(action);
			        btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnCapNhat.setBounds(264, 628, 135, 42);
			        contentPane_1.add(btnCapNhat);
			        
			         btnLuu = new JButton("Save");
			       // btnLuu.addActionListener(action);
			        btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnLuu.setBounds(421, 628, 135, 42);
			        contentPane_1.add(btnLuu);
			        
			        btnHuyBo = new JButton("Cancel");
			        btnHuyBo.addActionListener(action);
			        btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnHuyBo.setBounds(585, 628, 135, 42);
			        contentPane_1.add(btnHuyBo);
			        
			        JSeparator separator_2 = new JSeparator();
			        separator_2.setForeground(Color.BLACK);
			        separator_2.setBounds(15, 628, 733, -22);
			        contentPane_1.add(separator_2);
			        
			        JSeparator separator_1_1_1 = new JSeparator();
			        separator_1_1_1.setBounds(10, 616, 738, 2);
			        contentPane_1.add(separator_1_1_1);
			        
			         btnHuyTim = new JButton("Undo");
			        btnHuyTim.addActionListener(new ActionListener() {
			        	public void actionPerformed(ActionEvent e) {
			        	}
			        });
			        btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
			        btnHuyTim.setBounds(585, 12, 117, 54);
			        contentPane_1.add(btnHuyTim);
			        
			        textField_Add = new JTextField();
			        textField_Add.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_Add.setColumns(10);
			        textField_Add.setBounds(127, 523, 166, 29);
			        contentPane_1.add(textField_Add);
			        
			        textField_dob_month = new JTextField();
			        textField_dob_month.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_dob_month.setColumns(10);
			        textField_dob_month.setBounds(175, 576, 43, 29);
			        contentPane_1.add(textField_dob_month);
			        
			        textField_dob_year = new JTextField();
			        textField_dob_year.setFont(new Font("Tahoma", Font.PLAIN, 19));
			        textField_dob_year.setColumns(10);
			        textField_dob_year.setBounds(228, 576, 43, 29);
			        contentPane_1.add(textField_dob_year);
			      
			        StudentController stu=new StudentController(this,classRoom);
			        setVisible(true);
	}

    public int getSearchInp() {
        String text = searchInp.getText();
        if (text == null || text.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sinh viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            
            return 0;
        }
        return Integer.parseInt(text);
    }
    public void notFindStudent(int id) {
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
	 
	 public Student getInfoOfNewStudent() {
		 if(!test()) 
			 return null;
		 Student st;
		   int studentID; String name; LocalDate dob; String address; boolean gender=false;
			 String phoneNumber; int creditsCompleted; int  creditsOwed;
			 studentID=Integer.parseInt(textField_ID.getText());
			 name=textField_name.getText();
			 address=textField_Add.getText();
			int day=Integer.parseInt(textField_dob_day.getText());
			int month=Integer.parseInt(textField_dob_month.getText());
			int year=Integer.parseInt(textField_dob_year.getText());
			
			dob = LocalDate.of(year, month, day);
			 if (rdbtnFemale.isSelected()) {
				 gender=false;
				 
			 }else if(rdbtnMale.isSelected()) {
				 gender=true;
			 }
			 phoneNumber=textField_phone.getText();
			 creditsCompleted=Integer.parseInt(textField_phone.getText());
			 creditsOwed=Integer.parseInt(textField_owed.getText());
			 st=new Student( studentID, name, dob, address, gender, phoneNumber, creditsCompleted, creditsOwed); 
		 return st; 
	 }
	 public Student getInfoOfExitsStudent() {
	
		 Student st;
		   int studentID; String name; LocalDate dob; String address; boolean gender=false;
			 String phoneNumber; int creditsCompleted; int  creditsOwed;
			 studentID=Integer.parseInt(textField_ID.getText());
			 name=textField_name.getText();
			 address=textField_Add.getText();
			int day=Integer.parseInt(textField_dob_day.getText());
			int month=Integer.parseInt(textField_dob_month.getText());
			int year=Integer.parseInt(textField_dob_year.getText());
			
			dob = LocalDate.of(year, month, day);
			 if (rdbtnFemale.isSelected()) {
				 gender=false;
				 
			 }else if(rdbtnMale.isSelected()) {
				 gender=true;
			 }
			 phoneNumber=textField_phone.getText();
			 creditsCompleted=Integer.parseInt(textField_completed.getText());
			 creditsOwed=Integer.parseInt(textField_owed.getText());
			 st=new Student( studentID, name, dob, address, gender, phoneNumber, creditsCompleted, creditsOwed); 
		 return st; 
	 }
	 public boolean test() {
		    try {
		        int studentID = Integer.parseInt(textField_ID.getText());
		        String name = textField_name.getText();
		        String address = textField_Add.getText();
		        int day = Integer.parseInt(textField_dob_day.getText());
		        int month = Integer.parseInt(textField_dob_month.getText());
		        int year = Integer.parseInt(textField_dob_year.getText());
		        LocalDate dob = LocalDate.of(year, month, day);
		        boolean gender = false;
		        if (rdbtnFemale.isSelected()) {
		            gender = false;
		        } else if (rdbtnMale.isSelected()) {
		            gender = true;
		        }
		        String phoneNumber = textField_phone.getText();
		        int creditsCompleted=Integer.parseInt(textField_phone.getText());
				int creditsOwed=Integer.parseInt(textField_owed.getText());

				if (studentID<=0) {
			        JOptionPane.showMessageDialog(null, "Nhap sai ma sinh vien", "Loi", JOptionPane.ERROR_MESSAGE);
			        return false;
			    }
				else if (classRoom.checkAStudent(studentID)) {
			        JOptionPane.showMessageDialog(null, "Ma sinh vien da ton tai", "Loi", JOptionPane.ERROR_MESSAGE);
			        return false;
			    }
				else if (name.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Nhap sai ten sinh vien", "Loi", JOptionPane.ERROR_MESSAGE);
			        return false;
			    } else if (address.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Nhap sai dia chi sinh vien", "Loi", JOptionPane.ERROR_MESSAGE);
			        return false;
			    } else if (day <= 0 || month <= 0 || year <= 0) {
			        JOptionPane.showMessageDialog(null, "Nhap sai ngay sinh cua sinh vien", "Loi", JOptionPane.ERROR_MESSAGE);
			        return false;
			    } else if (phoneNumber.isEmpty()) {
			        JOptionPane.showMessageDialog(null, "Nhap sai so dien thoai cua sinh vien", "Loi", JOptionPane.ERROR_MESSAGE);
			        return false;
			    } else if (creditsCompleted < 0) {
			        JOptionPane.showMessageDialog(null, "Nhap sai Credits Completed", "Loi", JOptionPane.ERROR_MESSAGE);
			        return false;
			    } else if (creditsOwed < 0) {
			        JOptionPane.showMessageDialog(null, "Nhap sai Credits Owed", "Loi", JOptionPane.ERROR_MESSAGE);
			        return false;
			    }
		        return true;
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, "Nhap sai ", "Loi", JOptionPane.ERROR_MESSAGE);
		    } catch (IllegalArgumentException e) {
		        JOptionPane.showMessageDialog(null, e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
		        JOptionPane.showMessageDialog(null, e.getMessage(), "Loi", JOptionPane.ERROR_MESSAGE);
		    }

		    return false;
		}
	 

	 public int getIndexofSelectedRow() {
		
		 int index=table.getSelectedRow(); selectedStu=classRoom.getStudentList().get(index);
		 return index;
	 }
	 public void addStudentListener(ActionListener listener) {
		 btnThem.addActionListener(listener);
     }
	 public void deleteStudentListener(ActionListener listener) {
		 btnXoa.addActionListener(listener);
	 }
	 public void updateStudentListener(ActionListener listener) {
		  btnCapNhat.addActionListener(listener);
	 }
	 public void saveStudentListener(ActionListener listener) {
		  btnLuu.addActionListener(listener);
	 }
	 public void cancelStudentListener(ActionListener listener) {
		  btnHuyBo.addActionListener(listener);
	 }
	 public void setInfoOfNewStudent() {
	
		 
		 Student student=classRoom.getStudentList().get(getIndexofSelectedRow());
		
		    if ( getIndexofSelectedRow()==-1||student==null) {
		        return;
		    }
		    isUpdating=true;
		    textField_ID.setText(""+student.getStudentID());
		    textField_name.setText(student.getName());
		    textField_Add.setText(student.getAddress());
		    System.out.println("aaaaaaaaas");
		    textField_dob_day.setText(String.valueOf(student.getDob().getDayOfMonth()));
		    textField_dob_month.setText(String.valueOf(student.getDob().getMonthValue()));
		    textField_dob_year.setText(String.valueOf(student.getDob().getYear()));	    
		    if (student.isGender()) {
		        rdbtnMale.setSelected(true);
		    } else {
		        rdbtnFemale.setSelected(true);
		    }
		    textField_phone.setText(student.getPhoneNumber());
		    textField_completed.setText(""+student.getCreditsCompleted());
		    textField_owed.setText(""+(student.getCreditsOwed()));
		    
		}
	public void xoaForm() {
		searchInp.setText("");
		textField_ID.setText("");
		textField_name.setText("");
		textField_dob_day.setText("");
		textField_dob_month.setText("");
		textField_dob_year.setText("");
		rdbtnFemale.setSelected(false);
		rdbtnMale.setSelected(false);
		textField_phone.setText("");
		textField_completed.setText("");
		textField_owed.setText("");
		textField_Add.setText("");		
		table.clearSelection();
		bt.clearSelection();
	}
}
