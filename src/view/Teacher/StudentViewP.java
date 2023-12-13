package view.Teacher;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseConnection;
import controller.Teacher.StudentController;
import model.Classroom;
import model.Student;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

public class StudentViewP extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel contentPane_1;
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
	 * Create the panel.
	 */
	public StudentViewP(final Classroom classRoom) {
		
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
	        label_Student_ID.setBounds(15, 11, 135, 42);
	        contentPane_1.add(label_Student_ID);
	        
	        searchInp = new JTextField();
	        searchInp.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        searchInp.setColumns(10);
	        searchInp.setBounds(127, 12, 272, 35);
	        contentPane_1.add(searchInp);
	        
	         btnTim = new JButton("Search");
	        btnTim.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnTim.setBounds(421, 12, 117, 41);
	        contentPane_1.add(btnTim);
	        
	        JSeparator separator_1 = new JSeparator();
	        separator_1.setBounds(10, 63, 738, 2);
	        contentPane_1.add(separator_1);
	        
	        JLabel lblListOfStudents = new JLabel("List of students");
	        lblListOfStudents.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblListOfStudents.setBounds(10, 63, 251, 54);
	        contentPane_1.add(lblListOfStudents);
	        
	        JScrollPane scrollPane = new JScrollPane((Component) null);
	        scrollPane.setBounds(10, 105, 738, 214);
	        contentPane_1.add(scrollPane);
	        

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
	                   
				    	 
				    	 DetalInformationofStudentView stu=new DetalInformationofStudentView(classRoom.getStudentList().get(row));				    				    	
				    
				    	stu.requestFocus();
				        stu.setVisible(true);
				        stu.setEnabled(true);
				    	    	
				          			          
	                    	                   
	                }
			    }
			   
			});
	        scrollPane.setViewportView(table);
	        
	        JSeparator separator_1_1 = new JSeparator();
	        separator_1_1.setBounds(10, 329, 738, 2);
	        contentPane_1.add(separator_1_1);
	        
	        JLabel lblStudentInformation = new JLabel("Student information");
	        lblStudentInformation.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblStudentInformation.setBounds(10, 329, 233, 54);
	        contentPane_1.add(lblStudentInformation);
	        
	        JLabel lblStudentId = new JLabel("Student ID");
	        lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblStudentId.setBounds(10, 369, 102, 54);
	        contentPane_1.add(lblStudentId);
	        
	        textField_ID = new JTextField();
	        textField_ID.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        textField_ID.setColumns(10);
	        textField_ID.setBounds(122, 382, 166, 29);
	        contentPane_1.add(textField_ID);
	        
	        JLabel lblName = new JLabel("Name");
	        lblName.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblName.setBounds(10, 412, 102, 54);
	        contentPane_1.add(lblName);
	        
	        textField_name = new JTextField();
	        textField_name.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        textField_name.setColumns(10);
	        textField_name.setBounds(122, 425, 166, 29);
	        contentPane_1.add(textField_name);
	        
	        JLabel lblAddress = new JLabel("Address");
	        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblAddress.setBounds(10, 457, 102, 54);
	        contentPane_1.add(lblAddress);
	        
	        JLabel lblDayOfBirth = new JLabel("Day Of Birth");
	        lblDayOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblDayOfBirth.setBounds(10, 499, 155, 54);
	        contentPane_1.add(lblDayOfBirth);
	        
	        textField_dob_day = new JTextField();
	        textField_dob_day.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        textField_dob_day.setColumns(10);
	        textField_dob_day.setBounds(127, 512, 43, 29);
	        contentPane_1.add(textField_dob_day);
	        
	        JLabel lblGender = new JLabel("Gender");
	        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblGender.setBounds(331, 369, 102, 54);
	        contentPane_1.add(lblGender);
	        
	         rdbtnMale = new JRadioButton("Male");
	        rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        rdbtnMale.setBounds(412, 386, 61, 23);
	        contentPane_1.add(rdbtnMale);
	        
	         rdbtnFemale = new JRadioButton("Female");
	        rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        rdbtnFemale.setBounds(496, 386, 102, 23);
	        contentPane_1.add(rdbtnFemale);
	        
	        bt=new ButtonGroup();
	        bt.add(rdbtnMale);
	        bt.add(rdbtnFemale); 
	        
	        JLabel lblPhoneNumber = new JLabel("Phone Number");
	        lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblPhoneNumber.setBounds(331, 430, 140, 23);
	        contentPane_1.add(lblPhoneNumber);
	        
	        textField_phone = new JTextField();
	        textField_phone.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        textField_phone.setColumns(10);
	        textField_phone.setBounds(533, 427, 166, 29);
	        contentPane_1.add(textField_phone);
	        
	        JLabel lblCreditsComple = new JLabel("Credits Completed");
	        lblCreditsComple.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblCreditsComple.setBounds(331, 470, 160, 23);
	        contentPane_1.add(lblCreditsComple);
	        
	        textField_completed = new JTextField();
	        textField_completed.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        textField_completed.setColumns(10);
	        textField_completed.setBounds(533, 467, 166, 29);
	        contentPane_1.add(textField_completed);
	        
	        JLabel lblCreditsOwed = new JLabel("Credits Owed");
	        lblCreditsOwed.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        lblCreditsOwed.setBounds(331, 514, 160, 23);
	        contentPane_1.add(lblCreditsOwed);
	        
	        textField_owed = new JTextField();
	        textField_owed.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        textField_owed.setColumns(10);
	        textField_owed.setBounds(533, 511, 166, 29);
	        contentPane_1.add(textField_owed);
	        

	         btnThem = new JButton("Add");
	      
	      
	        btnThem.addActionListener(action);
	        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnThem.setBounds(44, 551, 89, 33);
	        contentPane_1.add(btnThem);
	        
	        btnXoa = new JButton("Delete");
	        btnXoa.addActionListener(action);
	        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnXoa.setBounds(154, 551, 89, 33);
	        contentPane_1.add(btnXoa);
	        
	         btnCapNhat = new JButton("Update");
	        btnCapNhat.addActionListener(action);
	        btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnCapNhat.setBounds(264, 551, 135, 33);
	        contentPane_1.add(btnCapNhat);
	        
	         btnLuu = new JButton("Save");
	       // btnLuu.addActionListener(action);
	        btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnLuu.setBounds(420, 551, 135, 33);
	        contentPane_1.add(btnLuu);
	        
	        btnHuyBo = new JButton("Cancel");
	        btnHuyBo.addActionListener(action);
	        btnHuyBo.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnHuyBo.setBounds(585, 551, 135, 33);
	        contentPane_1.add(btnHuyBo);
	        
	        JSeparator separator_2 = new JSeparator();
	        separator_2.setForeground(Color.BLACK);
	        separator_2.setBounds(15, 628, 733, -22);
	        contentPane_1.add(separator_2);
	        
	        JSeparator separator_1_1_1 = new JSeparator();
	        separator_1_1_1.setBounds(15, 603, 738, 2);
	        contentPane_1.add(separator_1_1_1);
	        
	         btnHuyTim = new JButton("Undo");
	        btnHuyTim.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        btnHuyTim.setBounds(585, 12, 117, 41);
	        contentPane_1.add(btnHuyTim);
	        
	        textField_Add = new JTextField();
	        textField_Add.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        textField_Add.setColumns(10);
	        textField_Add.setBounds(122, 470, 166, 29);
	        contentPane_1.add(textField_Add);
	        
	        textField_dob_month = new JTextField();
	        textField_dob_month.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        textField_dob_month.setColumns(10);
	        textField_dob_month.setBounds(175, 512, 43, 29);
	        contentPane_1.add(textField_dob_month);
	        
	        textField_dob_year = new JTextField();
	        textField_dob_year.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        textField_dob_year.setColumns(10);
	        textField_dob_year.setBounds(228, 512, 70, 29);
	        contentPane_1.add(textField_dob_year);
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
		 
		 public Student getInfoOfNewStudent() {
			 if(!test(true)) 
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
				 creditsCompleted=Integer.parseInt(textField_completed.getText());
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
		 public boolean isLeapYear(int year) {
			    // Kiểm tra xem năm có phải là năm nhuận không
			    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
			}
		 public boolean test(boolean isAddNew) {
			    try {
			        int studentID = Integer.parseInt(textField_ID.getText());
			        String name = textField_name.getText();
			        String address = textField_Add.getText();
			        int day = Integer.parseInt(textField_dob_day.getText());
			        int month = Integer.parseInt(textField_dob_month.getText());
			        int year = Integer.parseInt(textField_dob_year.getText());
			        boolean gender = false;

			        if (rdbtnFemale.isSelected()) {
			            gender = false;
			        } else if (rdbtnMale.isSelected()) {
			            gender = true;
			        }
			        String phoneNumber = textField_phone.getText();
			        int creditsCompleted = Integer.parseInt(textField_completed.getText());
			        int creditsOwed = Integer.parseInt(textField_owed.getText());

			        if(isAddNew) {
			      
			       


			        if (studentID <=0) {

			            JOptionPane.showMessageDialog(null, "Invalid student ID", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        } else if (classRoom.checkAStudent(studentID)) {
			            JOptionPane.showMessageDialog(null, "Student ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        } // Kiểm tra xem tên có ít nhất 2 từ hay không
			        }
			        String[] nameWords = name.split("\\s+");
			         if (nameWords.length < 2) {
			            JOptionPane.showMessageDialog(null, "Name must have at least 2 words", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        }else if (name.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Invalid student name", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        } else if (address.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Invalid student address", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        }  else if (day < 1 || day > 31) {
			            JOptionPane.showMessageDialog(null, "Invalid day", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        }  else if (month < 1 || month > 12) {
			            JOptionPane.showMessageDialog(null, "Invalid month", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        } else if (year < 1900 || year > 2024) {
			            JOptionPane.showMessageDialog(null, "Invalid year", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        } else if (day > 28 && month == 2 && !isLeapYear(year)) {
			            JOptionPane.showMessageDialog(null, "February can have a maximum of 28 days in a non-leap year", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        } else if ((day > 30 && (month == 4 || month == 6 || month == 9 || month == 11)) || (day > 31)) {
			            JOptionPane.showMessageDialog(null, "Month " + month + " can have a maximum of 30 days", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        } 
			        else if (phoneNumber.length() < 10) {
			            JOptionPane.showMessageDialog(null, "Phone number must be at least 10 digits", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        }else if (creditsCompleted < 0) {
			            JOptionPane.showMessageDialog(null, "Invalid Credits Completed value", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        } else if (creditsOwed < 0) {
			            JOptionPane.showMessageDialog(null, "Invalid Credits Owed value", "Error", JOptionPane.ERROR_MESSAGE);
			            return false;
			        }
			        return true;
			    } catch (NumberFormatException e) {
			        JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
			    } catch (IllegalArgumentException e) {
			        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			    }

			    return false;
			}

		 

		 public int getIndexofSelectedRow() {
			
			 int index=table.getSelectedRow(); 
			 selectedStu=classRoom.getStudentList().get(index);
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

