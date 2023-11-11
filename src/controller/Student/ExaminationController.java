package controller.Student;

import view.Student.ExaminationView;
import javax.swing.table.DefaultTableModel;

import controller.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExaminationController {

    private ExaminationView view;

    public ExaminationController(ExaminationView view) {
        this.view = view;
    }

    public void loadDataFromDatabase() {

        try {
            // Establish a connection
            Connection connection = DatabaseConnection.connectToBB(); // Get the database connection

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query to get data from the database
            ResultSet resultSet = statement.executeQuery("SELECT * FROM classroom");

            // Create a DefaultTableModel to hold the data
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            model.setRowCount(0); // Clear existing data

            // Populate the table with data from the result set
            while (resultSet.next()) {
                String classCode = resultSet.getString("classCode");
                String className = resultSet.getString("className");

                // Add a new row to the table model
                model.addRow(new Object[] { classCode, className });
            }

            // Close the connections
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
