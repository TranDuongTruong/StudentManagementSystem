package view.Student;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ScheduleView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ScheduleView() {
		setLayout(null);
		this.setBounds(162, 0, 835, 640);
		
		JLabel lblSchedual = new JLabel("Schedule");
		lblSchedual.setBounds(267, 304, 45, 13);
		add(lblSchedual);
		

	}
}
