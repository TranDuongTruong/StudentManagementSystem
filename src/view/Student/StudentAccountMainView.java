package view.Student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DatabaseConnection;
import controller.Admin.LoginController;
import model.Student;
import view.Teacher.MainView;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentAccountMainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	final JButton btn_CourseRegistration ;
    JButton btn_Transcript ;
    JButton btn_Curriculum ;
    JButton btn_Examination ;
    JButton btn_Information;
    JButton btn_Schedule;
    final JPanel content;
    static Student student;
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//LoginController.studentId=2;
					retrieveStudent();
					StudentAccountMainView frame = new StudentAccountMainView();
					
					System.out.println("aaaa");
					
					
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
		        String query =  "SELECT * FROM student WHERE studentID = ?";
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
		            System.out.println("Aaa"+creditsOwed);
		             student = new Student(studentID, name, dob, address, gender, phoneNumber, creditsCompleted, creditsOwed);
		           
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    
	}
	public StudentAccountMainView() {
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
	
		System.out.println("aaaaa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1021, 677);
		contentPane = new JPanel();
		 contentPane.setBackground(new Color(255, 255, 255));
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setSize(this.getMaximumSize());
		contentPane.setLayout(null);
	
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 128));
		panel.setBounds(0, 0, 152, 640);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setAlignmentX(0.0f);
		verticalBox_1.setBounds(10, 57, 132, 573);
		panel.add(verticalBox_1);
		
		JButton btn_DashBoard_1 = new JButton("Tran Van A");
		btn_DashBoard_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_DashBoard_1.setMaximumSize(new Dimension(100, 20));
		btn_DashBoard_1.setBorder(null);
		btn_DashBoard_1.setBackground(Color.RED); // Set the name label color to red
		verticalBox_1.add(btn_DashBoard_1);
		
		Component verticalStrut_4 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_4);
		
		 btn_Information = new JButton("Information"); // Updated button label
		btn_Information.setMaximumSize(new Dimension(100, 20));
		btn_Information.setBackground(Color.LIGHT_GRAY);
		verticalBox_1.add(btn_Information);
		
		Component verticalStrut_1_1 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_1_1);
		
		 btn_Schedule = new JButton("Schedule"); // Updated button label
		btn_Schedule.setMaximumSize(new Dimension(100, 20));
		btn_Schedule.setBackground(Color.LIGHT_GRAY);
		verticalBox_1.add(btn_Schedule);
		
		
	
		Component verticalStrut_2_1 = Box.createVerticalStrut(10);
		verticalBox_1.add(verticalStrut_2_1);
		
		JButton btn_Learning = new JButton("Learning");
	    btn_Learning.setMaximumSize(new Dimension(100, 20));
	    btn_Learning.setBackground(Color.LIGHT_GRAY);
	    verticalBox_1.add(btn_Learning);

	    // Create the additional buttons
	    int lBtn=120,wBtn=15;
	     btn_CourseRegistration = new JButton("Course");
	     btn_CourseRegistration.setMaximumSize(new Dimension(lBtn, wBtn));
			
	     btn_Transcript = new JButton("Transcript");
	     btn_Transcript.setMaximumSize(new Dimension(lBtn, wBtn));
	     
	     btn_Curriculum = new JButton("Curriculum");
	     btn_Curriculum.setMaximumSize(new Dimension(lBtn, wBtn));
	     btn_Examination = new JButton("Examination");
	     btn_Examination.setMaximumSize(new Dimension(lBtn, wBtn));

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
		
		Component verticalStrut_2_1_5 = Box.createVerticalStrut(300);
		verticalBox_1.add(verticalStrut_2_1_5);
		
		JButton btn_Logout = new JButton("<- Logout"); // Updated button label
		btn_Logout.setMaximumSize(new Dimension(100, 20));
		btn_Logout.setBackground(Color.WHITE);
		
		verticalBox_1.add(btn_Logout);
		
		 content = new JPanel();
		content.setBounds(162, 0, 835, 630);
		contentPane.add(content);
		content.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(410, 220, 45, 13);
		content.add(label);
		
		setButtonListener();
		
		//Basic Design --------------------------------------------------------
		
		
		
		
	}
	final ScheduleView scheduleView = new ScheduleView();
	final InformationView infoView=new InformationView();
	final ExaminationView examView=new  ExaminationView();
	final CuriculumView curView=new CuriculumView();
	final CourseView courView=new CourseView();
	final TranscriptView transView=new TranscriptView();
	boolean hasScheduleView = false;
	boolean hasInformationView = false;
	 boolean hasExaminationView = false;
	 boolean hasCurriculumView = false;
	 boolean hasCourseView = false;
	 boolean hasTranscriptView = false;
	public void setButtonListener() {

		btn_Schedule.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {	

		    	contentPane.remove(content);removeContent();
		    	
		    	contentPane.add(scheduleView);
		    	contentPane.invalidate();
		    	contentPane.repaint();hasScheduleView=true;
		    }
		});
		 btn_CourseRegistration.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	
			    	
			    	contentPane.remove(content);removeContent();
			    	contentPane.add(courView);	    	
			    	contentPane.invalidate();
			    	contentPane.repaint();hasCourseView =true;
			    }
			});
		 
	     btn_Transcript.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	
			    	contentPane.remove(content);removeContent();
			    	contentPane.add(transView);
			    	contentPane.invalidate();
			    	contentPane.repaint();hasTranscriptView =true;
			    }
			});
	     btn_Curriculum.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	
			    	contentPane.remove(content);removeContent();
			    	contentPane.add(curView);
			    	contentPane.invalidate();
			    	contentPane.repaint();hasCurriculumView =true;
			    }
			});
	     btn_Examination.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	
			    	contentPane.remove(content);removeContent();
			    	contentPane.add(examView);
			    	contentPane.invalidate();
			    	contentPane.repaint();hasExaminationView =true;
			    }
			});
	     btn_Information.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	
			    	contentPane.remove(content);removeContent();
			    	contentPane.add(infoView);
			    	contentPane.invalidate();
			    	contentPane.repaint();hasInformationView =true;
			    }
			});
	     
	}
	public void removeContent() {
		if (hasScheduleView) {
			  contentPane.remove(scheduleView);
			  hasScheduleView=false;
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
		}

	
	// Helper method to set the visibility of the additional buttons
	private void setAdditionalButtonsVisible(boolean visible) {
	    btn_CourseRegistration.setVisible(visible);
	    btn_Transcript.setVisible(visible);
	    btn_Curriculum.setVisible(visible);
	    btn_Examination.setVisible(visible);
	}

	// Helper method to set the size of the additional buttons
	private void setAdditionalButtonsSize(int width, int height) {
	    Dimension size = new Dimension(width, height);
	    btn_CourseRegistration.setPreferredSize(new Dimension(80, 20));
	    btn_Transcript.setPreferredSize(size);
	    btn_Curriculum.setPreferredSize(size);
	    btn_Examination.setPreferredSize(size);
	}
}