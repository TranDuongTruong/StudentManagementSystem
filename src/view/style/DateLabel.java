package view.style;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.Timer;

public class DateLabel extends JLabel {
	private final SimpleDateFormat dateFormat;
	private Timer timer;

	public DateLabel() {
		super();
		this.dateFormat = new SimpleDateFormat("EEE, dd MMM, yyyy", Locale.ENGLISH);
		setFont(new Font("Verdana", Font.PLAIN, 16));
		setForeground(new Color(255,128,128));
		setBackground(Color.red);
		setOpaque(true);

		updateDate();
		timer = new Timer(60000, e -> updateDate()); // Update every minute
		timer.start();
	}

	private void updateDate() {
		setText(dateFormat.format(Calendar.getInstance().getTime()));
	}

	public void startTimer() {
		timer.start();
	}

	public void stopTimer() {
		timer.stop();
	}
}