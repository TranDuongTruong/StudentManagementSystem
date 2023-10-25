package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

public class ClassesView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassesView frame = new ClassesView();
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
	public ClassesView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 97, 359);
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
		
		textField = new JTextField();
		textField.setBounds(103, 11, 247, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(360, 10, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(103, 42, 468, 306);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Class Code", "Class Name ", "Number Of Current Student", "Maximum Num of Student"},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
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
				"New column", "New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(74);
		table.getColumnModel().getColumn(1).setPreferredWidth(107);
		table.getColumnModel().getColumn(2).setPreferredWidth(156);
		table.getColumnModel().getColumn(3).setPreferredWidth(166);
		scrollPane.setColumnHeaderView(table);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		
		});
	}
}
