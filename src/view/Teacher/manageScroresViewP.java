package view.Teacher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.DatabaseConnection;
import controller.Teacher.StudentController;
import model.Classroom;
import model.Student;

public class manageScroresViewP extends JPanel {
	  private JTextField textField;
	  private JTable table;
	  private static final long serialVersionUID = 1L;
	   Classroom classroom;
	/**
	 * Create the panel.
	 */
	public manageScroresViewP(Classroom classroom) {	
		this.classroom=classroom;
		setLayout(null);
        setBounds(162, 0, 835, 640);

        JLabel label_Student_ID = new JLabel("Student ID");
        label_Student_ID.setFont(new Font("Tahoma", Font.PLAIN, 19));
        label_Student_ID.setBounds(28, 39, 135, 42);
        add(label_Student_ID);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
        textField.setColumns(10);
        textField.setBounds(185, 43, 272, 35);
        add(textField);

        JButton btnTim = new JButton("Search");
        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnTim.setBounds(512, 41, 117, 41);
        add(btnTim);

        JButton btnHuyTim = new JButton("Undo");
        btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnHuyTim.setBounds(649, 41, 117, 41);
        add(btnHuyTim);

        JLabel lblListOfScores = new JLabel("List of scores");
        lblListOfScores.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblListOfScores.setBounds(28, 124, 251, 54);
        add(lblListOfScores);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(28, 212, 738, 214);
        add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        		{null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"Student ID", "Name", "Attendance Score", "Regular Score", "Miderm Score", "Final Score", "Total Score"
        	}
        ));

        scrollPane.setViewportView(table);
	              
	}
}
	
	
	