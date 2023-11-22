package view.Teacher;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseConnection;
import controller.Teacher.ClassesController;
import model.ClassesManager;
import model.Classroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

public class ClassesViewP extends JPanel {
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
	JButton btn_CapNhat;	public ClassesManager model=new ClassesManager();
	JButton btn_Xoa;JPanel classesPane;
	public Classroom currentClassroom;
	private static final long serialVersionUID = 1L;
	TeacherAccountMainView mainView;
	/**
	 * Create the panel.
	 */
	public ClassesViewP(TeacherAccountMainView mainView) {
		this.mainView=mainView;
		 setLayout(null);
	        setBounds(162, 0, 835, 640);
	        
	         classesPane = new JPanel();
	        classesPane.setBackground(new Color(255, 255, 255));
	        classesPane.setBounds(0, 0, 835, 640);
	        add(classesPane);
	   
	        
	        classesPane.setLayout(null);
	        JLabel lb_FindMaLop = new JLabel("Class Code");
			lb_FindMaLop.setHorizontalAlignment(SwingConstants.CENTER);
			lb_FindMaLop.setFont(new Font("Tahoma", Font.PLAIN, 25));
			lb_FindMaLop.setBounds(63, 30, 140, 48);
			classesPane.add(lb_FindMaLop);
			
			textField_FindMaLop = new JTextField();
			textField_FindMaLop.setHorizontalAlignment(SwingConstants.CENTER);
			textField_FindMaLop.setFont(new Font("Tahoma", Font.PLAIN, 23));
			textField_FindMaLop.setBounds(213, 30, 228, 48);
			classesPane.add(textField_FindMaLop);
			textField_FindMaLop.setColumns(10);
			
			 btn_Tim = new JButton("Find");
			 btn_Tim.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 	}
			 });
			btn_Tim.setFont(new Font("Tahoma", Font.PLAIN, 25));
			btn_Tim.setBounds(465, 30, 97, 48);
			classesPane.add(btn_Tim);
			
			JLabel lblDanhSchCc = new JLabel("List of classes");
			lblDanhSchCc.setHorizontalAlignment(SwingConstants.CENTER);
			lblDanhSchCc.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblDanhSchCc.setBounds(63, 111, 177, 41);
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
				    	 			    	 
				    	 mainView.loadStudentView(classRoom);
	                }
			    }
			});
			//acessToListOfStudents();
			
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(73, 153, 635, 218);
			classesPane.add(scrollPane);
			
			JLabel lb_Malop = new JLabel("Class Code");
			lb_Malop.setHorizontalAlignment(SwingConstants.CENTER);
			lb_Malop.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb_Malop.setBounds(42, 410, 97, 41);
			classesPane.add(lb_Malop);
			
			textField_MaLop = new JTextField();
			textField_MaLop.setBounds(159, 414, 123, 41);
			classesPane.add(textField_MaLop);
			textField_MaLop.setColumns(10);
			
			JLabel lb_TenLop = new JLabel("Class name");
			lb_TenLop.setHorizontalAlignment(SwingConstants.CENTER);
			lb_TenLop.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb_TenLop.setBounds(42, 467, 118, 41);
			classesPane.add(lb_TenLop);
			
			textField_TenLop = new JTextField();
			textField_TenLop.setColumns(10);
			textField_TenLop.setBounds(159, 466, 123, 41);
			classesPane.add(textField_TenLop);
			
			JLabel lb_SoHSHT = new JLabel("Number of current student");
			lb_SoHSHT.setHorizontalAlignment(SwingConstants.CENTER);
			lb_SoHSHT.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb_SoHSHT.setBounds(297, 414, 244, 41);
			classesPane.add(lb_SoHSHT);
			
			textField_SoHSHT = new JTextField();
			textField_SoHSHT.setColumns(10);
			textField_SoHSHT.setBounds(541, 414, 164, 41);
			classesPane.add(textField_SoHSHT);
			
			JLabel lb_SoHSTD = new JLabel("Number of max student");
			lb_SoHSTD.setHorizontalAlignment(SwingConstants.CENTER);
			lb_SoHSTD.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb_SoHSTD.setBounds(307, 467, 221, 41);
			classesPane.add(lb_SoHSTD);
			
			textField_SoHSTD = new JTextField();
			textField_SoHSTD.setColumns(10);
			textField_SoHSTD.setBounds(541, 471, 164, 41);
			classesPane.add(textField_SoHSTD);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(73, 98, 601, 2);
			classesPane.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(73, 395, 601, 2);
			classesPane.add(separator_1);
			
			 btn_Them = new JButton("Add");
		
			
			btn_Them.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btn_Them.setBounds(63, 540, 97, 33);
			classesPane.add(btn_Them);
			
			 btn_Xoa = new JButton("Delete");
			
			btn_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btn_Xoa.setBounds(181, 540, 97, 33);
			classesPane.add(btn_Xoa);
			
			 btn_CapNhat = new JButton("Update");
		
			btn_CapNhat.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btn_CapNhat.setBounds(297, 540, 148, 33);
			classesPane.add(btn_CapNhat);
			
			 btn_Luu = new JButton("Save");
			btn_Luu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btn_Luu.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btn_Luu.setBounds(455, 540, 97, 33);
			classesPane.add(btn_Luu);
			
			 btn_Huy = new JButton("Cancel");
			
			btn_Huy.setFont(new Font("Tahoma", Font.PLAIN, 20));
			btn_Huy.setBounds(577, 540, 110, 33);
			classesPane.add(btn_Huy);
			
			 btnHuyTim = new JButton("Undo");
			btnHuyTim.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnHuyTim.setBounds(572, 30, 110, 48);
			classesPane.add(btnHuyTim);
			
			ClassesController control= new ClassesController(this);
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
	    textField_SoHSHT.setText(String.valueOf(classroom.getNumOfCurentStudents()));
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
	



}

