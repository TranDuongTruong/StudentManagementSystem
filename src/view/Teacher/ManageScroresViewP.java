package view.Teacher;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import controller.DatabaseConnection;
import model.Classroom;

public class ManageScroresViewP extends JPanel {
    private JTable table;
    private static final long serialVersionUID = 1L;
    private JTextField textFieldAttendanceScore;
    private JTextField textFieldRegularScore;
    private JTextField textFieldMidtermScore;
    private JTextField textFieldFinalScore;
    private JButton btnSave;
    private Classroom classroom;
    private String classCode;
    private JTextField textFieldTotalScore;
    private JTextField textFieldStudentName;
    private JTextField textFieldStudentID;
    private JButton btnExportToExcel; 
    private JTextField searchInp;


    /**
     * Create the panel.
     */
    public ManageScroresViewP(Classroom classroom, String classCode) {
        this.classroom = classroom;
        this.classCode = classCode;
        setLayout(null);
        setBounds(162, 0, 835, 640);
        setBorder(new LineBorder(new Color(255, 0, 0), 2));
        setBackground(new Color(255, 255, 255));

        JLabel lblListOfScores = new JLabel("List of scores");
        lblListOfScores.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblListOfScores.setBounds(28, 124, 251, 54);
        add(lblListOfScores);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(28, 212, 738, 214);
		scrollPane.setViewportBorder(new LineBorder(new Color(255, 0, 0)));
        add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                },
                new String[]{
                        "Student ID", "Name", "Attendance Score", "Regular Score", "Midterm Score", "Final Score", "Total Score"
                }
        ));
        table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(255, 0, 0), new Color(255, 0, 0), new Color(255, 0, 0), new Color(255, 0, 0)));


        scrollPane.setViewportView(table);

       

        // Trong phương thức khởi tạo của lớp ManageScroresViewP
        JLabel lblAttendanceScore = new JLabel("Attendance Score:");
        lblAttendanceScore.setBounds(268, 490, 150, 20);
        add(lblAttendanceScore);

        JLabel lblRegularScore = new JLabel("Regular Score:");
        lblRegularScore.setBounds(388, 490, 150, 20);
        add(lblRegularScore);

        JLabel lblMidtermScore = new JLabel("Midterm Score:");
        lblMidtermScore.setBounds(508, 490, 150, 20);
        add(lblMidtermScore);

        JLabel lblFinalScore = new JLabel("Final Score:");
        lblFinalScore.setBounds(628, 490, 150, 20);
        add(lblFinalScore);

        textFieldAttendanceScore = new JTextField();
        textFieldAttendanceScore.setBounds(268, 515, 100, 25);
        add(textFieldAttendanceScore);

        textFieldRegularScore = new JTextField();
        textFieldRegularScore.setBounds(388, 515, 100, 25);
        add(textFieldRegularScore);

        textFieldMidtermScore = new JTextField();
        textFieldMidtermScore.setBounds(508, 515, 100, 25);
        add(textFieldMidtermScore);

        textFieldFinalScore = new JTextField();
        textFieldFinalScore.setBounds(628, 515, 100, 25);
        add(textFieldFinalScore);

        JLabel lblTotalScore = new JLabel("Total Score:");
        lblTotalScore.setBounds(747, 490, 150, 20);
        add(lblTotalScore);

        textFieldTotalScore = new JTextField();
        textFieldTotalScore.setBounds(747, 515, 84, 25);
        textFieldTotalScore.setEditable(false); // Không cho phép chỉnh sửa
        add(textFieldTotalScore);
        
        JLabel lblStudentsScore = new JLabel("Student's score");
        lblStudentsScore.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblStudentsScore.setBounds(28, 437, 251, 42);
        add(lblStudentsScore);
        
        JLabel lblStudentID = new JLabel("Student ID:");
        lblStudentID.setBounds(27, 490, 150, 20);
        add(lblStudentID);
        
        JLabel lblStudentName = new JLabel("Name:");
        lblStudentName.setBounds(147, 490, 150, 20);
        add(lblStudentName);
        
        textFieldStudentName = new JTextField();
        textFieldStudentName.setEditable(false);
        textFieldStudentName.setBounds(147, 515, 100, 25);
        add(textFieldStudentName);
        
        textFieldStudentID = new JTextField();
        textFieldStudentID.setEditable(false);
        textFieldStudentID.setBounds(27, 515, 100, 25);
        add(textFieldStudentID);
        
        JButton btnUpdateButton = new JButton("Update");
        btnUpdateButton.setBounds(111, 568, 146, 42);
        add(btnUpdateButton);
        btnUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedViewRow = table.getSelectedRow();
                
                if (selectedViewRow != -1) {
                    // Convert the selected view row to model row
                    int selectedModelRow = table.convertRowIndexToModel(selectedViewRow);

                    // Get data from the model using the converted row index
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    textFieldAttendanceScore.setText(model.getValueAt(selectedModelRow, 2).toString());
                    textFieldRegularScore.setText(model.getValueAt(selectedModelRow, 3).toString());
                    textFieldMidtermScore.setText(model.getValueAt(selectedModelRow, 4).toString());
                    textFieldFinalScore.setText(model.getValueAt(selectedModelRow, 5).toString());
                    textFieldStudentID.setText(model.getValueAt(selectedModelRow, 0).toString());
                    textFieldStudentName.setText(model.getValueAt(selectedModelRow, 1).toString());
                    textFieldTotalScore.setText(model.getValueAt(selectedModelRow, 6).toString());
                } else {
                    JOptionPane.showMessageDialog(ManageScroresViewP.this, "Please select a row to update.");
                }
            }
        });


        btnSave = new JButton("Save");
        btnSave.setBounds(272, 568, 146, 42);
        add(btnSave);
        
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateScores();
                fetchDataFromClassroom(classCode);
            }
        });

     // Add a button for exporting to Excel
        btnExportToExcel = new JButton("Export to Excel");
        btnExportToExcel.setBounds(428, 568, 146, 42);
        add(btnExportToExcel);
        
        JLabel label_Student_ID = new JLabel("Student ID");
        label_Student_ID.setFont(new Font("Tahoma", Font.PLAIN, 19));
        label_Student_ID.setBounds(28, 71, 135, 42);
        add(label_Student_ID);
        
        searchInp = new JTextField();
        searchInp.setFont(new Font("Tahoma", Font.PLAIN, 19));
        searchInp.setColumns(10);
        searchInp.setBounds(173, 75, 272, 35);
        add(searchInp);
        
        JButton btnTim = new JButton("Search");
        btnTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnTim.setBounds(491, 73, 117, 41);
        add(btnTim);
        
        JButton btnHuyTim = new JButton("Undo");
        btnHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnHuyTim.setBounds(634, 73, 117, 41);
        add(btnHuyTim);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(28, 128, 738, 2);
        add(separator_1);
        
        // Add ActionListener for the export button
        btnExportToExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToExcel();
            }
        });
     // Add ActionListener for the search button
        btnTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentIDInput = searchInp.getText();
                if (!studentIDInput.isEmpty()) {
                    // Perform search
                    searchByStudentID(studentIDInput);
                } else {
                    JOptionPane.showMessageDialog(ManageScroresViewP.this, "Please enter a Student ID to search.");
                }
            }
        });

     // Add ActionListener for the undo button
        btnHuyTim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset search
                resetSearch();
            }
        });
        // Create the "Import from Excel" button
        JButton btnImportFromExcel = new JButton("Import from Excel");
        btnImportFromExcel.setBounds(584, 568, 146, 42);
        add(btnImportFromExcel);

        // Add an ActionListener for the import button
        btnImportFromExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importFromExcel();
            }
        });
        
        // Fetch data from the database based on the classCode
        fetchDataFromClassroom(classCode);

    }
    private void resetSearch() {
        // Clear the search input
        searchInp.setText("");
        
        // Reset the row sorter to display the full list
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(new TableRowSorter<>(model));
    }
    private void searchByStudentID(String studentIDInput) {
        // Check if the entered student ID contains only numerical digits
        if (!studentIDInput.matches("\\d+")) {
            JOptionPane.showMessageDialog(ManageScroresViewP.this, "Invalid Student ID format. Please enter a valid numerical Student ID.");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> filter;
        try {
            // Filter based on the entered student ID
            filter = RowFilter.regexFilter(studentIDInput, 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(filter);

        // Check if any rows are displayed after filtering
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(ManageScroresViewP.this, "No records found for Student ID: " + studentIDInput);
            // Reset the search
            resetSearch();
        }
    }


    private void fetchDataFromClassroom(String classCode) {
        try {
            // Establish database connection (update DatabaseConnection class if needed)
            Connection connection = DatabaseConnection.connectToBB();

            // Prepare SQL query
            String query = "SELECT * FROM scores WHERE classCode = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, classCode);

                // Execute query and populate the JTable
                ResultSet resultSet = pstmt.executeQuery();
                populateTable(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }
    }

    private void populateTable(ResultSet resultSet) throws SQLException {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable except for the "Attendance Score," "Regular Score," "Midterm Score," and "Final Score" columns
                return false;
            }
        };
        model.setColumnIdentifiers(new Object[]{"Student ID", "Name", "Attendance Score", "Regular Score", "Midterm Score", "Final Score", "Total Score"});

        while (resultSet.next()) {
            model.addRow(new Object[]{
                    resultSet.getInt("studentID"),
                    getStudentNameFromDatabase(resultSet.getInt("studentID")),
                    resultSet.getFloat("attendanceScore"),
                    resultSet.getFloat("regularScore"),
                    resultSet.getFloat("midtermScore"),
                    resultSet.getFloat("finalScore"),
                    resultSet.getString("totalScore")
            });
        }

        table.setModel(model);
    }

    public static String getStudentNameFromDatabase(int studentID) {
        String studentName = "";

        try {
            // Establish database connection
            Connection connection = DatabaseConnection.connectToBB();

            // Prepare SQL query
            String query = "SELECT name FROM student WHERE studentID = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, studentID);

                // Execute query
                ResultSet resultSet = pstmt.executeQuery();

                // Check if a result is returned
                if (resultSet.next()) {
                    studentName = resultSet.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions as needed
        }

        return studentName;
    }
    private void clearTextField() {
    	 // Xóa dữ liệu từ các JTextField
        textFieldAttendanceScore.setText("");
        textFieldRegularScore.setText("");
        textFieldMidtermScore.setText("");
        textFieldFinalScore.setText("");
        textFieldStudentID.setText("");
        textFieldStudentName.setText("");
        textFieldTotalScore.setText("");

    }

    private void handleUpdateScores() {
        // Lấy thông tin từ text fields
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            try {
                // Kiểm tra xem dữ liệu nhập vào các ô textField có phải là số từ 0 đến 10 không
                float attendanceScore = validateAndParseFloat(textFieldAttendanceScore.getText());
                float regularScore = validateAndParseFloat(textFieldRegularScore.getText());
                float midtermScore = validateAndParseFloat(textFieldMidtermScore.getText());
                float finalScore = validateAndParseFloat(textFieldFinalScore.getText());

                if (!isValidScore(attendanceScore) || !isValidScore(regularScore) ||
                    !isValidScore(midtermScore) || !isValidScore(finalScore)) {
                    JOptionPane.showMessageDialog(this, "Invalid score! Please enter a number between 0 and 10.");
                    
                    // Xóa dữ liệu từ các JTextField nếu số không hợp lệ
                    clearTextField();
                    
                    return;
                }

                int studentID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                String classCode = this.classCode;

                // Gọi hàm cập nhật điểm trong cơ sở dữ liệu
                boolean success = updateScoresInDatabase(studentID, classCode, attendanceScore, regularScore, midtermScore, finalScore);
                
                // Xóa dữ liệu từ các JTextField
                clearTextField();

                if (success) {
                    // Hiển thị thông báo cập nhật thành công hoặc thực hiện các hành động cần thiết
                    JOptionPane.showMessageDialog(this, "Scores updated successfully");
                } else {
                    // Hiển thị thông báo lỗi hoặc thực hiện các hành động cần thiết
                    JOptionPane.showMessageDialog(this, "Error updating scores");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric values for scores.");
                
                // Xóa dữ liệu từ các JTextField nếu số không hợp lệ
                clearTextField();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
        }
    }


    // Hàm kiểm tra và chuyển đổi chuỗi thành số, trả về -1 nếu chuỗi không hợp lệ
    private float validateAndParseFloat(String text) {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    // Hàm kiểm tra xem điểm có nằm trong khoảng từ 0 đến 10 không
    private boolean isValidScore(float score) {
        return score >= 0 && score <= 10;
    }


    // Hàm cập nhật điểm trong cơ sở dữ liệu
    private boolean updateScoresInDatabase(int studentID, String classCode, float newAttendanceScore, float newRegularScore, float newMidtermScore, float newFinalScore) {
        try {
            // Establish database connection (update DatabaseConnection class if needed)
            Connection connection = DatabaseConnection.connectToBB();

            // Chuẩn bị câu truy vấn SQL UPDATE
            String updateQuery = "UPDATE scores SET attendanceScore = ?, regularScore = ?, midtermScore = ?, finalScore = ? " +
                    "WHERE studentID = ? AND classCode = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // Đặt các giá trị tham số
                preparedStatement.setFloat(1, newAttendanceScore);
                preparedStatement.setFloat(2, newRegularScore);
                preparedStatement.setFloat(3, newMidtermScore);
                preparedStatement.setFloat(4, newFinalScore);
                preparedStatement.setInt(5, studentID);
                preparedStatement.setString(6, classCode);

                // Thực thi câu truy vấn
                int rowsAffected = preparedStatement.executeUpdate();

                // Kiểm tra xem có hàng nào bị ảnh hưởng không
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void exportToExcel() {
        try {
            // Create a file chooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a location to save the Excel file");
            
            // Set the default file extension filter to Excel files
            FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
            fileChooser.setFileFilter(excelFilter);
            
            // Set a default file name with the desired extension
            String defaultFileName = "Scores.xlsx";
            fileChooser.setSelectedFile(new File(defaultFileName));

            // Show the file chooser dialog
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File file = fileChooser.getSelectedFile();
                
                // Ensure the selected file has the correct extension
                if (!file.getName().toLowerCase().endsWith(".xlsx")) {
                    file = new File(file.getAbsolutePath() + ".xlsx");
                }
                
                // Create a workbook and sheet as before
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("Scores");

                // Create header row
                XSSFRow headerRow = spreadsheet.createRow(0);
                String[] headers = {"Student ID", "Name", "Attendance Score", "Regular Score", "Midterm Score", "Final Score"};
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }

                // Get data from the table and write to the sheet
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    XSSFRow row = spreadsheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount() - 1; j++) {
                        // Exclude the last column ("Total Score")
                        Cell cell = row.createCell(j);
                        cell.setCellValue(model.getValueAt(i, j).toString());
                    }
                }

                // Write to the selected file
                FileOutputStream out = new FileOutputStream(file);
                workbook.write(out);
                out.close();

                JOptionPane.showMessageDialog(this, "Data exported to Excel successfully.");

            } 
//            else if (userSelection == JFileChooser.CANCEL_OPTION) {
//                // User canceled the operation
//                JOptionPane.showMessageDialog(this, "Export to Excel canceled by the user.");
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error exporting data to Excel.");
        }
    }
    private void importFromExcel() {
        try {
            // Create a file chooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose an Excel file to import");

            // Set the file filter to accept only Excel files
            FileNameExtensionFilter excelFilter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
            fileChooser.setFileFilter(excelFilter);

            // Show the file chooser dialog
            int userSelection = fileChooser.showOpenDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                // Get the selected file
                File file = fileChooser.getSelectedFile();

                // Read data from the selected Excel file
                readDataFromExcel(file);

                JOptionPane.showMessageDialog(this, "Data imported from Excel successfully.");
            } 
//            else if (userSelection == JFileChooser.CANCEL_OPTION) {
//                // User canceled the operation
//                JOptionPane.showMessageDialog(this, "Import from Excel canceled by the user.");
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error importing data from Excel.");
        }
    }
    private void readDataFromExcel(File file) {
        try {
            // Create a FileInputStream to read the Excel file
            FileInputStream fis = new FileInputStream(file);

            // Create a workbook from the Excel file
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            // Get the first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Get the number of rows in the sheet
            int rowCount = sheet.getPhysicalNumberOfRows();

            // Clear existing data from JTable and database
            clearTableAndDatabase();

            // Iterate through each row
            for (int i = 1; i < rowCount; i++) {
                // Create a new row in the JTable
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{});

                // Get the current row in the JTable
                int currentRow = model.getRowCount() - 1;

                // Get the current row in the Excel sheet
                XSSFRow excelRow = sheet.getRow(i);

                // Get the number of columns in the sheet
                int columnCount = excelRow.getPhysicalNumberOfCells();

                // Iterate through each cell in the row
                for (int j = 0; j < columnCount; j++) {
                    // Get the current cell in the Excel sheet
                    XSSFCell cell = excelRow.getCell(j);

                    // Set the cell value to the JTable
                    model.setValueAt(getCellValue(cell), currentRow, j);
                }

                // Save the data to the database
                saveDataToDatabase(model, currentRow);
                fetchDataFromClassroom(classCode);
                
            }

            // Close the FileInputStream and workbook
            fis.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDataToDatabase(DefaultTableModel model, int row) {
        try {
            Connection connection = DatabaseConnection.connectToBB();

            // Prepare SQL query to insert data into the database
            String insertQuery = "INSERT INTO scores (studentID, classCode, attendanceScore, regularScore, midtermScore, finalScore) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Set values for the parameters based on the JTable data
                preparedStatement.setInt(1, Integer.parseInt(model.getValueAt(row, 0).toString())); // Assuming column 0 is Student ID
                preparedStatement.setString(2, classCode);
                preparedStatement.setFloat(3, Float.parseFloat(model.getValueAt(row, 2).toString())); // Assuming column 2 is Attendance Score
                preparedStatement.setFloat(4, Float.parseFloat(model.getValueAt(row, 3).toString())); // Assuming column 3 is Regular Score
                preparedStatement.setFloat(5, Float.parseFloat(model.getValueAt(row, 4).toString())); // Assuming column 4 is Midterm Score
                preparedStatement.setFloat(6, Float.parseFloat(model.getValueAt(row, 5).toString())); // Assuming column 5 is Final Score


                // Execute the SQL query to insert data
                preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearTableAndDatabase() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear all rows from JTable

        // Clear data from the database (You may need to implement this based on your database structure)
        try {
            Connection connection = DatabaseConnection.connectToBB();
            String clearDataQuery = "DELETE FROM scores WHERE classCode = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(clearDataQuery)) {
                preparedStatement.setString(1, classCode);
                preparedStatement.executeUpdate();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return cell.getNumericCellValue();
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    

}