package view;

import javax.swing.JPanel;
import javax.swing.JTable;

public class ClassesView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ClassesView() {
		setLayout(null);
		
		
		table = new JTable();
		table.setBounds(0, 10, 450, 280);
		add(table);

	}
}
