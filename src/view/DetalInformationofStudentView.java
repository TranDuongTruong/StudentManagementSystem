package view;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.net.URL;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class DetalInformationofStudentView extends JPanel {

	private static final long serialVersionUID = 1L;
	private Object imageIcon;

	/**
	 * Create the panel.
	 */
	public DetalInformationofStudentView() {
		setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentId.setBounds(40, 55, 102, 29);
		add(lblStudentId);
		
		JLabel lblNewLabel = new JLabel("hinh");
		lblNewLabel.setBounds(386, 103, 166, 177);
		add(lblNewLabel);
		
		JLabel lblStudentInformation = new JLabel("STUDENT INFORMATION");
		lblStudentInformation.setIcon(new ImageIcon(DetalInformationofStudentView.class.getResource("/Assert/Custom-Icon-Design-Silky-Line-User-User-info-2.16.png")));
		lblStudentInformation.setForeground(new Color(255, 38, 11));
		lblStudentInformation.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblStudentInformation.setBounds(17, 6, 264, 37);
		add(lblStudentInformation);
		
		JLabel lblStudentId_1 = new JLabel("Name:");
		lblStudentId_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentId_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentId_1.setBounds(40, 94, 102, 29);
		add(lblStudentId_1);
		
		JLabel lblStudentId_2 = new JLabel("Address:");
		lblStudentId_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentId_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentId_2.setBounds(40, 135, 102, 29);
		add(lblStudentId_2);
		
		JLabel lblDayOfBirth = new JLabel("Day Of Birth:");
		lblDayOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDayOfBirth.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDayOfBirth.setBounds(40, 176, 102, 29);
		add(lblDayOfBirth);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGender.setBounds(40, 217, 102, 29);
		add(lblGender);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhoneNumber.setBounds(17, 258, 126, 29);
		add(lblPhoneNumber);
		
		JLabel lblCreditsCompleted = new JLabel("Credits Completed:");
		lblCreditsCompleted.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreditsCompleted.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCreditsCompleted.setBounds(-12, 299, 155, 29);
		add(lblCreditsCompleted);
		
		JLabel lblCreditsOwed = new JLabel("Credits Owed:");
		lblCreditsOwed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreditsOwed.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCreditsOwed.setBounds(17, 340, 126, 29);
		add(lblCreditsOwed);
		
		JLabel lblID_info = new JLabel("Student ID");
		lblID_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblID_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID_info.setBounds(168, 55, 102, 29);
		add(lblID_info);
		
		JLabel lblName_info = new JLabel("Student ID");
		lblName_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblName_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName_info.setBounds(168, 94, 102, 29);
		add(lblName_info);
		
		JLabel lblAddress_info = new JLabel("Student ID");
		lblAddress_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress_info.setBounds(168, 135, 102, 29);
		add(lblAddress_info);
		
		JLabel lblDayOfBirth_info = new JLabel("Student ID");
		lblDayOfBirth_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblDayOfBirth_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDayOfBirth_info.setBounds(168, 176, 102, 29);
		add(lblDayOfBirth_info);
		
		JLabel lblGender_info = new JLabel("Student ID");
		lblGender_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender_info.setBounds(168, 217, 102, 29);
		add(lblGender_info);
		
		JLabel lblPhoneNumber_info = new JLabel("Student ID");
		lblPhoneNumber_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNumber_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNumber_info.setBounds(168, 258, 102, 29);
		add(lblPhoneNumber_info);
		
		JLabel lblCreditsCompleted_info = new JLabel("Student ID");
		lblCreditsCompleted_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreditsCompleted_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCreditsCompleted_info.setBounds(168, 299, 102, 29);
		add(lblCreditsCompleted_info);
		
		JLabel lblCreditsOwed_info = new JLabel("Student ID");
		lblCreditsOwed_info.setHorizontalAlignment(SwingConstants.LEFT);
		lblCreditsOwed_info.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCreditsOwed_info.setBounds(168, 340, 102, 29);
		add(lblCreditsOwed_info);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 43, 623, 24);
		add(separator);
		
		
	}
}
