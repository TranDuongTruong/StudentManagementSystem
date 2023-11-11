package controller.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.w3c.dom.css.Rect;

import com.mysql.cj.xdevapi.Statement;

import controller.DatabaseConnection;
import model.ClassesManager;
import model.Classroom;
import view.Student.ScheduleView;
import java.sql.*;
public class ScheduleCtrl {
	public ScheduleView view;

	public ScheduleCtrl(ScheduleView view) {
		super();
		this.view = view;
		view.nextWeekListener(new NextWeekListener());
		view.periviousWeekListener(new PeriviousWeekListener() );
		retrieveDataFromDatabase();
	}
	 private class NextWeekListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	view.currentWeek++;
	        	view.setDataforSechduleTable();retrieveDataFromDatabase();
	        }

	       
	    }
	 private class PeriviousWeekListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	view.currentWeek--;
	        	view.setDataforSechduleTable();
	        }

	       
	    }
	 public void SetSchedule() {
		 Object[] data = retrieveDataFromDatabase();
		  //  JTable scheduleTable = new JTable(data, weeksInRealTime);
		 
		 
	 }
	 private Object[] retrieveDataFromDatabase() {
		    Object[] data = new Object[7][9]; // Kích thước của mảng dữ liệu tương ứng với JTable "schedule"
		    DatabaseConnection db = new DatabaseConnection();
		    Connection con = db.connectToBB();

		    try {
		        // Thực hiện truy vấn để lấy dữ liệu từ bảng "schedule"
		        String query = "SELECT * FROM schedule";
		        java.sql.Statement stmt =  con.createStatement();
		        ResultSet resultSet = ((java.sql.Statement) stmt).executeQuery(query);
		        LocalDate start,end;
		        // Lặp qua các bản ghi trong ResultSet và lưu dữ liệu vào mảng data
		        
		        while (resultSet.next()) {
		        	
		            String classCode = resultSet.getString("classCode");
		            String dayOfWeek = resultSet.getString("dayOfWeek");
		            
		           
		            String startTime = resultSet.getTime("startTime").toString();
		           
		            String endTime = resultSet.getTime("endTime").toString();
		            
		            
		            Date startDay=resultSet.getDate("startDate");
		            String startDate = startDay.toString();
		            Date endDay= resultSet.getDate("endDate");
		            String endDate = endDay.toString();
		            
		            String roomNumber = resultSet.getString("roomNumber");
		           
		           // System.out.println("Date:"+startDate+"\t"+startDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()+"\n");
		          
		            if(checkDateTime(startDay,endDay))
		            {
		            
		            int row = findRowIndex(startTime);
		            // Tìm vị trí tương ứng trong mảng data dựa trên classCode và dayOfWeek
		            int collum = findColumnIndex(dayOfWeek); // Hàm findColumnIndex để tìm vị trí cột tương ứng với ngày trong tuần
		           
		            
		            System.out.println("ccccl: "+collum+"\t"+row+"\t"+dayOfWeek+startTime);
		            
		            List<Object> newData = new ArrayList<>(Arrays.asList(classCode, dayOfWeek, startTime, endTime, startDate, endDate, roomNumber));
		            
		            this.view.setValueInTable(row,collum,newData);
		         
		           // System.out.println(data);
		            }
		            
		        }

		        // Đóng kết nối và các đối tượng liên quan
		        resultSet.close();
		        stmt.close();
		        con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return data;
		}
	 private boolean checkDateTime(Date start, Date end) {
		    LocalDate currentDate = LocalDate.now();
		    LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY).plusWeeks(this.view.currentWeek - 1);
		    LocalDate endOfWeek = startOfWeek.with(DayOfWeek.SUNDAY);
		    
		    
		   LocalDate startDate = new Timestamp(start.getTime()).toLocalDateTime().toLocalDate();
		  
	        LocalDate endDate = new Timestamp(end.getTime()).toLocalDateTime().toLocalDate();
	        
		 
		    
		    boolean isStartDateValid = startDate.isBefore(startOfWeek) && endDate.isAfter(startOfWeek);		 	    
		    System.out.println(isStartDateValid );
		    return isStartDateValid ;
		}
	 private int findColumnIndex(String dayOfWeek) {
		    int columnIndex = 0; // Giá trị mặc định nếu không tìm thấy vị trí cột

		    switch (dayOfWeek) {
		        case "Mon":
		            columnIndex = 1;
		            break;
		        case "Tue":
		            columnIndex = 2;
		            break;
		        case "Wed":
		            columnIndex = 3;
		            break;
		        case "Thu":
		            columnIndex = 4;
		            break;
		        case "Fri":
		            columnIndex = 5;
		            break;
		        case "Sat":
		            columnIndex = 6;
		            break;
		        case "Sun":
		            columnIndex = 7;
		            break;
		        default:
		            // Xử lý trường hợp không hợp lệ nếu cần thiết
		            break;
		    }

		    return columnIndex;
		}
	 private int findRowIndex(String startTime) {
		    int rowIndex = 0;
		    switch (startTime) {
		        case "07:00:00":
		            rowIndex = 1;
		            break;
		        case "09:00:00":
		            rowIndex = 2;
		            break;
		        case "13:00:00":
		            rowIndex = 3;
		            break;
		        case "15:00:00":
		            rowIndex = 4;
		            break;
		        case "18:00:00":
		            rowIndex = 5;
		            break;
		    }
		    return rowIndex;
		}
}
