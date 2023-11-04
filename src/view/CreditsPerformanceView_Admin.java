package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.PerformanceController_Admin;
import model.Classroom;
import model.Student;

import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;

public class CreditsPerformanceView_Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	static Classroom classRoom;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CreditsPerformanceView_Admin frame = new CreditsPerformanceView_Admin();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreditsPerformanceView_Admin(final Classroom classRoom) {
		this.classRoom=classRoom;	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 50, 738, 160);
		contentPane.add(scrollPane);
		
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
			    	 stu.setVisible(true);
			    	 
                    	                   
                }
		    }
		});
		scrollPane.setViewportView(table);
		PerformanceController_Admin per=new PerformanceController_Admin(this, classRoom);
		 
	}
	public void displayStudentList(List<Student> studentList) {		 
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    // Xóa tất cả các dòng hiện tại trong model
	    model.setRowCount(0);
	   // System.out.println("\tfffffffffff"+studentList.size());
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
}
