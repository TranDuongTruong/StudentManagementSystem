package view.Student;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.Teacher.MainView;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.net.URL;
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
    final JPanel content ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentAccountMainView frame = new StudentAccountMainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentAccountMainView() {
		//setExtendedState(JFrame.MAXIMIZED_BOTH);

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
		content.setBounds(162, 0, 835, 640);
		contentPane.add(content);
		content.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(410, 220, 45, 13);
		content.add(label);
		
		setButtonListener();
		
		//Basic Design --------------------------------------------------------
		
		
		
		
	}
	
	public void setButtonListener() {
		final ScheduleView scheduleView = new ScheduleView();
		final InformationView infoView=new InformationView();
		final ExaminationView examView=new  ExaminationView();
		final CuriculumView curView=new CuriculumView();
		final CourseView courView=new CourseView();
		final TranscriptView transView=new TranscriptView();
		
		btn_Schedule.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {	
//		    	contentPane.add(content);
//		        contentPane.add(scheduleView);
//		        contentPane.revalidate();
//		        contentPane.remove(content);
//		        contentPane.repaint();
		    	content.removeAll();
		    	
		        content.add(scheduleView);
		        content.revalidate();	       
		        content.repaint();
		     
		    }
		});
		 btn_CourseRegistration.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	
			    	content.removeAll();
			        content.add(courView);
			        content.revalidate();	       
			        content.repaint();
			    }
			});
		 
	     btn_Transcript.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	
			    	content.removeAll();
			        content.add(transView);
			        content.revalidate();	       
			        content.repaint();
			    }
			});
	     btn_Curriculum.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	
			    	content.removeAll();
			        content.add(curView);
			        content.revalidate();	       
			        content.repaint();
			    }
			});
	     btn_Examination.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	
			    	content.removeAll();
			        content.add(examView);
			        content.revalidate();	       
			        content.repaint();
			    }
			});
	     btn_Information.addActionListener(new ActionListener() {
				
			    public void actionPerformed(ActionEvent e) {	
			    	content.removeAll();
			        content.add(infoView);
			        content.revalidate();	       
			        content.repaint();
			    }
			});
	     
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