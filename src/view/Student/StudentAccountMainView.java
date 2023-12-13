package view.Student;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.Timer;

import controller.Admin.LoginController;
import controller.DatabaseConnection;
import controller.Student.MainViewCtrl;
import model.Student;
import view.Admin.LoginView;
import view.style.GradientPanel;

public class StudentAccountMainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	final JButton btn_CourseRegistration;
	JButton btn_Transcript;
	JButton btn_Curriculum;
	JButton btn_Examination;
	JButton btn_Information;
	JButton btn_Schedule;
	JButton btn_Syllabli;
	JButton btn_chatBox;

	final JPanel content;
	static Student student;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//				LoginController.studentId=9;
					retrieveStudent();
					StudentAccountMainView frame = new StudentAccountMainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void retrieveStudent() {
		DatabaseConnection db = new DatabaseConnection();
		Connection con = db.connectToBB();
		int studentID;
		String name;
		LocalDate dob;
		String address;
		boolean gender;
		String phoneNumber;
		int creditsCompleted;
		int creditsOwed;

		try {
			String query = "SELECT * FROM student WHERE studentID = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, LoginController.studentId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				studentID = rs.getInt("studentID");
				name = rs.getString("name");
				Date dobDate = rs.getDate("dob");
				dob = dobDate.toLocalDate();
				address = rs.getString("address");
				gender = rs.getBoolean("gender");
				phoneNumber = rs.getString("phoneNumber");
				creditsCompleted = rs.getInt("creditsCompleted");
				creditsOwed = rs.getInt("creditsOwed");
				System.out.println("Aaa" + creditsOwed);
				student = new Student(studentID, name, dob, address, gender, phoneNumber, creditsCompleted,
						creditsOwed);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static class ClockLabel extends JLabel {
		private final SimpleDateFormat timeFormat;
		private Timer timer;

		public ClockLabel() {
			super();
			this.timeFormat = new SimpleDateFormat("hh:mm:ss a");
			setFont(new Font("Verdana", Font.PLAIN, 20));
			setForeground(new Color(0x00FF00));
			setBackground(Color.black);
			setOpaque(true);

			updateTime();
			timer = new Timer(1000, e -> updateTime());
			timer.start();
		}

		private void updateTime() {
			setText(timeFormat.format(Calendar.getInstance().getTime()));
		}

		public void startTimer() {
			timer.start();
		}

		public void stopTimer() {
			timer.stop();
		}
	}

	ClockLabel clockLabel;

	public class DateLabel extends JLabel {
		private final SimpleDateFormat dateFormat;
		private Timer timer;

		public DateLabel() {
			super();
			this.dateFormat = new SimpleDateFormat("EEE, dd MMM, yyyy", Locale.ENGLISH);
			setFont(new Font("Verdana", Font.PLAIN, 16));
			setForeground(new Color(0x00FF00));
			setBackground(Color.black);
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

	DateLabel dateLabel;

	public StudentAccountMainView() {
		// setExtendedState(JFrame.MAXIMIZED_BOTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1021, 677);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setSize(this.getMaximumSize());
		contentPane.setLayout(null);

		GradientPanel panel = new GradientPanel(Color.red, Color.white);
		panel.setBackground(new Color(255, 128, 128));
		panel.setBounds(0, 0, 162, 640);
		contentPane.add(panel);
		panel.setLayout(null); 

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setAlignmentX(0.0f);
		verticalBox_1.setBounds(10, 83, 132, 573);
		panel.add(verticalBox_1);

		String studentName = retrieveStudentName(LoginController.studentId);
		JButton btn_DashBoard_1 = new JButton(studentName);
		btn_DashBoard_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		// btn_DashBoard_1.setText(studentName);
		btn_DashBoard_1.setMaximumSize(new Dimension(120, 20));
		btn_DashBoard_1.setBorder(null);
		btn_DashBoard_1.setBackground(Color.RED); // Set the name label color to red
		verticalBox_1.add(btn_DashBoard_1);

		btn_Information = new JButton("Dasboard"); // Updated button label
		btn_Information.setMaximumSize(new Dimension(120, 20));
		btn_Information.setBackground(Color.LIGHT_GRAY);
		verticalBox_1.add(btn_Information);

		Component verticalStrut_1_1 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_1_1);

		btn_Schedule = new JButton("Schedule"); // Updated button label
		btn_Schedule.setMaximumSize(new Dimension(120, 20));
		btn_Schedule.setBackground(Color.LIGHT_GRAY);
		verticalBox_1.add(btn_Schedule);

		Component verticalStrut_2_1 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_2_1);

		JButton btn_Learning = new JButton("Learning");
		btn_Learning.setMaximumSize(new Dimension(120, 20));
		btn_Learning.setBackground(Color.LIGHT_GRAY);
		verticalBox_1.add(btn_Learning);

		// Create the additional buttons
		int lBtn = 120, wBtn = 15;
		btn_CourseRegistration = new JButton("Course");
		btn_CourseRegistration.setMaximumSize(new Dimension(lBtn, wBtn));

		btn_Transcript = new JButton("Transcript");
		btn_Transcript.setMaximumSize(new Dimension(lBtn, wBtn));

		btn_Curriculum = new JButton("Curriculum");
		btn_Curriculum.setMaximumSize(new Dimension(lBtn, wBtn));
		btn_Examination = new JButton("Examination");
		btn_Examination.setMaximumSize(new Dimension(lBtn, wBtn));

		btn_Syllabli = new JButton("Syllabli");

		btn_Syllabli.setMaximumSize(new Dimension(lBtn, wBtn));

		btn_chatBox = new JButton("ChatGPT");

		btn_chatBox.setMaximumSize(new Dimension(lBtn, wBtn));
		// Set the initial visibility and size of the additional buttons
		setAdditionalButtonsVisible(false);
		setAdditionalButtonsSize(80, 20);

		// ActionListener for the "Learning" button
		btn_Learning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isVisible = !btn_CourseRegistration.isVisible();

				// Toggle visibility of additional buttons
				setAdditionalButtonsVisible(isVisible);

				// Toggle size of additional buttons
				if (isVisible) {
					setAdditionalButtonsSize(80, 20);
				} else {
					setAdditionalButtonsSize(60, 20);
				}
			}
		});

		Component verticalStrut_2_1_1 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_2_1_1);

		// Add the additional buttons to the vertical box
		verticalBox_1.add(btn_CourseRegistration);

		Component verticalStrut_2_1_2 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_2_1_2);
		verticalBox_1.add(btn_Transcript);

		Component verticalStrut_2_1_3 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_2_1_3);
		verticalBox_1.add(btn_Curriculum);

		Component verticalStrut_2_1_4 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_2_1_4);
		verticalBox_1.add(btn_Examination);

		Component verticalStrut_2_1_5 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_2_1_5);
		verticalBox_1.add(btn_Syllabli);

		Component verticalStrut_2_1_6 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_2_1_6);
		verticalBox_1.add(btn_chatBox);

		Component verticalStrut_2_1_7 = Box.createVerticalStrut(300);
		verticalBox_1.add(verticalStrut_2_1_7);

		JButton btn_Logout = new JButton("<- Logout"); // Updated button label
		btn_Logout.setMaximumSize(new Dimension(140, 25));
		btn_Logout.setBackground(Color.WHITE);
		btn_Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController loginController = new LoginController(new LoginView());
				loginController.displayLoginView();
				dispose();

			}
		});

		verticalBox_1.add(btn_Logout);

		Component verticalStrut_4 = Box.createVerticalStrut(10);
		verticalStrut_4.setBounds(10, 61, 132, 10);
		panel.add(verticalStrut_4);
		
		//Start: Clock
		clockLabel = new ClockLabel();
		clockLabel.setBounds(10, 0, 142, 26);
		panel.add(clockLabel);

		dateLabel = new DateLabel();
		dateLabel.setBounds(0, 28, 162, 20);
		panel.add(dateLabel);
		//End: Clock


		content = new JPanel();
		content.setBorder(new LineBorder(new Color(255, 0, 0)));
		content.setBounds(162, 0, 835, 630);

		contentPane.add(content);
		content.setLayout(null);

		JLabel label = new JLabel("New label");
		label.setBounds(410, 220, 45, 13);
		content.add(label);

		setButtonListener();

		// Basic Design --------------------------------------------------------

	}

	final ScheduleView scheduleView = new ScheduleView();
	final InformationView infoView = new InformationView();
	final ExaminationView examView = new ExaminationView();
	final CuriculumView curView = new CuriculumView();
	final CourseView courView = new CourseView();
	final TranscriptView transView = new TranscriptView(this);
	final SyllabiView syllabiView = new SyllabiView();
	final chatBoxViewP chatView = new chatBoxViewP();
	final DashboardStudentView dashboarStudentView = new DashboardStudentView();
	TranscriptDetailView transcriptDetailView;
	boolean hasScheduleView = false;
	boolean hasInformationView = false;
	boolean hasExaminationView = false;
	boolean hasCurriculumView = false;
	boolean hasCourseView = false;
	boolean hasTranscriptView = false;
	boolean hasSyllabi = false;
	boolean hasChatBox = false;
	boolean hasTranscriptDetail = false;
	boolean hasdashboarStudentView = false;

	public void setButtonListener() {

		btn_Schedule.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				contentPane.remove(content);
				removeContent();

				contentPane.add(scheduleView);
				contentPane.invalidate();
				contentPane.repaint();
				hasScheduleView = true;
			}
		});
		btn_CourseRegistration.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				contentPane.remove(content);
				removeContent();
				contentPane.add(courView);
				contentPane.invalidate();
				contentPane.repaint();
				hasCourseView = true;
			}
		});

		btn_Transcript.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				contentPane.remove(content);
				removeContent();
				contentPane.add(transView);
				contentPane.invalidate();
				contentPane.repaint();
				hasTranscriptView = true;
			}
		});
		btn_Curriculum.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				contentPane.remove(content);
				removeContent();
				contentPane.add(curView);
				contentPane.invalidate();
				contentPane.repaint();
				hasCurriculumView = true;
			}
		});
		btn_Examination.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				contentPane.remove(content);
				removeContent();
				contentPane.add(examView);
				contentPane.invalidate();
				contentPane.repaint();
				hasExaminationView = true;
			}
		});
		btn_Information.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				contentPane.remove(content);
				removeContent();
				contentPane.add(dashboarStudentView);
				contentPane.invalidate();
				contentPane.repaint();
				hasdashboarStudentView = true;
			}
		});

		btn_Syllabli.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				contentPane.remove(content);
				removeContent();
				contentPane.add(syllabiView);
				contentPane.invalidate();
				contentPane.repaint();
				hasSyllabi = true;
			}
		});
		btn_chatBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				contentPane.remove(content);
				removeContent();
				contentPane.add(chatView);
				contentPane.invalidate();
				contentPane.repaint();
				hasChatBox = true;
			}
		});

	}

	public void removeContent() {
		if (hasScheduleView) {
			contentPane.remove(scheduleView);
			hasScheduleView = false;
		}
		if (hasInformationView) {
			contentPane.remove(infoView);
			hasInformationView = false;
		}

		if (hasExaminationView) {
			contentPane.remove(examView);
			hasExaminationView = false;
		}

		if (hasCurriculumView) {
			contentPane.remove(curView);
			hasCurriculumView = false;
		}

		if (hasCourseView) {
			contentPane.remove(courView);
			hasCourseView = false;
		}

		if (hasTranscriptView) {
			contentPane.remove(transView);
			hasTranscriptView = false;
		}
		if (hasSyllabi) {
			contentPane.remove(syllabiView);
			hasSyllabi = false;
		}
		if (hasChatBox) {
			contentPane.remove(chatView);
			hasChatBox = false;
		}
		if (hasTranscriptDetail) {
			contentPane.remove(transcriptDetailView);
			hasTranscriptDetail = false;
		}
		if (hasdashboarStudentView) {
			contentPane.remove(dashboarStudentView);
			hasdashboarStudentView = false;
		}
	}

	public void setDetailTranscript(String classCode) {
		transcriptDetailView = new TranscriptDetailView(classCode);
		contentPane.remove(content);
		removeContent();

		contentPane.add(transcriptDetailView);
		contentPane.invalidate();
		contentPane.repaint();
		hasTranscriptDetail = true;
	}

	// Helper method to set the visibility of the additional buttons
	private void setAdditionalButtonsVisible(boolean visible) {
		btn_CourseRegistration.setVisible(visible);
		btn_Transcript.setVisible(visible);
		btn_Curriculum.setVisible(visible);
		btn_Examination.setVisible(visible);
		btn_Syllabli.setVisible(visible);
		btn_chatBox.setVisible(visible);
	}

	// Helper method to set the size of the additional buttons
	private void setAdditionalButtonsSize(int width, int height) {
		Dimension size = new Dimension(width, height);
		btn_CourseRegistration.setPreferredSize(new Dimension(80, 20));
		btn_Transcript.setPreferredSize(size);
		btn_Curriculum.setPreferredSize(size);
		btn_Examination.setPreferredSize(size);
		btn_Syllabli.setPreferredSize(size);
		btn_chatBox.setPreferredSize(size);
	}

	private String retrieveStudentName(int studentId) {
		DatabaseConnection db = new DatabaseConnection();
		Connection con = db.connectToBB();

		try {
			String query = "SELECT name FROM student WHERE studentID = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				return rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; // Trả về chuỗi trống nếu có lỗi
	}
}