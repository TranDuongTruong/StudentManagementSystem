package view.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import controller.DatabaseConnection;
import controller.Teacher.PerformanceController_Admin;
import model.CreditsPerformance;
import java.awt.Color;

public class AccademicPerfromanceP extends JPanel {
	private JPanel contentPane;  JFreeChart chart; ChartPanel chartPanel;
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AccademicPerfromanceP() {
		setBounds(162, 0, 835, 640);
		setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBounds(0, 0, 835, 640);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(contentPane);
        contentPane.setLayout(null);

        
        // Thêm biểu đồ tròn credits perfoemance 
        DefaultPieDataset dataset = createDataset();
         chart = createChart(dataset);
         chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(10, 64, 479, 259);
        contentPane.add(chartPanel);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(20, 371, 469, 259);
        contentPane.add(panel);
        
        ImageIcon backIcon = new ImageIcon(ShowUserAccountP.class.getResource("/Assert/admin/back.png"));
        //System.out.println("ddddđ"); 
        PerformanceController_Admin perCtrl=new PerformanceController_Admin(this);
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
	          //  dispose();
	        }

			public void chartMouseMoved(ChartMouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

	        
	    });
	}
}
