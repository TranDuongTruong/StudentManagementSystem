package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseConnection;
import view.Admin.AdminHomeView;

import view.Admin.ShowUserAccountP;
public class DisplayUserAccountsController implements ActionListener {

    private ShowUserAccountP view;
  
    public DisplayUserAccountsController(ShowUserAccountP view) {
    	 this.view = view;
       //  this.view.setController(this);

         // Load data from the database in the constructor
         loadDataFromDatabase();
    }

    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == view.getBackButton()) {
//            // Add logic to go back to the admin home view here
//            AdminHomeView adminhomeView = new AdminHomeView();
//            adminhomeView.setVisible(true);
//           // view.dispose();
//        }
    }
    

    public void loadDataFromDatabase() {
        try {
            Connection connection = DatabaseConnection.connectToBB(); 
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts");
            ResultSet resultSet = statement.executeQuery();

            // Process the result set and populate the JTable
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (resultSet.next()) {
                Vector<Object> row = new Vector<Object>();
                row.add(resultSet.getInt("id"));
                row.add(resultSet.getString("role"));
                row.add(resultSet.getString("email"));
                row.add(resultSet.getString("password"));
                row.add(resultSet.getString("name"));

                // Kiểm tra nếu studentID là null, thì thêm giá trị trống (null)
                if (resultSet.getObject("studentID") == null) {
                    row.add(null);
                } else {
                    row.add(resultSet.getInt("studentID"));
                }

                data.add(row);
            }

            Vector<String> columnNames = new Vector<String>();
            columnNames.add("ID");
            columnNames.add("Role");
            columnNames.add("Email");
            columnNames.add("Password");
            columnNames.add("Name");
            columnNames.add("Student ID"); // Thêm tên cột "Student ID"

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            view.getTable().setModel(model);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayUserView() {
    	view.setVisible(true);
    }
}
