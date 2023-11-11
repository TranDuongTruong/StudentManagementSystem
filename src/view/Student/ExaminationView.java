package view.Student;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

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
        scrollPane.setBounds(85, 144, 663, 157);
        add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ClassCode", "ClassName" })  		
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
        

        // Call the controller method to load data from the database
        controller.loadDataFromDatabase();
    }

    public JTable getTable() {
        return table;
    }
}
