package CarbonEmissionCalculator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	// DatabaseConnection db = new DatabaseConnection();

	public void addUser(String username, String password) throws SQLException {

		String add = "INSERT INTO USERS (username,password) values (?,?)";
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(add);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected >= 1) {
				System.out.println("User Added successfully");
			} else {
				System.out.println("Failed to add user");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean authenticate(String username, String password) {
		String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getUserId(String name) {

		String select = "Select user_id from users where username=?";
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement stmt = connection.prepareStatement(select);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void deleteUser(String name) {

		String delete = "delete from users where username=?";
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(delete);
			pstmt.setString(1, name);
			int result = pstmt.executeUpdate();
			if (result >= 1) {
				System.out.println("User Deleted sucessfully");
			} else {
				System.out.println("Invalid user");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
