package view.Teacher;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.data.general.DefaultPieDataset;

import controller.DatabaseConnection;
import controller.Teacher.PerformanceController_Admin;
import model.CreditsPerformance;
import view.Admin.AdminHomeView;
import view.Admin.AttendanceRateChart;
import view.Admin.DisplayUserAccountsView;


import org.jfree.chart.plot.PiePlot;

import javax.swing.ImageIcon;
import javax.swing.JButton;
public class PerformanceView_Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;  JFreeChart chart; ChartPanel chartPanel;
	private JButton btnAttendanceRate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerformanceView_Admin frame = new PerformanceView_Admin();
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
	public PerformanceView_Admin() {
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 867, 588);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        // Thêm biểu đồ tròn
	        DefaultPieDataset dataset = createDataset();
	         chart = createChart(dataset);
	         chartPanel = new ChartPanel(chart);
	        chartPanel.setBounds(10, 10, 479, 259);
	        contentPane.add(chartPanel);
	        btnAttendanceRate = new JButton("Attendance Rate");
	         btnAttendanceRate.setBounds(10, 306, 119, 48);
	         contentPane.add(btnAttendanceRate);
	         
	         btnAttendanceRate.addActionListener(new ActionListener() {
	         	public void actionPerformed(ActionEvent e) {
	         		
	         		AttendanceRateChart a=new AttendanceRateChart();
	         		a.setVisible(true);
	         		dispose();
	         		
	         	}
	         });
	        JButton btnBack = new JButton("Back");
	        btnBack.setBounds(740, 511, 103, 30);
	        
	        ImageIcon backIcon = new ImageIcon(DisplayUserAccountsView.class.getResource("/Assert/admin/back.png"));
	        btnBack.setIcon(backIcon);
	        btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AdminHomeView adminhomeView = new AdminHomeView();
		            adminhomeView.setVisible(true);
	                
	                dispose();

				}
			});
	        contentPane.add(btnBack);
	        //System.out.println("ddddđ"); 
	       // PerformanceController_Admin perCtrl=new PerformanceController_Admin(this);
	        //System.out.println("ddddđ");
	        setVisible(true);
	       
	}
	private DefaultPieDataset createDataset() {
	    DefaultPieDataset dataset = new DefaultPieDataset();
	    DatabaseConnection con = new DatabaseConnection();
	    CreditsPerformance creditsData = con.getCreditsPerformance();
	    
	    dataset.setValue("More than 5 subjects owed: " + creditsData.getNumStudentsOwedMoreThan5(), creditsData.getNumStudentsOwedMoreThan5());
	    dataset.setValue("1-5 subjects owed: " + creditsData.getNumStudentsOwed1To5(), creditsData.getNumStudentsOwed1To5());
	    dataset.setValue("No subjects owed: " + creditsData.getNumStudentsNotOwed(), creditsData.getNumStudentsNotOwed());

	    return dataset;
	}
	private JFreeChart createChart(DefaultPieDataset dataset) {
	    JFreeChart chart = ChartFactory.createPieChart(
	            "Credits Performance",
	            dataset,
	            true,
	            true,
	            false
	    );

	    PiePlot plot = (PiePlot) chart.getPlot();
	    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2} ({1})"));

	    return chart;
	}
	
	 public void creditsChartListener(final ActionListener listener) {
		    chartPanel.addChartMouseListener(new ChartMouseListener() {
		        public void chartMouseClicked(ChartMouseEvent event) {
		            listener.actionPerformed(new ActionEvent(event, ActionEvent.ACTION_PERFORMED, "ChartMouseClicked"));
		            dispose();
		        }

				public void chartMouseMoved(ChartMouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

		        
		    });
		}
	 
}
