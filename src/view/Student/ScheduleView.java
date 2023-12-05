package view.Student;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.Student.ScheduleCtrl;
import model.Student;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

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
import javax.swing.JLabel;
import javax.swing.JButton;

public class ScheduleView extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable scheduleTable;
    private JTable colorTable;
    JButton btnPeriviousWeek;
    JButton btnNextWeek;
    public int currentWeek=1;
   
    public ScheduleView() {
    	
        setLayout(null);
        setBounds(162, 0, 835, 640);

        // Create the schedule table
        scheduleTable = new JTable();
        scheduleTable.setBounds(10, 63, 600, 300);
      //  add(scheduleTable);
        scheduleTable .setRowHeight(50); 
        // Set table model with column names
        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {" ", " ", " ", " ", " ", " "," "," "}
        );
        scheduleTable.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		" ", " ", " ", " ", " ", " ", " ", " "
        	}
        ));
        scheduleTable.getColumnModel().getColumn(0).setPreferredWidth(80);
        
        Font tableFont = new Font("SansSerif", Font.PLAIN, 16);
        scheduleTable.setFont(tableFont);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(10, 10, 815, 620);
        add(panel);
        panel.setLayout(null);
        panel.add(scheduleTable);
        
        JLabel titleLb = new JLabel("Learning Schedule week 12: November 6, 2023 - November 12, 2023");
        titleLb.setBounds(10, 10, 600, 43);
        
        panel.add(titleLb);
        
         btnNextWeek = new JButton("Next week");
        btnNextWeek.setBounds(638, 63, 132, 32);
        panel.add(btnNextWeek);
        
         btnPeriviousWeek = new JButton("Perivious week");
        btnPeriviousWeek.setBounds(638, 118, 132, 32);
        panel.add(btnPeriviousWeek);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 407, 795, 203);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblTableOfColor = new JLabel("Table of color annotations on the sechdule ");
        lblTableOfColor.setBounds(10, 10, 378, 34);
        panel_1.add(lblTableOfColor);
        
        colorTable = new JTable();
        colorTable.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"Color", "Class Mode Type Name", "Description"},
        		{"Green", "Online Class", null},
        		{"Yellow", "Offline Class", null},
        	},
        	new String[] {
        		"New column", "New column", "New column"
        	}
        ));
        colorTable .setRowHeight(40);
        
        
        colorTable.getColumnModel().getColumn(0).setPreferredWidth(67);
        colorTable.getColumnModel().getColumn(0).setMinWidth(22);
        colorTable.getColumnModel().getColumn(0).setMaxWidth(70);
        colorTable.setBounds(20, 54, 765, 120);
        panel_1.add(colorTable);
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
        		//System.out.println(scheduleData[i][j]);
        	}
        	else {
        		scheduleData[i][0]=hour[i-1];
        	}
        	
        }
        
        
            updateSchedule(scheduleData);
            ScheduleCtrl sechualeCtrl=new ScheduleCtrl(this);
            
            
            
            
            
            
            setVisible(true);
    }
//    public void setValueInTable(int rowS, int columnS, Object data, boolean offline) {
//        scheduleTable.setValueAt(data, rowS, columnS);
//        
//        TableCellRenderer customRenderer = new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, rowS, columnS);
//               // System.out.println(column+"\t"+columnS+"\t"+row+"\t"+rowS);
//                // Kiểm tra xem ô hiện tại có phải là ô được chọn
//                if (row==rowS&&column==columnS) {
//                	
//                	if(offline)
//                    component.setBackground(Color.YELLOW);
//                	else 
//                	component.setBackground(Color.green);
//                } else {
//                    component.setBackground(table.getBackground());
//                }
//                
//                return component;
//            }
//        };
//        
//        
//        
//        // Thiết lập renderer cho ô được chọn
//        scheduleTable.getColumnModel().getColumn(columnS).setCellRenderer(customRenderer);
//        
//        // Thiết lập font size
//        int fontSize = 12; // Điều chỉnh kích thước font tùy ý
//
//        // Thiết lập font cho ô được chọn
//        scheduleTable.setFont(scheduleTable.getFont().deriveFont(Font.PLAIN, fontSize));
//        
//        scheduleTable.repaint();
//    }
    public void setValueInTable(int rowS, int columnS, Object data, boolean offline) {
        scheduleTable.setValueAt(data, rowS, columnS);

        TableCellRenderer customRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, rowS, columnS);
                
                // Kiểm tra xem ô hiện tại có phải là ô được chọn
                if (row == rowS && column == columnS) {
                    if (offline)
                        component.setBackground(Color.YELLOW);
                    else
                        component.setBackground(Color.GREEN);
                } else {
                    component.setBackground(table.getBackground());
                }

                // Định dạng nội dung thành 2 dòng bằng HTML
                String htmlValue = "<html>" + value.toString()
                .replaceFirst(",", "<br>")
                .replace(", ", "<br>")
                .replace(",", "<br>") + "</html>";
                ((JLabel) component).setText(htmlValue);

                return component;
            }
        };

        // Thiết lập renderer cho ô được chọn
        scheduleTable.getColumnModel().getColumn(columnS).setCellRenderer(customRenderer);

        // Thiết lập font size
        int fontSize = 12; // Điều chỉnh kích thước font tùy ý

        // Thiết lập font cho ô được chọn
        scheduleTable.setFont(scheduleTable.getFont().deriveFont(Font.PLAIN, fontSize));

        scheduleTable.repaint();
    }
    public void setDataforSechduleTable() {
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
         	//	System.out.println(scheduleData[i][j]);
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
    public void nextWeekListener(ActionListener listener) {
		  btnNextWeek.addActionListener(listener);
	 }
    public void periviousWeekListener(ActionListener listener) {
    	btnPeriviousWeek.addActionListener(listener);
	 }
 
	
    private String[] getWeekRealTime() {
        LocalDate currentDate = LocalDate.now();

        // Tính toán ngày bắt đầu của tuần hiện tại
        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY).plusWeeks(currentWeek - 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MM");
        String[] weeks = new String[7];

        for (int i = 0; i < 7; i++) {
            LocalDate currentWeekDate = startOfWeek.plusDays(i);
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