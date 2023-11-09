package view.Student;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CuriculumView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public CuriculumView() {
		setLayout(null);
		this.setBounds(162, 0, 835, 640);
		
		JLabel lblCuriculum = new JLabel("Curiculum");
		lblCuriculum.setBounds(268, 215, 467, 225);
		add(lblCuriculum);
		

	}
}
