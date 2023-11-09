package view.Student;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class InformationView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public InformationView() {
		setLayout(null);
		this.setBounds(162, 0, 835, 640);
		
		JLabel lblInfomation = new JLabel("Infomation");
		lblInfomation.setBounds(342, 174, 313, 169);
		add(lblInfomation);
		

	}
}
