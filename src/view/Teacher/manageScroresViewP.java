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

	private static final long serialVersionUID = 1L;
	Classroom classroom;
	/**
	 * Create the panel.
	 */
	public manageScroresViewP(Classroom classroom) {	
		this.classroom=classroom;
			 setLayout(null);
	        setBounds(162, 0, 835, 640);
	        
	        JButton button = new JButton("New button");
	        button.setBounds(370, 201, 85, 21);
	        add(button);
	              
	}
}
	
	
	