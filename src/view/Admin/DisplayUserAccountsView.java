package view.Admin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Admin.DisplayUserAccountsController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class DisplayUserAccountsView extends JFrame {
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	DisplayUserAccountsView frame = new DisplayUserAccountsView();
                	DisplayUserAccountsController controller = new DisplayUserAccountsController(frame); // Pass the view to the controller
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JPanel contentPane;
    private JTable table;
    private JButton backButton;
    
    public DisplayUserAccountsView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 696, 425);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblStudents = new JLabel("Admin: Show Users");
        lblStudents.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblStudents.setBounds(10, 0, 322, 30);
        contentPane.add(lblStudents);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 31, 680, 13);
        contentPane.add(separator);

        JScrollPane scrollPane = new JScrollPane((Component) null);
        scrollPane.setBounds(0, 61, 680, 139);
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

        backButton = new JButton("Back");
        backButton.setBounds(241, 284, 103, 30);
        contentPane.add(backButton);
        ImageIcon backIcon = new ImageIcon(DisplayUserAccountsView.class.getResource("/Assert/admin/back.png"));
        backButton.setIcon(backIcon);
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBackButton() {
        return backButton;
    }
    
 
    public void setController(DisplayUserAccountsController controller) {
        backButton.addActionListener(controller);
    }
   
}
