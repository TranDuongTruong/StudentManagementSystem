package view.Student;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Student.ExaminationController;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;

public class ExaminationView extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private ExaminationController controller;

    /**
     * Create the panel.
     */
    public ExaminationView() {
    	ExaminationController controller = new ExaminationController(this);
        setLayout(null);
        this.setBounds(162, 0, 835, 640);

        JLabel lblDanhSchCc = new JLabel("List of classes");
        lblDanhSchCc.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSchCc.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDanhSchCc.setBounds(54, 62, 177, 41);
        add(lblDanhSchCc);

        JScrollPane scrollPane = new JScrollPane((Component) null);
        scrollPane.setBounds(85, 144, 663, 75);
        add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ClassCode", "ClassName", "Status"})  		
        {
            // isCellEditable không cho phép chỉnh sửa
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        } 		
       );		

        table.getColumnModel().getColumn(1).setPreferredWidth(83);
        scrollPane.setViewportView(table);
        
        // Gọi đến Rule -> Quiz với mã lớp tương ứng
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Get the selected classCode from the selected row
                        String classCode = (String) table.getValueAt(selectedRow, 0);

                        // Kiểm tra xem có bài kiểm tra nào không
                        if (controller.hasExams(classCode)) {
                            new Quiz(classCode);
                            // Đóng studentaccountView
                            StudentAccountMainView studentAccountView = (StudentAccountMainView) SwingUtilities.getWindowAncestor(ExaminationView.this);
                            studentAccountView.dispose();
                        } else {
                            // Hiển thị thông báo nếu không có bài kiểm tra
                            JOptionPane.showMessageDialog(null, "There are no exams available for this class.",
                                    "No Exams", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        // Call the controller method to load data from the database
        controller.loadDataFromDatabase();
    }

    public JTable getTable() {
        return table;
    }
}
