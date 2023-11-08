package view.Student;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class TranscriptView extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TranscriptView() {
		setLayout(null);
		this.setBounds(162, 0, 835, 640);
		JButton button = new JButton("New button");
		button.setBounds(182, 110, 85, 21);
		add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(671, 588, 85, 21);
		add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(34, 566, 85, 21);
		add(button_2);
		
		JButton button_3 = new JButton("New button");
		button_3.setBounds(726, 29, 85, 21);
		add(button_3);
		
		JLabel lblTranscripts = new JLabel("Transcripts");
		lblTranscripts.setBounds(220, 230, 290, 110);
		add(lblTranscripts);

	}
}
