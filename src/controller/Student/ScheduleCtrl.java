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
import controller.Admin.LoginController;
import model.ClassesManager;
import model.Classroom;
import model.Student;
import view.Student.ScheduleView;
import java.sql.*;
public class ScheduleCtrl {
	public ScheduleView view;
	public int studentID;
	String[] registeredClassCodes = {"C104","C105"};
	public ScheduleCtrl(ScheduleView view) {
		super();
		this.view = view;
		
		studentID= LoginController.studentId;
		//System.out.println("aaaaaaaaaaaaaaaaaaaa"+stu.getStudentID());
		registeredClassCodes=getRegisteredClassCodes();
		view.nextWeekListener(new NextWeekListener());
		view.periviousWeekListener(new PeriviousWeekListener() );
		
		retrieveDataFromDatabase(registeredClassCodes);
	}
	public String[] getRegisteredClassCodes() {
	    List<String> registeredClassCodes = new ArrayList<>();
	    DatabaseConnection db = new DatabaseConnection();
	    Connection con = db.connectToBB();

	    try {
	        // Thực hiện truy vấn để lấy các mã lớp đã đăng ký của học sinh từ bảng "studentclassroom"
	        String query = "SELECT classCode FROM studentclassroom WHERE studentID = ?";
	        PreparedStatement pstmt = con.prepareStatement(query);
	        pstmt.setString(1, Integer.toString(studentID));
	        ResultSet resultSet = pstmt.executeQuery();

	        // Lặp qua các bản ghi trong ResultSet và lưu các mã lớp vào danh sách registeredClassCodes
	        while (resultSet.next()) {
	            String classCode = resultSet.getString("classCode");
	            registeredClassCodes.add(classCode);
	        }

	        // Đóng kết nối và các đối tượng liên quan
	        resultSet.close();
	        pstmt.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    // Chuyển đổi danh sách registeredClassCodes thành mảng String[]
	    String[] classCodeArray = registeredClassCodes.toArray(new String[registeredClassCodes.size()]);

	    return classCodeArray;
	}
	 private class NextWeekListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	view.currentWeek++;
	        	view.setDataforSechduleTable();retrieveDataFromDatabase(registeredClassCodes);
	        }

	       
	    }
	 private class PeriviousWeekListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	        	view.currentWeek--;
	        	view.setDataforSechduleTable();retrieveDataFromDatabase(registeredClassCodes);
	        }

	       
	    }
	 public void SetSchedule() {
		 Object[] data = retrieveDataFromDatabase(registeredClassCodes);
		  //  JTable scheduleTable = new JTable(data, weeksInRealTime);
		 
		 
	 }
	
	 private Object[][] retrieveDataFromDatabase(String[] registeredClassCodes) {
		    Object[][] data = new Object[7][9]; // Kích thước của mảng dữ liệu tương ứng với JTable "schedule"
		    DatabaseConnection db = new DatabaseConnection();
		    Connection con = db.connectToBB();
		    
		    try {
		    	String query = "SELECT * FROM schedule WHERE classCode IN (";

		    	// Tạo phần danh sách các mã lớp trong câu truy vấn
		    	for (int i = 0; i < registeredClassCodes.length; i++) {
		    	    query += "'" + registeredClassCodes[i] + "'";
		    	    if (i < registeredClassCodes.length - 1) {
		    	        query += ",";
		    	    }
		    	}

		    	query += ")";
		    	
		    	
		        java.sql.Statement stmt =  con.createStatement();
		        ResultSet resultSet = ((java.sql.Statement) stmt).executeQuery(query);

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
		            System.out.println(classCode);
		            
		            
		            if(checkDateTime(startDay,endDay))
		            {
		            int row = findRowIndex(startTime);
		            int column = findColumnIndex(dayOfWeek);
		            
		            data[row][column] = new Object[] { classCode, dayOfWeek, startTime, endTime, startDate, endDate, roomNumber };
		           
		            List<Object> newData = new ArrayList<>(Arrays.asList(classCode,startDate, endDate, roomNumber));
		          //  System.out.println("dAY: "+startDate);
		            this.view.setValueInTable(row,column,newData);
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
		    boolean isStartDateValid = startDate.isBefore(startOfWeek) && endDate.isAfter(startOfWeek)||startDate.isAfter(startOfWeek)&&startDate.isBefore(endOfWeek);	
		    System.out.println(startOfWeek+"\t"+startDate+"\t"+isStartDateValid );
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
