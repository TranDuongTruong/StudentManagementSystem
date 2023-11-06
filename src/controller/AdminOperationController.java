package controller;

import javax.swing.*;

import view.AdminAccount.AdminOperationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminOperationController {

	public static void searchButtonClicked(String id, JTextField emailField, JTextField passwordField,
			JTextField nameField, JComboBox<String> roleComboBox) {
		String[] result = searchInDatabase(id);
		if (result != null) {
			emailField.setText(result[0]);
			passwordField.setText(result[1]);
			nameField.setText(result[2]);
			// Chuyển đổi giá trị "role" từ cơ sở dữ liệu để trùng khớp với JComboBox
			String role = result[3];
			if (role.equals("admin")) {
				roleComboBox.setSelectedItem("Admin");
			} else if (role.equals("student")) {
				roleComboBox.setSelectedItem("Student");
			} else if (role.equals("teacher")) {
				roleComboBox.setSelectedItem("Teacher");
			}
		} else {
			// Xử lý trường hợp không tìm thấy dữ liệu
			// Xóa dữ liệu trong các textField
			emailField.setText("");
			passwordField.setText("");
			nameField.setText("");

			// Đặt Role về "Student"
			roleComboBox.setSelectedItem("Student");

			JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu với ID này.", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);

		}
	}

	private static String[] searchInDatabase(String id) {
		String[] result = null;
		Connection connection = null;

		try {

			connection = connection = DatabaseConnection.connectToBB();

			String query = "SELECT email, password, name, role FROM accounts WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				result = new String[4];
				result[0] = resultSet.getString("email");
				result[1] = resultSet.getString("password");
				result[2] = resultSet.getString("name");
				result[3] = resultSet.getString("role");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static void deleteButtonClicked(String id, JTextField emailField, JTextField passwordField,
			JTextField nameField, JComboBox<String> roleComboBox) {
		String[] result = searchInDatabase(id);

		if (result != null) {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all of the samples?",
					"Delete Account", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				boolean deleted = deleteInDatabase(id);

				if (deleted) {
					JOptionPane.showMessageDialog(null, "Deleted Success", "Delete Account",
							JOptionPane.INFORMATION_MESSAGE);
					clearFields(emailField, passwordField, nameField, roleComboBox);
				} else {
					JOptionPane.showMessageDialog(null, "Delete Failed", "Delete Account", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "No data found with this ID", "Delete Account",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private static boolean deleteInDatabase(String id) {

		boolean deleted = false;
		Connection connection = null;

		try {
			connection = DatabaseConnection.connectToBB();

			String query = "DELETE FROM accounts WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				deleted = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return deleted;
	}

	private static void clearFields(JTextField emailField, JTextField passwordField, JTextField nameField,
			JComboBox<String> roleComboBox) {
		emailField.setText("");
		passwordField.setText("");
		nameField.setText("");
		roleComboBox.setSelectedItem("Student");
	}

	public static void updateButtonClicked(String id, JTextField emailField, JTextField passwordField,
			JTextField nameField, JComboBox<String> roleComboBox) {
		String[] result = searchInDatabase(id);

		if (result != null) {
			String email = emailField.getText();
			String password = passwordField.getText();
			String name = nameField.getText();
			String role = roleComboBox.getSelectedItem().toString().toLowerCase();

			boolean updated = updateInDatabase(id, email, password, name, role);

			if (updated) {
				JOptionPane.showMessageDialog(null, "Updated data successfully.", "Update",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Updated data failed.", "Update",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "No data found with this ID", "Update",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private static boolean updateInDatabase(String id, String email, String password, String name, String role) {
		boolean updated = false;
		Connection connection = null;

		try {
			connection = DatabaseConnection.connectToBB();

			String query = "UPDATE accounts SET email = ?, password = ?, name = ?, role = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, role);
			preparedStatement.setString(5, id);

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				updated = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return updated;
	}
}
