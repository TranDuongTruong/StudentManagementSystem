package controller.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ClassesManager;
import model.Classroom;
import view.Student.ScheduleView;

public class SechduleCtrl {
	public ScheduleView view;

	public SechduleCtrl(ScheduleView view) {
		super();
		this.view = view;
		view.nextWeekListener(new NextWeekListener());
		view.periviousWeekListener(new PeriviousWeekListener() );
	}
	 private class NextWeekListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	view.currentWeek++;
	        	view.setDataforSechduleTable();
	        }

	       
	    }
	 private class PeriviousWeekListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	view.currentWeek--;
	        	view.setDataforSechduleTable();
	        }

	       
	    }
	
}
