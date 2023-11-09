package test;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
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

public class ScheduleFrame extends JFrame {
    private JPanel contentPane;
    private JTable scheduleTable;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ScheduleFrame frame = new ScheduleFrame();
                    Object[][] scheduleData = {
                        {"Ngày Tháng Năm", "7h", "9h", "13h", "15h", "18h"},
                        {"Thứ Hai", "", "Lịch thi 1", "", "", ""},
                        {"Thứ Ba", "", "", "", "Lịch thi 2", ""},
                        //...
                    };
                    frame.updateSchedule(scheduleData);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ScheduleFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Create the schedule table
        scheduleTable = new JTable();
        scheduleTable.setBounds(10, 11, 564, 339);
        contentPane.add(scheduleTable);

        // Set table model with column names
        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"Ngày Tháng Năm", "7h", "9h", "13h", "15h", "18h"}
        );
        scheduleTable.setModel(tableModel);

        // Set custom cell renderer for background color
        scheduleTable.setDefaultRenderer(Object.class, new CustomCellRenderer());

        // Set weeks array
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String[] weeks = new String[7];
        for (int i = 0; i < 7; i++) {
            LocalDate currentWeekDate = currentDate.with(DayOfWeek.of(i + 1));
            String weekDay = currentWeekDate.format(formatter);
            weeks[i] = getWeekDayName(i + 1) + " " + weekDay;
        }

        // Update the first row with week days
        for (int i = 0; i < 6; i++) {
            if (weeks.length > 0) {
                tableModel.setValueAt(weeks[i], 0, i + 1);
            }
        }
        if (weeks.length > 6) {
            tableModel.setValueAt(weeks[6], 0, 5);
        }

    }

    public void updateSchedule(Object[][] data) {
        DefaultTableModel tableModel = (DefaultTableModel) scheduleTable.getModel();
        tableModel.setDataVector(data, new String[] {"Ngày Tháng Năm", "7h", "9h", "13h", "15h", "18h"});
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

    private String getWeekDayName(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return "CN";
            case 2:
                return "T2";
            case 3:
                return "T3";
            case 4:
                return "T4";
            case 5:
                return "T5";
            case 6:
                return "T6";
            case 7:
                return "T7";
            default:
                return "";
        }
    }
}