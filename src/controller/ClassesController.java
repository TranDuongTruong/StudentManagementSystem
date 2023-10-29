package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import model.Classroom;
import model.Student;
import view.ClassesView;

public class ClassesController implements ActionListener {
    List <Classroom> classes;
    public ClassesView view;
   
    public ClassesController(ClassesView view){
        classes=new ArrayList<Classroom>();
        this.view=view;        
        displayClasses();
    }
    
     void displayClasses() {
        DatabaseConnection a= new DatabaseConnection();
        classes=a.retrieveClassesFromDatabase();
        view.displayClassList(classes);
    }
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
        if(cm.equals("Thêm")) {
            this.view.xoaForm();
            this.view.model.setLuaChon("Thêm");
        }else if(cm.equals("Lưu")) {
            try {
                //Get dữ liệu
                String classCode = this.view.textField_MaLop.getText();
                String className = this.view.textField_TenLop.getText();
                int numOfCurentStudents = Integer.valueOf(this.view.textField_SoHSHT.getText());
                int maximumNumOfStudents =Integer.valueOf(this.view.textField_SoHSTD.getText());;
                
                Classroom lop= new Classroom(classCode, className, numOfCurentStudents, maximumNumOfStudents);
                if (this.view.model.getLuaChon().equals("") || this.view.model.getLuaChon().equals("Thêm")) {
                    this.view.ThemLop(lop);
                } else if (this.view.model.getLuaChon().equals("Cập nhật")) {
                    this.view.CapNhatLop(lop);
                } 
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
    }
    
    
}