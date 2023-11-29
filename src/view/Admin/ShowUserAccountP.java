package view.Admin;

import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Admin.DisplayUserAccountsController;

public class ShowUserAccountP extends JPanel {

	private static final long serialVersionUID = 1L;

	  private JPanel contentPane;
	    private JTable table;/**
	 * Create the panel.
	 */
	public ShowUserAccountP() {
		setBounds(162, 0, 835, 640);
		setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBounds(100, 100, 568, 414);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(contentPane);
        contentPane.setLayout(null);

        JLabel lblStudents = new JLabel("Admin: Show Users");
        lblStudents.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblStudents.setBounds(10, 0, 322, 30);
        contentPane.add(lblStudents);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 31, 680, 13);
        contentPane.add(separator);

        JScrollPane scrollPane = new JScrollPane((Component) null);
        scrollPane.setBounds(0, 61, 680, 353);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        		{null, null, null, null, null, null},
        	},
        	new String[] {
        		"ID", "Role", "Email", "Password", "Name", "Student ID"
        	}
        ));
        scrollPane.setViewportView(table);
        ImageIcon backIcon = new ImageIcon(ShowUserAccountP.class.getResource("/Assert/admin/back.png"));
    }

    public JTable getTable() {
        return table;
    }

//    public JButton getBackButton() {
//        return backButton;
//    }
//    
// 
//    public void setController(DisplayUserAccountsController controller) {
//        backButton.addActionListener(controller);
//    }
   
}
