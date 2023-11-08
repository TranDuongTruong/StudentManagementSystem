package view.Student;

import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScheduleView extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable scheduleTable;

  
    public ScheduleView() {
        setLayout(null);
        setBounds(162, 0, 835, 640);

//        JPanel contentPane = new JPanel();
//        contentPane.setBounds(0, 0, 835, 640);
//        
//
//        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        add(contentPane);
//        contentPane.setLayout(null);

        // Create the schedule table
        scheduleTable = new JTable();
        scheduleTable.setBounds(29, 211, 782, 371);
        add(scheduleTable);
        
        // Set table model with column names
        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {" ", " ", " ", " ", " ", " "," "," "}
        );
        scheduleTable.setModel(tableModel);
        Font tableFont = new Font("SansSerif", Font.PLAIN, 16);
        scheduleTable.setFont(tableFont);
        // Set custom cell renderer for background color
        scheduleTable.setDefaultRenderer(Object.class, new CustomCellRenderer());
        // Update the first row with week days
        tableModel.addRow(new Object[tableModel.getColumnCount()]);
        // Tạo một đối tượng renderer tùy chỉnh để căn giữa dữ liệu trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        scheduleTable.setDefaultRenderer(Object.class, centerRenderer);
        
        // Thiết lập kích thước font lớn cho dữ liệu trong bảng
        Font dataFont = new Font("SansSerif", Font.PLAIN, 10);
        scheduleTable.setDefaultRenderer(Object.class, new CustomRenderer(dataFont, centerRenderer));
   
   
        String[] weeksInRealTime=getWeekRealTime();
        
        String[] hour= {"7AM","9AM","1PM","3PM","6PM"};
        Object[][] scheduleData = {
                {" ", " ", " ", " ", " ", " "," "," "},
                {" ", " ", " ", " ", " ", " "," "," "},
                {" ", " ", " ", " ", " ", " "," "," "},
                {" ", " ", " ", " ", " ", " "," "," "},
                {" ", " ", " ", " ", " ", " "," "," "},
                {" ", " ", " ", " ", " ", " "," "," "}
            };
        
      
        for(int i=0;i<6;i++) {
        	if(i==0)
        	for(int j=1;j<8;j++) {
        		scheduleData[i][j]=weeksInRealTime[j-1];
        		System.out.println(scheduleData[i][j]);
        	}
        	else {
        		scheduleData[i][0]=hour[i-1];
        	}
        	
        }
        
        
            updateSchedule(scheduleData);
            setVisible(true);
    }

    public void updateSchedule(Object[][] data) {
        DefaultTableModel tableModel = (DefaultTableModel) scheduleTable.getModel();
        tableModel.setDataVector(data, new String[] {" ", " ", " ", " ", " ", " "," "," "});
    }

    private class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Set background color for exam schedule cells
            if (row > 0 && column > 0 && value.toString().startsWith("Lịch thi")) {
                cell.setBackground(Color.YELLOW);
            } else {
                cell.setBackground(table.getBackground());
            }

            return cell;
        }
    }

    private String[]  getWeekRealTime() {
    	 LocalDate currentDate = LocalDate.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM");
         String[] weeks = new String[7];

         for (int i = 0; i < 7; i++) {
             LocalDate currentWeekDate = currentDate.with(DayOfWeek.of(i + 1));
             String weekDay = currentWeekDate.format(formatter);
             weeks[i] = getWeekDayName(i + 1) + " " + weekDay;
         }

        return weeks;
    }
    private String getWeekDayName(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return "";
            case 2:
                return "";
            case 3:
                return "";
            case 4:
                return "";
            case 5:
                return "";
            case 6:
                return "";
            case 7:
                return "";
            default:
                return "";
        }
    }
    class CustomRenderer extends DefaultTableCellRenderer {
        private Font font;
        private DefaultTableCellRenderer defaultRenderer;

        public CustomRenderer(Font font, DefaultTableCellRenderer defaultRenderer) {
            this.font = font;
            this.defaultRenderer = defaultRenderer;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            component.setFont(font);
            return component;
        }
    }
}