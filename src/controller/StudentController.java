package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;

import com.mysql.cj.result.LocalDateTimeValueFactory;

import view.StudentView;

public class StudentController implements ActionListener{
	public StudentView view;
	
	 
	public StudentController(StudentView view) {
		super();
		this.view = view;
	}


	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		JOptionPane.showMessageDialog(view, "Ban vua nhan vao: "+cm);
		if(cm.equals("Add"));{
			this.view.xoaForm();
			this.view.model.setLuachon("Add");
		}if(cm.equals("save")) {
			try {
				int studentID = Integer.valueOf(this.view.textField_ID.getText());
				String name =this.view.textField_name.getText();
				String address =this.view.comboBox_queQuan.getSelectedIndex()+"";
				//LocalDate dob= new LocalDate(this.view.textField_dob.getText());
				
				
					
				//		    private LocalDate  dob;
				//		    private String address;
				//		    private boolean gender;// male: true; female: false
				//		    private String phoneNumber;
				//		    private int creditsCompleted;
				//		    private int creditsOwed;
				if (this.view.model.getLuachon().equals("") || this.view.model.getLuachon().equals("Add")) {

				} else if (this.view.model.getLuachon().equals("Update")) {

				} 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}if(cm.equals("")) {
			
		}
		
	}

}
