package view.Student;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ExaminationView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ExaminationView() {
		setLayout(null);
		this.setBounds(162, 0, 835, 640);
		
		JLabel lblExamination = new JLabel("Examination");
		lblExamination.setBounds(214, 174, 217, 83);
		add(lblExamination);
		

	}
}
