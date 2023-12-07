package view.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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


    /**
     * Create the panel.
     */
    public ManageScroresViewP(Classroom classroom, String classCode) {
        this.classroom = classroom;
        this.classCode = classCode;
        setLayout(null);
        setBounds(162, 0, 835, 640);

        JLabel lblListOfScores = new JLabel("List of scores");
        lblListOfScores.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblListOfScores.setBounds(28, 124, 251, 54);
        add(lblListOfScores);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(28, 212, 738, 214);
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

        scrollPane.setViewportView(table);

       

        // Trong phương thức khởi tạo của lớp ManageScroresViewP
        JLabel lblAttendanceScore = new JLabel("Attendance Score:");
        lblAttendanceScore.setBounds(269, 490, 150, 20);
        add(lblAttendanceScore);

        JLabel lblRegularScore = new JLabel("Regular Score:");
        lblRegularScore.setBounds(389, 490, 150, 20);
        add(lblRegularScore);

        JLabel lblMidtermScore = new JLabel("Midterm Score:");
        lblMidtermScore.setBounds(509, 490, 150, 20);
        add(lblMidtermScore);

        JLabel lblFinalScore = new JLabel("Final Score:");
        lblFinalScore.setBounds(629, 490, 150, 20);
        add(lblFinalScore);

        textFieldAttendanceScore = new JTextField();
        textFieldAttendanceScore.setBounds(269, 515, 100, 25);
        add(textFieldAttendanceScore);

        textFieldRegularScore = new JTextField();
        textFieldRegularScore.setBounds(389, 515, 100, 25);
        add(textFieldRegularScore);

        textFieldMidtermScore = new JTextField();
        textFieldMidtermScore.setBounds(509, 515, 100, 25);
        add(textFieldMidtermScore);

        textFieldFinalScore = new JTextField();
        textFieldFinalScore.setBounds(629, 515, 100, 25);
        add(textFieldFinalScore);

        JLabel lblTotalScore = new JLabel("Total Score:");
        lblTotalScore.setBounds(748, 490, 150, 20);
        add(lblTotalScore);

        textFieldTotalScore = new JTextField();
        textFieldTotalScore.setBounds(748, 515, 100, 25);
        textFieldTotalScore.setEditable(false); // Không cho phép chỉnh sửa
        add(textFieldTotalScore);
        
        JLabel lblStudentsScore = new JLabel("Student's score");
        lblStudentsScore.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblStudentsScore.setBounds(28, 437, 251, 42);
        add(lblStudentsScore);
        
        JLabel lblStudentID = new JLabel("Student ID:");
        lblStudentID.setBounds(28, 490, 150, 20);
        add(lblStudentID);
        
        JLabel lblStudentName = new JLabel("Name:");
        lblStudentName.setBounds(148, 490, 150, 20);
        add(lblStudentName);
        
        textFieldStudentName = new JTextField();
        textFieldStudentName.setEditable(false);
        textFieldStudentName.setBounds(148, 515, 100, 25);
        add(textFieldStudentName);
        
        textFieldStudentID = new JTextField();
        textFieldStudentID.setEditable(false);
        textFieldStudentID.setBounds(28, 515, 100, 25);
        add(textFieldStudentID);
        
        JButton btnUpdateButton = new JButton("Update");
        btnUpdateButton.setBounds(111, 568, 146, 42);
        add(btnUpdateButton);
        btnUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    // Get data from the selected row and populate text fields
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    textFieldAttendanceScore.setText(model.getValueAt(selectedRow, 2).toString());
                    textFieldRegularScore.setText(model.getValueAt(selectedRow, 3).toString());
                    textFieldMidtermScore.setText(model.getValueAt(selectedRow, 4).toString());
                    textFieldFinalScore.setText(model.getValueAt(selectedRow, 5).toString());
                    textFieldStudentID.setText(model.getValueAt(selectedRow, 0).toString());
                    textFieldStudentName.setText(model.getValueAt(selectedRow, 1).toString());
                    textFieldTotalScore.setText(model.getValueAt(selectedRow, 6).toString());
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
        
        // Add ActionListener for the export button
        btnExportToExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToExcel();
            }
        });
        // Fetch data from the database based on the classCode
        fetchDataFromClassroom(classCode);

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

    private void handleUpdateScores() {
        // Lấy thông tin từ text fields
        int selectedRow = table.getSelectedRow();

        if (selectedRow != -1) {
            int studentID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            String classCode = this.classCode;
            float newAttendanceScore = Float.parseFloat(textFieldAttendanceScore.getText());
            float newRegularScore = Float.parseFloat(textFieldRegularScore.getText());
            float newMidtermScore = Float.parseFloat(textFieldMidtermScore.getText());
            float newFinalScore = Float.parseFloat(textFieldFinalScore.getText());

            // Gọi hàm cập nhật điểm trong cơ sở dữ liệu
            boolean success = updateScoresInDatabase(studentID, classCode, newAttendanceScore, newRegularScore, newMidtermScore, newFinalScore);
            // Xóa dữ liệu từ các JTextField
            textFieldAttendanceScore.setText("");
            textFieldRegularScore.setText("");
            textFieldMidtermScore.setText("");
            textFieldFinalScore.setText("");
            textFieldStudentID.setText("");
            textFieldStudentName.setText("");
            textFieldTotalScore.setText("");
            if (success) {
                // Hiển thị thông báo cập nhật thành công hoặc thực hiện các hành động cần thiết
                JOptionPane.showMessageDialog(this, "Scores updated successfully");
            } else {
                // Hiển thị thông báo lỗi hoặc thực hiện các hành động cần thiết
                JOptionPane.showMessageDialog(this, "Error updating scores");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
        }
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
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Scores");

            // Create header row
            XSSFRow headerRow = spreadsheet.createRow(0);
            String[] headers = {"Student ID", "Name", "Attendance Score", "Regular Score", "Midterm Score", "Final Score"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Get data from the table
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                XSSFRow row = spreadsheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount() - 1; j++) {
                    // Exclude the last column ("Total Score")
                    Cell cell = row.createCell(j);
                    cell.setCellValue(model.getValueAt(i, j).toString());
                }
            }

            // Write to Excel file
            String desktopPath = System.getProperty("user.home") + "\\Desktop";
            String filePath = desktopPath + "\\Scores.xlsx";
            FileOutputStream out = new FileOutputStream(new File(filePath));
            workbook.write(out);
            out.close();

            JOptionPane.showMessageDialog(this, "Data exported to Excel successfully.");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error exporting data to Excel.");
        }
    }

}
