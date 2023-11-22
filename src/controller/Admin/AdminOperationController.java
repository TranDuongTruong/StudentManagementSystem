package controller.Admin;

import javax.swing.*;

import controller.DatabaseConnection;
import view.Admin.AdminOperationView;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminOperationController {

	public static void searchButtonClicked(String id, JTextField emailField, JTextField passwordField,
			JTextField nameField, JTextField roleField, JLabel errorLabel,JLabel studentIDLabel, JTextField studentIDField) {
		String[] result = searchInDatabase(id);
		errorLabel.setText("");
	    emailField.setBackground(Color.WHITE);
	    passwordField.setBackground(Color.WHITE);
	    nameField.setBackground(Color.WHITE);
		if (result != null) {
			emailField.setText(result[0]);
			passwordField.setText(result[1]);
			nameField.setText(result[2]);
			roleField.setText(result[3]);
			studentIDField.setText(result[4]);
			if(roleField.getText().toString().equals("student")) {
				studentIDLabel.setVisible(true);
				studentIDField.setVisible(true);
		        studentIDField.setEditable(false);
			}else {
		        studentIDLabel.setVisible(false);
		        studentIDField.setVisible(false);
		        studentIDField.setEditable(false);
		    }
		} else {
			// Xử lý trường hợp không tìm thấy dữ liệu
			clearFields(emailField, passwordField, nameField, roleField, errorLabel);
			JOptionPane.showMessageDialog(null, "No data found with this ID.", "Search Account",
					JOptionPane.INFORMATION_MESSAGE);

		}
	}

	private static String[] searchInDatabase(String id) {
	    String[] result = null;
	    Connection connection = null;

	    try {
	        connection = DatabaseConnection.connectToBB();

	        String query = "SELECT email, password, name, role, studentID FROM accounts WHERE id = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, id);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            result = new String[5];  // Increase the array size to accommodate studentID
	            result[0] = resultSet.getString("email");
	            result[1] = resultSet.getString("password");
	            result[2] = resultSet.getString("name");
	            result[3] = resultSet.getString("role");
	            result[4] = resultSet.getString("studentID");
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
			JTextField nameField, JTextField roleField, JLabel errorLabel) {
		String[] result = searchInDatabase(id);

		if (result != null) {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete account?",
					"Delete Account", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				boolean deleted = deleteInDatabase(id);

				if (deleted) {
					JOptionPane.showMessageDialog(null, "Deleted Success", "Delete Account",
							JOptionPane.INFORMATION_MESSAGE);
					clearFields(emailField, passwordField, nameField, roleField, errorLabel);
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
			JTextField roleField, JLabel errorLabel) {
		errorLabel.setText("");
	    emailField.setBackground(Color.WHITE);
	    passwordField.setBackground(Color.WHITE);
	    nameField.setBackground(Color.WHITE);
		emailField.setText("");
		passwordField.setText("");
		nameField.setText("");
		roleField.setText("");
	}
	 private static boolean isValidEmail(String email) {
	        // Basic email validation using regex
	        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
	        return email.matches(emailPattern);
	    }

	 private static boolean isValidPassword(String password) {
	        // Check for password length
	        return password.length() >= 6;
	    }

	 private static boolean isValidName(String name) {
	        // Check for name length
	        return name.length() >= 4;
	    }
	 private static boolean isEmailExists(String email) {
	        try {
	            Connection connection = DatabaseConnection.connectToBB();
	            String query = "SELECT COUNT(*) FROM accounts WHERE email = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, email);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            resultSet.next();
	            int count = resultSet.getInt(1);

	            resultSet.close();
	            preparedStatement.close();
	            connection.close();

	            return count > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	 }

	public static void updateButtonClicked(String id, JTextField emailField, JTextField passwordField,
			JTextField nameField, JTextField roleField,JLabel errorLabel) {
		  String[] result = searchInDatabase(id);

		    if (result != null) {
		        String email = emailField.getText();
		        String password = passwordField.getText();
		        String name = nameField.getText();
		      

		        if (isValidEmail(email) && isValidPassword(password) && isValidName(name)) {
		        	if (isEmailExists(email)  && !email.equals(result[0])) {
		        		errorLabel.setText("Email already exists. Please choose another email.");
		                emailField.setBackground(Color.PINK);
		                errorLabel.setForeground(Color.RED);
		            }
		        	else {
		            	boolean updated = updateInDatabase(id, email, password, name);

//			            if (updated) {
//			                errorLabel.setText("Updated data successfully.");
//			                errorLabel.setForeground(Color.GREEN);
//			            } else {
//			                errorLabel.setText("Updated data failed.");
//			                errorLabel.setForeground(Color.RED);
//			            }
		            	if (updated) {
		    				JOptionPane.showMessageDialog(null, "Updated data successfully.", "Update",
		    						JOptionPane.INFORMATION_MESSAGE);
		    			} else {
		    				JOptionPane.showMessageDialog(null, "Updated data failed.", "Update",
		    						JOptionPane.ERROR_MESSAGE);
		    				
		    			}
		            }
		            
		        } else {
		        	  if (!isValidEmail(email)) {
		                  errorLabel.setText("Invalid email format.");
		                  emailField.setBackground(Color.PINK);
		                  errorLabel.setForeground(Color.RED);
		              }
		              if (!isValidPassword(password)) {
		                  errorLabel.setText("Password must be 6 characters or more.");
		                  passwordField.setBackground(Color.PINK);
		                  errorLabel.setForeground(Color.RED);
		              }
		              if(!isValidName(name)){
		            	  errorLabel.setText("Name must be 4 characters or more.");
		                  nameField.setBackground(Color.PINK);
		                  errorLabel.setForeground(Color.RED);
		              }
		        }
		    } else {
//		        errorLabel.setText("No data found with this ID");
//		        errorLabel.setForeground(Color.RED);
		    	JOptionPane.showMessageDialog(null, "No data found with this ID", "Update",
						JOptionPane.INFORMATION_MESSAGE);
				clearFields(emailField, passwordField, nameField, roleField, errorLabel);

		    }
	}

	private static boolean updateInDatabase(String id, String email, String password, String name) {
		boolean updated = false;
		Connection connection = null;

		try {
			connection = DatabaseConnection.connectToBB();

			String query = "UPDATE accounts SET email = ?, password = ?, name = ? WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, id);

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
