package view.Student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class DetalinformationofCourseView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalinformationofCourseView frame = new DetalinformationofCourseView();
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
	public DetalinformationofCourseView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentId.setBounds(6, 31, 102, 29);
		contentPane.add(lblStudentId);
		
		JLabel lblStudentId_1 = new JLabel("Name:");
		lblStudentId_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentId_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentId_1.setBounds(6, 70, 102, 29);
		contentPane.add(lblStudentId_1);
		
		JLabel lblStudentId_2 = new JLabel("Address:");
		lblStudentId_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentId_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentId_2.setBounds(6, 111, 102, 29);
		contentPane.add(lblStudentId_2);
		
		JLabel lblDayOfBirth = new JLabel("Day Of Birth:");
		lblDayOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDayOfBirth.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDayOfBirth.setBounds(6, 152, 102, 29);
		contentPane.add(lblDayOfBirth);
		
		JLabel lblID_info = new JLabel("0");
		lblID_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblID_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID_info.setBounds(134, 31, 102, 29);
		contentPane.add(lblID_info);
		
		JLabel lblName_info = new JLabel((String) null);
		lblName_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblName_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName_info.setBounds(134, 70, 102, 29);
		contentPane.add(lblName_info);
		
		JLabel lblAddress_info = new JLabel((String) null);
		lblAddress_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress_info.setBounds(134, 111, 102, 29);
		contentPane.add(lblAddress_info);
		
		JLabel lblDayOfBirth_info = new JLabel("null");
		lblDayOfBirth_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblDayOfBirth_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDayOfBirth_info.setBounds(134, 152, 102, 29);
		contentPane.add(lblDayOfBirth_info);
	}
}
