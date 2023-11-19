package view.Student;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SyllabiView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public SyllabiView() {
		setBackground(new Color(255, 0, 0));
		setLayout(null);
		this.setBounds(162, 0, 835, 640);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 46, 815, 584);
		add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
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
		table.setBounds(10, 10, 795, 564);
		panel.add(table);
		
		JTextArea txtrSyllabi = new JTextArea();
		txtrSyllabi.setForeground(new Color(255, 255, 255));
		txtrSyllabi.setBackground(new Color(198, 0, 0));
		txtrSyllabi.setText("Syllabi");
		txtrSyllabi.setBounds(10, 0, 128, 47);
		
		txtrSyllabi.setAlignmentX(Component.RIGHT_ALIGNMENT);
		txtrSyllabi.setAlignmentY(Component.BOTTOM_ALIGNMENT);

		int width = 200;  // Desired width
		int height = 100; // Desired height
		txtrSyllabi.setPreferredSize(new Dimension(width, height));

		add(txtrSyllabi);
	}
}
