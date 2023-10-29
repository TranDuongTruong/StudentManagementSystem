package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import model.Classroom;
import model.Student;
import model.StudentManager;
import view.ClassesView;

public class ClassesController implements ActionListener {
    StudentManager classes;
    public ClassesView view;
   
    public ClassesController(ClassesView view){
        classes=new StudentManager();
        this.view=view;
        displayClasses();
    }
    
     void displayClasses() {
        DatabaseConnection a= new DatabaseConnection();
        classes=a.retrieveClassesFromDatabase();
        view.model=classes;
        view.displayClassList(classes);
    }
    public void actionPerformed(ActionEvent e) {
        String cm = e.getActionCommand();
        JOptionPane.showMessageDialog(view, "Bạn vừa nhấn vào: "+cm);
        if(cm.equals("Thêm")) {
            this.view.xoaForm();
            this.view.model.setLuachon("Thêm");
        }else if(cm.equals("Lưu")) {
            try {
                this.view.ThemHoacCapNhatLop();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }else if(cm.equals("Cập Nhật")) {
        	this.view.hienthiThongTinLopDaChon();
        }else if(cm.equals("Xoá")) {
        	this.view.ThucHienXoa();
        }else if(cm.equals("Huỷ bỏ")) {
        	this.view.xoaForm();
        	this.view.huyTim();
        } if(cm.equals("Tìm")) {
        	this.view.ThucHienTim();
        }
    }
}