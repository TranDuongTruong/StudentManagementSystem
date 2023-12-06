package view.Admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.DatabaseConnection;

public class AttendanceRateChart extends JFrame implements ActionListener {
    private int columnCount = 3; // Số cột mặc định
    private DatabaseConnection db;
    private Connection connection;
    private DefaultCategoryDataset dataset;
    private JButton increaseButton;
    private JButton decreaseButton;
    private JPanel buttonPanel;
    private ChartPanel chartPanel;
    LocalDate currentDate ;
    LocalDate originalDate ;
    DateTimeFormatter formatter ;
    String dateString;
    public AttendanceRateChart() {
    	
    	
        super("Attendance Rate Chart");
        currentDate = LocalDate.now();
	     formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd");
	     dateString = currentDate.format(formatter);
       
	     originalDate=currentDate;
	     
        // Tạo các nút tăng/giảm số cột
        increaseButton = new JButton("Increase Columns");
        increaseButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		currentDate  = currentDate.plusDays(1);
        		dateString = currentDate.format(formatter);
        		 createChart();
        		
        	}
        });
        decreaseButton = new JButton("Decrease Columns");
        decreaseButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  currentDate = currentDate.minusDays(1);
        		 dateString = currentDate.format(formatter);
        		 createChart();
        		
        	}
        });
        increaseButton.addActionListener(this);
        decreaseButton.addActionListener(this);

        // Tạo panel chứa các nút
        buttonPanel = new JPanel();
        buttonPanel.add(decreaseButton);
        buttonPanel.add(increaseButton);

        // Tạo biểu đồ cột ban đầu
        createChart();

        // Thêm các panel vào JFrame
        add(buttonPanel, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AttendanceRateChart();
    }

    private void createChart() {
    	
    	 db = new DatabaseConnection();
         connection = db.connectToBB();
         dataset = new DefaultCategoryDataset();
        dataset.clear(); // Xóa dữ liệu cũ
        System.out.println("aaaaaaaaaa\taaaaa"+dateString);
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Truy vấn danh sách các bảng điểm danh
            String query = "SELECT tableName FROM attendance_tables";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            // Iterate qua các bảng điểm danh
            while (resultSet.next()) {
                String tableName = resultSet.getString("tableName");

                // Truy vấn tỷ lệ đi học đầy đủ cho từng bảng điểm danh
                String attendanceQuery = "SELECT COUNT(*) AS total, `" + dateString + "` FROM " + tableName + " GROUP BY " + dateString + "";
                
                try {
                    PreparedStatement attendanceStatement = connection.prepareStatement(attendanceQuery);
                    ResultSet attendanceResult = attendanceStatement.executeQuery();

                    if (attendanceResult.next()) {
                        int total = attendanceResult.getInt("total");
                        double presentSum = 0;

                        // Lấy giá trị của cột ở vị trí thứ 2
                        double present = attendanceResult.getDouble(2);
                        presentSum += present;

                        // Tính toán tỷ lệ đi học đầy đủ
                        double attendanceRate = (presentSum / total) * 100;

                        // Thêm dữ liệu cho biểu đồ
                        dataset.addValue(attendanceRate, "Attendance Rate", tableName);
                        originalDate=currentDate;
                        attendanceResult.close();
                        attendanceStatement.close();
                    }
                } catch (SQLException e) {
                	
                	if (currentDate.isAfter(originalDate)) {
                	    JOptionPane.showMessageDialog(null, "There is no data about the next day.");
                	} else if (currentDate.isBefore(originalDate)) {
                	    JOptionPane.showMessageDialog(null, "There is no data about the previous day.");
                	}
                	currentDate=originalDate;
                	dateString = currentDate.format(formatter);
                	
                	
                    // Xử lý ngoại lệ SQLException
                    e.printStackTrace();
                }

               
            }

            // Tạo biểu đồ cột
            JFreeChart chart = ChartFactory.createBarChart(
                    "Attendance Rate by Class",
                    "Class",
                    "Attendance Rate (%)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );
            
            // Tạo ChartPanel để hiển thị biểu đồ
            chartPanel = new ChartPanel(chart);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == increaseButton) {
            if (columnCount < getMaxColumnCount()) {
                columnCount++;
                createChart();
                revalidate();
                repaint();
            }
        } else if (e.getSource() == decreaseButton) {
            if (columnCount > 3) {
                columnCount--;
                createChart();
                revalidate();
                repaint();
            }
        }
    }

    private int getMaxColumnCount() {
        int maxColumnCount = 0;

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Truy vấn thông tin về bảng điểm danh có nhiều cột nhất
            String query = "SELECT MAX(columnCount) AS maxColumnCount FROM attendance_tables";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                maxColumnCount = resultSet.getInt("maxColumnCount");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return maxColumnCount;
    }
}