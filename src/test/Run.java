package test;

import java.awt.EventQueue;

import controller.Admin.LoginController;
import view.Admin.LoginView;

public class Run {
	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    LoginView frame = new LoginView();
	                    LoginController controller = new LoginController(frame); // Pass the view to the controller
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
}
