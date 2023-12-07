package view.Teacher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        JButton btnNewButton = new JButton("Update");
        btnNewButton.setBounds(107, 488, 146, 42);
        add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
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
                } else {
                    JOptionPane.showMessageDialog(ManageScroresViewP.this, "Please select a row to update.");
                }
            }
        });
        // Trong phương thức khởi tạo của lớp ManageScroresViewP
        JLabel lblAttendanceScore = new JLabel("Attendance Score:");
        lblAttendanceScore.setBounds(28, 425, 150, 20);
        add(lblAttendanceScore);

        JLabel lblRegularScore = new JLabel("Regular Score:");
        lblRegularScore.setBounds(148, 425, 150, 20);
        add(lblRegularScore);

        JLabel lblMidtermScore = new JLabel("Midterm Score:");
        lblMidtermScore.setBounds(268, 425, 150, 20);
        add(lblMidtermScore);

        JLabel lblFinalScore = new JLabel("Final Score:");
        lblFinalScore.setBounds(388, 425, 150, 20);
        add(lblFinalScore);

        textFieldAttendanceScore = new JTextField();
        textFieldAttendanceScore.setBounds(28, 450, 100, 25);
        add(textFieldAttendanceScore);

        textFieldRegularScore = new JTextField();
        textFieldRegularScore.setBounds(148, 450, 100, 25);
        add(textFieldRegularScore);

        textFieldMidtermScore = new JTextField();
        textFieldMidtermScore.setBounds(268, 450, 100, 25);
        add(textFieldMidtermScore);

        textFieldFinalScore = new JTextField();
        textFieldFinalScore.setBounds(388, 450, 100, 25);
        add(textFieldFinalScore);

        btnSave = new JButton("Save");
        btnSave.setBounds(268, 488, 146, 42);
        add(btnSave);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateScores();
                fetchDataFromClassroom(classCode);
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
}